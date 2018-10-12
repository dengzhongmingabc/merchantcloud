package com.yidao.goods.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFastDFSClientService {

    public String saveFile(MultipartFile multipartFile) throws IOException;
}