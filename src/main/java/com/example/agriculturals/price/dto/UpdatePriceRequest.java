package com.example.agriculturals.price.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePriceRequest {
    private String product;
    private String marketName;
    private double minPrice;
    private double maxPrice;
}
