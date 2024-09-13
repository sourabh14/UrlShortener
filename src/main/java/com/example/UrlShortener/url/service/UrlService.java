package com.example.UrlShortener.url.service;

import com.example.UrlShortener.url.dto.CreateUrlShortLinkRequest;
import com.example.UrlShortener.url.dto.CreateUrlShortLinkResponse;

public interface UrlService {
    CreateUrlShortLinkResponse createUrlShortLink(CreateUrlShortLinkRequest request);
}
