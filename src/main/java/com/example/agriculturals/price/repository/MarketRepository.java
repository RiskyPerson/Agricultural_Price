package com.example.agriculturals.price.repository;

import com.example.agriculturals.price.domain.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarketRepository extends JpaRepository<Market, String> {
    Optional<Market> findByName(String name);

}
