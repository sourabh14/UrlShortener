package com.example.UrlShortener.url.service.impl;

import com.example.UrlShortener.url.service.KeyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class KeyServiceImpl implements KeyService {

    // Thread safe queue
    private ConcurrentLinkedQueue<String> keyQueue;

    @Override
    public Set<String> generateKeyBatch(int batchSize) {
        // Generate keys of batchSize and add it to keyQueue
        keyQueue = new ConcurrentLinkedQueue<>();
        System.out.println("Generating keys...");
        Set<String> keys = new HashSet<>();
        while (keys.size() < batchSize) {
            String key = UUID.randomUUID().toString().replace("-", "").substring(0, 7); // 7-character key
            keys.add(key);
        }
        keyQueue.addAll(keys);
        return null;
    }

    @Override
    public String getKey() {
        return keyQueue.poll();
    }
}
