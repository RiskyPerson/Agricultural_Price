package com.example.agriculturals.price.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgriculturalPriceByDay {
    @JsonProperty("updated")
    private LocalDateTime updateTime;
    @JsonProperty("date")
    private String date;
    @JsonProperty("value")
    private List<AgriculturalPriceDTO> agriculturalPriceDTOs;
}
