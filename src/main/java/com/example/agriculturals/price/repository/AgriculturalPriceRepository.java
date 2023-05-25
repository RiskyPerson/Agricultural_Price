package com.example.agriculturals.price.repository;

import com.example.agriculturals.price.domain.Agricultural;
import com.example.agriculturals.price.domain.AgriculturalPrice;
import com.example.agriculturals.price.domain.AgriculturalPriceKey;
import com.example.agriculturals.price.domain.Market;
import jakarta.transaction.Transactional;
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
    @Transactional
    @Query("Update AgriculturalPrice ap SET ap.price = :price WHERE ap.agriculturalPriceKey.marketId = :marketId AND ap.agriculturalPriceKey.agriculturalId = :agriculturalId")
    void updateAgriculturalPriceByMarketAndAgricultural(Long agriculturalId, String marketId, String price);
    @Modifying
    int deleteAgriculturalPriceByAgricultural(Agricultural agricultural);

    @Query("SELECT ap FROM AgriculturalPrice ap JOIN FETCH ap.market JOIN FETCH ap.agricultural WHERE ap.market.name = :market AND ap.agricultural.product = :product")
    Optional<AgriculturalPrice> findByMarketAndProduct(@Param("market") String market,@Param("product")String product);

    Optional<AgriculturalPrice> findByMarketAndAgricultural(Market market, Agricultural agricultural);

    List<AgriculturalPrice> findByAgricultural_Type(String type);

    AgriculturalPrice findByAgricultural_Id(Long id);
    @Query("SELECT ap FROM AgriculturalPrice  ap  WHERE ap.agriculturalPriceKey.marketId =:id")
    AgriculturalPrice findByAgriculturalId(@Param("id") Long id);
    @Query("SELECT ap FROM AgriculturalPrice ap")
    List<AgriculturalPrice> findTop10();
}
