package com.example.UrlShortener.url.service;

import java.util.Set;

public interface KeyService {
    void generateKeyBatch(int batchSize);

    int getKeyCount();

    String getKey();
}
