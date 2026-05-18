package edu.rachelpizane.icompras.faturamento.api;

import edu.rachelpizane.icompras.faturamento.bucket.BucketFile;
import edu.rachelpizane.icompras.faturamento.bucket.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URI;

@RestController
@RequestMapping("/bucket")
@RequiredArgsConstructor
public class BucketController {

    private final BucketService service;

    @GetMapping
    public ResponseEntity<Void> getUrl(@RequestParam String filename) {
        try {
            String url = service.getUrl(filename);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create(url))
                    .build();
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            MediaType type = MediaType.parseMediaType(file.getContentType());
            BucketFile bucket = new BucketFile(file.getOriginalFilename(), is, type, file.getSize());

            service.upload(bucket);
            return ResponseEntity.status(HttpStatus.OK).body("Arquivo enviado com sucesso");

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao enviar o arquivo: " + e.getMessage());
        }
    }
}
