package io.github.sodmomas.takeaway.file;

import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * @author Sod-Momas
 * @since 2023/6/30
 */
@RestController
public class FileController {
    private static final String root = "/var/file/";

    @PostMapping("/file/up")
    List<String> up(@RequestPart("files") MultipartFile[] files) throws IOException {
        List<String> paths = new ArrayList<>();
        for (MultipartFile file : files) {
            paths.add(save(file));
        }
        return paths;
    }

    @GetMapping("/file/{filename}")
    ResponseEntity<Resource> down(@PathVariable("filename") String filename) {
        final Path base = Paths.get(FileController.root).resolve(filename);
        if (Files.notExists(base)) {
            return ResponseEntity.notFound().build();
        }
        String contentDisposition = ContentDisposition.attachment().filename(filename, Charset.defaultCharset()).build().toString();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new PathResource(base));
    }

    private String save(MultipartFile file) throws IOException {
        final String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
        final String filename = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase(Locale.ROOT) + "." + (ext == null ? "" : ext);
        final Path tmp = Paths.get("/var/file/" + filename);
        file.transferTo(tmp);
        return "http://localhost/files/" + filename;
    }
}
