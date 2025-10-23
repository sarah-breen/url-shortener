package dev.sarah.urlshortener.controller;

import dev.sarah.urlshortener.dto.CreateUrlRequest;
import dev.sarah.urlshortener.dto.UrlResponse;
import dev.sarah.urlshortener.model.UrlEntity;
import dev.sarah.urlshortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shorten")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    //POST /shorten - Create a new short URL
    @PostMapping
    public ResponseEntity<UrlResponse> createShortUrl(@RequestBody CreateUrlRequest request) {
        UrlEntity entity = urlService.createShortUrl(request.getUrl());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UrlResponse.fromEntity(entity));
    }

    //GET /shorten/{shortCode] - retrieve original URL
    @GetMapping("/{shortCode}")
    public ResponseEntity<UrlResponse> getUrl(@PathVariable String shortCode) {
        UrlEntity entity = urlService.getByShortCode(shortCode);
        return  ResponseEntity.ok(UrlResponse.fromEntity(entity));
    }

    //PUT /shorten/{shortCode} - update original URL
    @PutMapping("/{shortCode}")
    public ResponseEntity<UrlResponse> updateUrl(@PathVariable String shortCode, @RequestBody CreateUrlRequest request) {
        UrlEntity entity = urlService.updateUrl(shortCode, request.getUrl());
        return ResponseEntity.ok(UrlResponse.fromEntity(entity));
    }

    //DELETE /shorten/{shortCode} - delete URL
    @DeleteMapping("/{shortCode}")
    public ResponseEntity<Void> deleteUrl(@PathVariable String shortCode) {
        urlService.deleteUrl(shortCode);
        return ResponseEntity.noContent().build();
    }

    //GET /shorten/{shortCode}/stats - Get URL statistics
    @GetMapping("/{shortCode}/stats")
    public ResponseEntity<UrlResponse> getUrlStats(@PathVariable String shortCode) {
        UrlEntity entity = urlService.getByShortCode(shortCode);
        urlService.incrementAccessCount(shortCode);
        return ResponseEntity.ok(UrlResponse.fromEntity(entity));
    }
}