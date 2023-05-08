package com.example.agriculturals.price.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yaml.snakeyaml.error.Mark;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgriculturalPrice {
    @EmbeddedId
    private AgriculturalPriceKey agriculturalPriceKey;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("marketId")
    @JoinColumn(name = "market_id")
    private Market market;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("agriculturalId")
    @JoinColumn(name = "agricultural_id")
    private Agricultural agricultural;
    @Column(columnDefinition = "NVARCHAR(100)")
    private String price;
}
