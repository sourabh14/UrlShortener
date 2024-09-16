package com.example.UrlShortener.url.controller;

import com.example.UrlShortener.url.dto.GetTopDomainsResponse;
import com.example.UrlShortener.url.service.UrlService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/metrics")
@AllArgsConstructor
public class UrlMetricsController {
    private final UrlService urlService;

    @GetMapping("/top-domains")
    public ResponseEntity<GetTopDomainsResponse> getTopDomains() {
        GetTopDomainsResponse response = urlService.getTopDomains();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
