package com.example.UrlShortener.url.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateShortUrlRequest {
    @Pattern(regexp = "^(https?://[^\\s/$.?#].[^\\s]*)$", message = "Invalid URL format")
    private String url;
}
