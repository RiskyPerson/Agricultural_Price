package com.example.agriculturals.price.repository;

import com.example.agriculturals.price.domain.Agricultural;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgriculturalRepository extends JpaRepository<Agricultural, Long> {

}
