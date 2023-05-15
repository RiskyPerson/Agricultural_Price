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
    @Column(columnDefinition = "NVARCHAR(50)")
    private String type;
    @Column(columnDefinition = "NVARCHAR(50)")
    private String unit;
    @Column(columnDefinition = "NVARCHAR(255) UNIQUE")
    private String product;
//    @OneToMany(mappedBy = "agricultural", fetch = FetchType.LAZY)
//    private List<AgriculturalPrice> agriculturalPrices;
}
