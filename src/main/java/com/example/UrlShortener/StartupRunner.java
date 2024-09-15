package com.example.UrlShortener;

import com.example.UrlShortener.url.service.KeyService;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class StartupRunner implements ApplicationRunner {
    private final KeyService keyService;
    private static final int batchSize = 1000;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Generate 1000 keys at the time of startup
        keyService.generateKeyBatch(batchSize);
    }
}
