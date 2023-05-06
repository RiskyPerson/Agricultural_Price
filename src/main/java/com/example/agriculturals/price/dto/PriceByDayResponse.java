package com.example.agriculturals.price.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PriceByDayResponse {
    @JsonProperty("agriculturals")
    private AgriculturalPriceByDay agriculturalPriceByDay;

}
