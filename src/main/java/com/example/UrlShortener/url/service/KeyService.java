package com.example.UrlShortener.url.service;

import java.util.Set;

public interface KeyService {
    Set<String> generateKeyBatch(int batchSize);

    String getKey();
}
