package com.berg.fastsearch.core.system.file.service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import java.io.File;
import java.io.InputStream;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-7
 */
public interface IQiNiuService {
    Response uploadFile(File file) throws QiniuException;

    Response uploadFile(InputStream inputStream) throws QiniuException;

    Response delete(String key) throws QiniuException;
}
