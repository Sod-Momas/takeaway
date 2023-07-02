package io.github.sodmomas.takeaway.service;

import io.github.sodmomas.takeaway.model.dto.FileInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 对象存储服务接口层
 *
 * @author haoxr
 * @since 2022/11/19
 */
@Service
public class OssService {

    /**
     * 上传文件
     *
     * @param file 表单文件对象
     * @return 文件信息
     */
    public FileInfo uploadFile(MultipartFile file) {
        return new FileInfo();
    }

    /**
     * 删除文件
     *
     * @param filePath 文件完整URL
     * @return 删除结果
     */
    public boolean deleteFile(String filePath) {
        return false;
    }


}
