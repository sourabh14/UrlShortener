package com.example.UrlShortener.url.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUrlShortLinkRequest {
    @NotEmpty
    private String url;
    private String domain;
}
