package com.yidao.datacenter.controller;

import com.yidao.core.utils.JSONHelper;
import com.yidao.core.web.BaseController;
import com.yidao.datacenter.service.IFastDFSClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Api(description = "文件相关api")
@RestController
@RequestMapping("/api/file")
public class FileController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IFastDFSClientService fastDFSClientService;

    @Value("${sysconfig.domain}")
    private String domain;

    @ApiOperation(value="文件上传")
    @ResponseBody
    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    public void uploadFile(@RequestParam MultipartFile uploadFile, RedirectAttributes redirectAttributes){
        if (uploadFile.isEmpty()) {
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
        String myFileName = uploadFile.getOriginalFilename();
        String contentType = uploadFile.getContentType();
        /*if (contentType.equals("image/pjpeg")
                || contentType.equals("image/gif")
                || contentType.equals("image/jpeg")
                || contentType.equals("image/png")
                || contentType.equals("image/webp")) {
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString("只能上传pjpeg，gif"));
            return;
        }*/
        try {
            String path=fastDFSClientService.saveFile(uploadFile);
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString(domain+path));
            return;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }

    }
}
