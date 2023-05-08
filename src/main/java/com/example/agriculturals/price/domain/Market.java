package com.example.agriculturals.price.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Market {
    @Id
    private String id;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String name;
//    @OneToMany(mappedBy = "market", fetch = FetchType.LAZY)
//    private List<AgriculturalPrice> agriculturalPrices;
}
