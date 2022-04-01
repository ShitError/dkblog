package com.jishudaniu.dkblog.strategy;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传策略
 *
 * @author darkchen
 * @date 2021/12/28
 */
public interface UploadStrategy {

    /**
     * 上传文件
     *
     * @param file 文件
     * @param path 上传路径
     * @return {@link String} 文件地址
     */
    String uploadFile(MultipartFile file, String path);

}
