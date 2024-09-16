package com.example.UrlShortener.url.service.impl;

import com.example.UrlShortener.url.dto.CreateShortUrlRequest;
import com.example.UrlShortener.url.dto.CreateShortUrlResponse;
import com.example.UrlShortener.url.dto.GetTopDomainsResponse;
import com.example.UrlShortener.url.service.KeyService;
import com.example.UrlShortener.url.service.UrlService;
import com.example.UrlShortener.url.util.UrlUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UrlServiceImpl implements UrlService {
    private final KeyService keyService;
    // short-url to long-url
    private static ConcurrentMap<String, String> urlMap = new ConcurrentHashMap<>();
    // long-url to short-url
    private static ConcurrentMap<String, String> reverseUrlMap = new ConcurrentHashMap<>();
    private static final String BASE_URL = "http://localhost:8081/";

    @Override
    public CreateShortUrlResponse createShortUrl(CreateShortUrlRequest request) {
        CreateShortUrlResponse response = new CreateShortUrlResponse();
        String url = request.getUrl();
        response.setUrl(url);
        if (reverseUrlMap.containsKey(url)) {
            response.setShortenedUrl(reverseUrlMap.get(url));
            return response;
        }
        // Synchronize only when adding a new URL to the map
        synchronized (this) {
            // Check again to avoid race condition
            if (reverseUrlMap.containsKey(url)) {
                response.setShortenedUrl(reverseUrlMap.get(url));
                return response;
            }
            String shortUrlKey = keyService.getKey();
            String shortUrl = BASE_URL + shortUrlKey;
            System.out.println("url: " + url + ", shortUrl: " + shortUrl);

            urlMap.put(shortUrl, url);
            reverseUrlMap.put(url, shortUrl);
            response.setShortenedUrl(shortUrl);
            return response;
        }
    }

    @Override
    public String getLongUrl(String key) {
        return urlMap.get(BASE_URL + key);
    }

    @Override
    public GetTopDomainsResponse getTopDomains() {
        GetTopDomainsResponse response = new GetTopDomainsResponse();
        Map<String, Integer> domainCountMap = new HashMap<>();

        for (String url : reverseUrlMap.keySet()) {
            String domain = UrlUtils.getDomainFromUrl(url);
            domainCountMap.put(domain, domainCountMap.getOrDefault(domain, 0) + 1);
        }

        List<String> topDomains = domainCountMap.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) // Sort by count descending
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        response.setTopDomains(topDomains);
        return response;
    }

}
