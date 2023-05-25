package com.example.agriculturals.price.controller;

import com.example.agriculturals.price.domain.Agricultural;
import com.example.agriculturals.price.domain.AgriculturalPrice;
import com.example.agriculturals.price.domain.Market;
import com.example.agriculturals.price.dto.AgriculturalPriceDTO;
import com.example.agriculturals.price.dto.PriceByDayResponse;
import com.example.agriculturals.price.dto.UpdatePriceRequest;
import com.example.agriculturals.price.service.AgriculturalPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AgriculturalController {
    private final AgriculturalPriceService agriculturalPriceService;

    @GetMapping("/agricultural")
    public ResponseEntity<List<Agricultural>> getAllAgricultural() {
        return ResponseEntity.ok(agriculturalPriceService.getAllAgricultural());
    }

    @GetMapping("/markets")
    public ResponseEntity<List<Market>> getAllMarket() {
        return ResponseEntity.ok(agriculturalPriceService.getAllMarket());
    }

    @PostMapping("/add-agricultural")
    public ResponseEntity<Agricultural> addAgricultural(@RequestBody Agricultural agricultural) {
        return ResponseEntity.ok(agriculturalPriceService.addAgricultural(agricultural));
    }

    @PostMapping("/add-agricultural-price")
    public ResponseEntity<AgriculturalPrice> addAgriculturalPrice(@RequestBody AgriculturalPrice agriculturalPrice) {
        return ResponseEntity.ok(agriculturalPriceService.addAgriculturalPrice(agriculturalPrice));
    }

    @PostMapping("/add-market")
    public ResponseEntity<Market> addMarket(@RequestBody Market market) {
        return ResponseEntity.ok(agriculturalPriceService.addMarket(market));
    }

    @GetMapping("/agricultural/{id}")
    public ResponseEntity<Agricultural> getAgricultural(@PathVariable @RequestBody Long id) {
        return ResponseEntity.ok(agriculturalPriceService.getAgricultural(id));
    }

    @GetMapping("/agricultural-price/{date}")
    public ResponseEntity<PriceByDayResponse> getAgriculturalPriceByDay(@PathVariable @RequestBody LocalDate date) {
        return ResponseEntity.ok(agriculturalPriceService.getPriceByDay(date));
    }

    @GetMapping("/agricultural-price")
    public ResponseEntity<List<AgriculturalPriceDTO>> getAllWithMarketAndAgricultural() {
        return ResponseEntity.ok(agriculturalPriceService.getAllWithMarketAndAgricultural());
    }

    @GetMapping("/agricultural-price-dto")
    public ResponseEntity<List<AgriculturalPriceDTO>> getAllAgriculturalPrice() {
        return ResponseEntity.ok(agriculturalPriceService.getAllAgriculturalPrice());
    }

    @PutMapping("/agricultural-price/update")
    public ResponseEntity<AgriculturalPriceDTO> updateAgriculturalPrice(@RequestBody UpdatePriceRequest request) {
        return ResponseEntity.ok(agriculturalPriceService.updatePrice(request));
    }
    @GetMapping("/random-price")
    public ResponseEntity<List<String>> randomPrice(){
        return ResponseEntity.ok(agriculturalPriceService.randomPriceV1());
    }
    @GetMapping("/random-price/id/{id}")
    public ResponseEntity<List<String>> randomPriceOfProduct(@PathVariable @RequestParam Long id){
        return ResponseEntity.ok(agriculturalPriceService.randomPriceSpecificProduct(id));
    }
    @GetMapping("/random-price/type/{type}")
    public ResponseEntity<List<String>> randomPriceByType(@PathVariable @RequestParam String type){
        return ResponseEntity.ok(agriculturalPriceService.randomPriceByProductType(type));
    }

}

