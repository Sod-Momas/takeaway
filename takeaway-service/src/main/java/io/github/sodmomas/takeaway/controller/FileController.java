package io.github.sodmomas.takeaway.controller;

import io.github.sodmomas.takeaway.common.result.Result;
import io.github.sodmomas.takeaway.service.MinioOssService;
import io.github.sodmomas.takeaway.model.dto.FileInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "07.文件接口")
@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

    private final MinioOssService minioOssService;

    @PostMapping
    @Operation(summary = "文件上传", security = {@SecurityRequirement(name = "Authorization")})
    public Result<FileInfo> uploadFile(
            @Parameter(description ="表单文件对象") @RequestParam(value = "file") MultipartFile file
    ) {
        FileInfo fileInfo = minioOssService.uploadFile(file);
        return Result.success(fileInfo);
    }

    @DeleteMapping
    @Operation(summary = "文件删除", security = {@SecurityRequirement(name = "Authorization")})
    @SneakyThrows
    public Result deleteFile(
            @Parameter(description ="文件路径") @RequestParam String filePath
    ) {
        boolean result = minioOssService.deleteFile(filePath);
        return Result.judge(result);
    }
}
