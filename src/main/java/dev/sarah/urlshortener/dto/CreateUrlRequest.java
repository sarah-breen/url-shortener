package dev.sarah.urlshortener.dto;

import lombok.Data;

@Data
public class CreateUrlRequest {
    private String url;
}