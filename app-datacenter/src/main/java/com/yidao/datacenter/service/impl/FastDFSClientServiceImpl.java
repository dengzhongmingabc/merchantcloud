package com.yidao.datacenter.service.impl;

import com.yidao.core.utils.FastDFSFileUtil;
import com.yidao.datacenter.service.IFastDFSClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Component
@Service
public class FastDFSClientServiceImpl implements IFastDFSClientService {

    private final Logger logger = LoggerFactory.getLogger(FastDFSClientServiceImpl.class);

    public String saveFile(MultipartFile multipartFile) throws IOException {
        String[] fileAbsolutePath={};
        String fileName=multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream=multipartFile.getInputStream();
        if(inputStream!=null){
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDFSFileUtil file = new FastDFSFileUtil(fileName, file_buff, ext);
        try {
            fileAbsolutePath = file.upload(file);  //upload to fastdfs
        } catch (Exception e) {
            logger.error("upload file Exception!",e);
        }
        if (fileAbsolutePath==null) {
            logger.error("upload file failed,please upload again!");
        }
        String path=fileAbsolutePath[0]+ "/"+fileAbsolutePath[1];
        return path;
    }
}