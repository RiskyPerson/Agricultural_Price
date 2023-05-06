package com.example.agriculturals.price.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Agricultural {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String type;
    private String unit;
    private String product;
//    @OneToMany(mappedBy = "agricultural", fetch = FetchType.LAZY)
//    private List<AgriculturalPrice> agriculturalPrices;
}
