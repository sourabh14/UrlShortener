package com.example.UrlShortener.url.service;

import com.example.UrlShortener.url.dto.CreateShortUrlRequest;
import com.example.UrlShortener.url.dto.CreateShortUrlResponse;
import com.example.UrlShortener.url.dto.GetTopDomainsResponse;

public interface UrlService {
    CreateShortUrlResponse createShortUrl(CreateShortUrlRequest request);

    String getLongUrl(String key);

    GetTopDomainsResponse getTopDomains();
}
