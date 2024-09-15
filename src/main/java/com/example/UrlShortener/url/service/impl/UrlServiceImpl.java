package com.example.UrlShortener.url.service.impl;

import com.example.UrlShortener.url.dto.CreateShortUrlRequest;
import com.example.UrlShortener.url.dto.CreateShortUrlResponse;
import com.example.UrlShortener.url.service.UrlService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
@AllArgsConstructor
public class UrlServiceImpl implements UrlService {
    // short-url to long-url
    private ConcurrentMap<String, String> urlMap = new ConcurrentHashMap<>();
    // long-url to short-url
    private ConcurrentMap<String, String> reverseUrlMap = new ConcurrentHashMap<>();
    private static final String BASE_URL = "http://localhost:8080/";

    @Override
    public CreateShortUrlResponse createShortUrl(CreateShortUrlRequest request) {
        CreateShortUrlResponse response = new CreateShortUrlResponse();
        String url = request.getUrl();
        response.setUrl(url);
        if (reverseUrlMap.containsKey(url)) {
            response.setShortenedUrl(reverseUrlMap.get(url));
            return response;
        }
        String shortUrlKey = generateKey();
        String shortUrl = BASE_URL + shortUrlKey;
        System.out.println("url: " + url + ", shortUrl: " + shortUrl);

        urlMap.put(shortUrl, url);
        reverseUrlMap.put(url, shortUrl);

        response.setShortenedUrl(urlMap.get(request.getUrl()));
        return null;
    }

}
