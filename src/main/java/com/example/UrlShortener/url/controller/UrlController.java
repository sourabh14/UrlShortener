package com.example.UrlShortener.url.controller;

import com.example.UrlShortener.url.dto.CreateShortUrlRequest;
import com.example.UrlShortener.url.dto.CreateShortUrlResponse;
import com.example.UrlShortener.url.service.UrlService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/v1/url")
@AllArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @PostMapping("/create")
    public ResponseEntity<CreateShortUrlResponse> createShortUrl(@Valid @RequestBody CreateShortUrlRequest request)
            throws BadRequestException {
        CreateShortUrlResponse response = urlService.createShortUrl(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{key}}")
    public ResponseEntity<RedirectView> getLongUrl(@Valid @PathVariable("key") String key) {
        RedirectView redirectView = new RedirectView();
        String longUrl = urlService.getLongUrl(key);
        if (longUrl != null) {
            redirectView.setUrl(longUrl);
            return new ResponseEntity<>(redirectView, HttpStatus.MOVED_PERMANENTLY);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
