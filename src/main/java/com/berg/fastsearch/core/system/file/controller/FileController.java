package com.berg.fastsearch.core.system.file.controller;

import com.berg.fastsearch.core.car.entity.CarPicture;
import com.berg.fastsearch.core.car.web.dto.CarPictureDto;
import com.berg.fastsearch.core.system.base.web.dto.ResponseData;
import com.berg.fastsearch.core.system.file.dto.QiNiuPutRet;
import com.berg.fastsearch.core.system.file.service.IQiNiuService;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-7
 */
@RequestMapping("/system/file")
@RestController
public class FileController {

    @Autowired
    private IQiNiuService qiNiuService;

    @Autowired
    private Gson gson;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseData upload(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()) {
            return ResponseData.ofStatus(ResponseData.Status.NOT_VALID_PARAM);
        }

        String fileName = file.getOriginalFilename();

        try {
            InputStream inputStream = file.getInputStream();
            Response response = qiNiuService.uploadFile(inputStream);
            if (response.isOK()) {
                QiNiuPutRet ret = gson.fromJson(response.bodyString(), QiNiuPutRet.class);
                return ResponseData.ofSuccess(ret);
            } else {
                return ResponseData.ofMessage(response.statusCode, response.getInfo());
            }

        } catch (QiniuException e) {
            Response response = e.response;
            try {
                return ResponseData.ofMessage(response.statusCode, response.bodyString());
            } catch (QiniuException e1) {
                e1.printStackTrace();
                return ResponseData.ofStatus(ResponseData.Status.INTERNAL_SERVER_ERROR);
            }
        } catch (IOException e) {
            return ResponseData.ofStatus(ResponseData.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
