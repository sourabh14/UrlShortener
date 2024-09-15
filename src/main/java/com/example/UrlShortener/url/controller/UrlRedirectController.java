package com.example.UrlShortener.url.controller;

import com.example.UrlShortener.url.service.UrlService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@AllArgsConstructor
public class UrlRedirectController {
    private final UrlService urlService;

    @GetMapping("/{key}")
    public RedirectView getLongUrl(@Valid @PathVariable("key") String key) {
        String longUrl = urlService.getLongUrl(key);
        if (longUrl != null) {
            return new RedirectView(longUrl);
        }
        return new RedirectView("/error");
    }
}
