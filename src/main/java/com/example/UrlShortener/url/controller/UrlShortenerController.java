package com.example.UrlShortener.url.controller;

import com.example.UrlShortener.url.dto.CreateShortUrlRequest;
import com.example.UrlShortener.url.dto.CreateShortUrlResponse;
import com.example.UrlShortener.url.service.UrlService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/url")
@AllArgsConstructor
public class UrlShortenerController {
    private final UrlService urlService;

    @PostMapping("/create")
    public ResponseEntity<CreateShortUrlResponse> createShortUrl(@Valid @RequestBody CreateShortUrlRequest request) {
        CreateShortUrlResponse response = urlService.createShortUrl(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
