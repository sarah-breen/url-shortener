package dev.sarah.urlshortener.dto;

import dev.sarah.urlshortener.model.UrlEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UrlResponse {
    private Long id;
    private String url;
    private String shortCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long accessCount;

    //Factory method to convert Entity to DTO
    public static UrlResponse fromEntity(UrlEntity entity) {

        return new UrlResponse(
                entity.getId(),
                entity.getUrl(),
                entity.getShortCode(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getAccessCount()
        );
    }
}