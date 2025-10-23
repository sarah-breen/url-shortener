package dev.sarah.urlshortener.service;

import dev.sarah.urlshortener.model.UrlEntity;
import dev.sarah.urlshortener.repository.UrlRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_CODE_LENGTH = 6;

    @Transactional
    public UrlEntity createShortUrl(String origiunalUrl) {
        String shortCode = generateUniqueShortCode();

        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setUrl((origiunalUrl));
        urlEntity.setShortCode(shortCode);
        urlEntity.setAccessCount(0L);

        return urlRepository.save(urlEntity);
    }

    //GET URL by short code
    public UrlEntity getByShortCode(String shortCode) {
        return urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new IllegalArgumentException("Short code not found"));
    }

    //Update an existing URL
    @Transactional
    public UrlEntity updateUrl(String shortCode, String newUrl) {
        UrlEntity urlEntity = getByShortCode(shortCode);
        urlEntity.setUrl(newUrl);
        return urlRepository.save(urlEntity);
    }

    //Delete a URL
    @Transactional
    public void deleteUrl(String shortCode) {
        UrlEntity urlEntity = getByShortCode(shortCode);
        urlRepository.delete(urlEntity);
    }

    //Increment access count
    @Transactional
    public void incrementAccessCount(String shortCode) {
        UrlEntity urlEntity = getByShortCode(shortCode);
        urlEntity.setAccessCount(urlEntity.getAccessCount() + 1);
        urlRepository.save(urlEntity);
    }

    //Generate unique short code
    private String generateUniqueShortCode() {
        String shortCode;
        do {
            shortCode = generateRandomCode();
        } while (urlRepository.existsByShortCode(shortCode));
        return shortCode;
    }

    //Generate random code
    private String generateRandomCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(SHORT_CODE_LENGTH);
        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}
