package com.jishudaniu.dkblog.strategy.context;

import com.jishudaniu.dkblog.strategy.UploadStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import static com.jishudaniu.dkblog.enums.UploadModeEnum.getStrategy;


/**
 * 上传策略上下文
 *
 * @author darkchen
 * @date 2021/12/28
 */
@Service
public class UploadStrategyContext {
    /**
     * 上传模式
     */
    @Value("${upload.mode}")
    private String uploadMode;

    @Autowired
    private Map<String, UploadStrategy> uploadStrategyMap;

    /**
     * 上传文件
     *
     * @param file 文件
     * @param path 路径
     * @return {@link String} 文件地址
     */
    public String executeUploadStrategy(MultipartFile file, String path) {
        return uploadStrategyMap.get(getStrategy(uploadMode)).uploadFile(file, path);
    }


}
