package com.example.agriculturals.price.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@Setter
@Getter
public class AgriculturalPriceKey implements Serializable {
    private Long agriculturalId;
    private String marketId;
    private LocalDateTime updateDate;
}
