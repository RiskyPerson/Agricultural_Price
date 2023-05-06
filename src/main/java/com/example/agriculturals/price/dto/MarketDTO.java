package com.example.agriculturals.price.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketDTO {
    @JsonProperty("brand1")
    private String id;
    @JsonProperty("brand")
    private String name;
}
