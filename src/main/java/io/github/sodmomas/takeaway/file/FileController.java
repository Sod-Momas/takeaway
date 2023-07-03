//package io.github.sodmomas.takeaway.file;
//
///**
// * @author Sod-Momas
// * @since 2023/6/30
// */
////@RestController
////public class FileController {
////    private static final String root = "/var/file/";
////
////    @PostMapping("/file/up")
////    List<String> up(@RequestPart("files") MultipartFile[] files) throws IOException {
////        List<String> paths = new ArrayList<>();
////        for (MultipartFile file : files) {
////            paths.add(save(file));
////        }
////        return paths;
////    }
////
////    @GetMapping("/file/{filename}")
////    ResponseEntity<Resource> down(@PathVariable("filename") String filename) {
////        final Path base = Paths.get(FileController.root).resolve(filename);
////        if (Files.notExists(base)) {
////            return ResponseEntity.notFound().build();
////        }
////        String contentDisposition = ContentDisposition.attachment().filename(filename, Charset.defaultCharset()).build().toString();
////        return ResponseEntity.ok()
////                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
////                .contentType(MediaType.APPLICATION_OCTET_STREAM)
////                .body(new PathResource(base));
////    }
////
////    private String save(MultipartFile file) throws IOException {
////        final String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
////        final String filename = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase(Locale.ROOT) + "." + (ext == null ? "" : ext);
////        final Path tmp = Paths.get("/var/file/" + filename);
////        file.transferTo(tmp);
////        return "http://localhost/files/" + filename;
////    }
////}
