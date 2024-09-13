package com.example.UrlShortener.url.service.impl;

import com.example.UrlShortener.url.dto.CreateUrlShortLinkRequest;
import com.example.UrlShortener.url.dto.CreateUrlShortLinkResponse;
import com.example.UrlShortener.url.service.UrlService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UrlServiceImpl implements UrlService {

    @Override
    public CreateUrlShortLinkResponse createUrlShortLink(CreateUrlShortLinkRequest request) {
        return null;
    }

}
