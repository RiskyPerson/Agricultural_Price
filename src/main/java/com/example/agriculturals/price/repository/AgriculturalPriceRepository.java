package com.example.agriculturals.price.repository;

import com.example.agriculturals.price.domain.Agricultural;
import com.example.agriculturals.price.domain.AgriculturalPrice;
import com.example.agriculturals.price.domain.AgriculturalPriceKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AgriculturalPriceRepository extends JpaRepository<AgriculturalPrice, AgriculturalPriceKey> {
    @Query("SELECT ap FROM AgriculturalPrice ap JOIN FETCH ap.market JOIN FETCH ap.agricultural")
    List<AgriculturalPrice> findAllWithMarketAndAgricultural();
    @Query("SELECT ap FROM AgriculturalPrice ap JOIN FETCH ap.market JOIN FETCH ap.agricultural WHERE DATE(ap.agriculturalPriceKey.updateDate) = DATE(?1)")
    Optional<List<AgriculturalPrice>> findByUpdateDate(LocalDate updateDate);
    @Modifying
    @Query("UPDATE AgriculturalPrice ap SET ap.price = :price WHERE ap.market.name = :market AND ap.agricultural.product =  :product")
    void updateAgriculturalPriceBy(@Param("market") String market,@Param("product") String product,@Param("price")String price);
    @Modifying
    int deleteAgriculturalPriceByAgricultural(Agricultural agricultural);
    @Modifying
    int deleteByPrice(String price);

    @Query("SELECT ap FROM AgriculturalPrice ap JOIN FETCH ap.market JOIN FETCH ap.agricultural WHERE ap.market.name = :market AND ap.agricultural.product = :product")
    AgriculturalPrice findByMarketAndProduct(@Param("market") String market,@Param("product")String product);


}
