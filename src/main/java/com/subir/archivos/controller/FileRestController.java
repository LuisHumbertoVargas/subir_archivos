package com.subir.archivos.controller;

import com.subir.archivos.model.MyFile;
import com.subir.archivos.model.Response;
import com.subir.archivos.service.IFileServiceAPI;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;


@RestController
@RequestMapping("/files")
public class FileRestController {
    
    @Autowired
    private IFileServiceAPI fileServiceAPI;
    
    @PostMapping("/upload")
    public ResponseEntity<Response> uploadsFiles(@RequestParam("files") List<MultipartFile> files) throws Exception {
        fileServiceAPI.save(files);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("Upload Completed"));
    }
    
    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws Exception{
        Resource resource = fileServiceAPI.load(filename);
        return ResponseEntity.ok().header(
                HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
                        resource.getFilename() + "\"").body(resource);
        
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<MyFile>> getAllFiles() throws Exception {
        List<MyFile> files = fileServiceAPI.loadAll().map(path->{
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(FileRestController.class, "getFile", path.getFileName().toString()).build().toString();
            return new MyFile(filename, url);         
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }
    
//    @PutMapping("/{id}")
//    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
//        return null;
//    }
    
    
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable String id) {
//        return null;
//    }
    
}
