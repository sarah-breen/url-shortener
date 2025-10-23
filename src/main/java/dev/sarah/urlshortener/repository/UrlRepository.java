package dev.sarah.urlshortener.repository;

import dev.sarah.urlshortener.model.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {

    //Finds a URL by its short code
    Optional<UrlEntity> findByShortCode(String shortCode);

    //Check if a short code already exists
    boolean existsByShortCode(String shortCode);

}