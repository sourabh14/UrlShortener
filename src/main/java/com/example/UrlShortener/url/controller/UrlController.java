package com.example.UrlShortener.url.controller;

import com.example.UrlShortener.url.dto.CreateUrlShortLinkRequest;
import com.example.UrlShortener.url.dto.CreateUrlShortLinkResponse;
import com.example.UrlShortener.url.service.UrlService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/url")
@AllArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @PostMapping("/create")
    public ResponseEntity<CreateUrlShortLinkResponse> createShortLink(@Valid @RequestBody CreateUrlShortLinkRequest request)
            throws BadRequestException {
        CreateUrlShortLinkResponse response = urlService.createUrlShortLink(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
