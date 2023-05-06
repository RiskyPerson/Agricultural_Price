package com.example.agriculturals.price.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class AgriculturalPriceKey implements Serializable {
    private Long agriculturalId;
    private String marketId;
}
