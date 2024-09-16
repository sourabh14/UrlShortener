package com.example.UrlShortener.url.util;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlUtils {

    public static String getDomainFromUrl(String urlString) {
        try {
            URL url = new URL(urlString);
            return url.getHost();
        } catch (MalformedURLException e) {
            // Handle invalid URL format
            return "";
        }
    }
}
