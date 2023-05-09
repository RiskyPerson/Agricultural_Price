package com.example.agriculturals.price.dto;

import com.example.agriculturals.price.domain.Agricultural;
import com.example.agriculturals.price.domain.Market;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AgriculturalPriceDTO {
    @JsonProperty("type")
    private String type;
    @JsonProperty("product")
    private String product;
    @JsonProperty("unit")
    private String unit;
    @JsonProperty("brand1")
    private String marketId;
    @JsonProperty("brand")
    private String marketName;

//    private MarketDTO marketDTO;
//    private AgriculturalDTO agriculturalDTO;
    @JsonProperty("price")
    private String price;
    @JsonProperty("updated")
    private LocalDateTime updateDate;
}
