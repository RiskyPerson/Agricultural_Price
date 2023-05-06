package com.example.agriculturals.price.repository;

import com.example.agriculturals.price.domain.AgriculturalPrice;
import com.example.agriculturals.price.domain.AgriculturalPriceKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AgriculturalPriceRepository extends JpaRepository<AgriculturalPrice, AgriculturalPriceKey> {
    @Query("SELECT ap FROM AgriculturalPrice ap JOIN FETCH ap.market JOIN FETCH ap.agricultural")
    List<AgriculturalPrice> findAllWithMarketAndAgricultural();
    @Query("SELECT ap FROM AgriculturalPrice ap JOIN FETCH ap.market JOIN FETCH ap.agricultural WHERE DATE(ap.updateDate) = DATE(?1)")
    Optional<List<AgriculturalPrice>> findByUpdateDate(LocalDateTime updateDate);

}
