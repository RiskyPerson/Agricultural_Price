package com.example.agriculturals.price.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgriculturalDTO {
    @JsonProperty("type")
    private String type;
    @JsonProperty("unit")
    private String unit;
    @JsonProperty("product")
    private String product;
}
