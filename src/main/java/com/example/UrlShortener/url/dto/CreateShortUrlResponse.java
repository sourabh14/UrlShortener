package com.example.UrlShortener.url.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateShortUrlResponse {
    private String  shortenedUrl;
    private String  url;
}
