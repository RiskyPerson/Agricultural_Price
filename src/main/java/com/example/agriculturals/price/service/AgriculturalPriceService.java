package com.example.agriculturals.price.service;

import com.example.agriculturals.price.domain.Agricultural;
import com.example.agriculturals.price.domain.AgriculturalPrice;
import com.example.agriculturals.price.domain.Market;
import com.example.agriculturals.price.dto.AgriculturalPriceByDay;
import com.example.agriculturals.price.dto.AgriculturalPriceDTO;
import com.example.agriculturals.price.dto.PriceByDayResponse;
import com.example.agriculturals.price.mapstruct.MapStructMapper;
import com.example.agriculturals.price.repository.AgriculturalPriceRepository;
import com.example.agriculturals.price.repository.AgriculturalRepository;
import com.example.agriculturals.price.repository.MarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class AgriculturalPriceService {
    private final AgriculturalRepository agriculturalRepository;
    private final MarketRepository marketRepository;
    private final AgriculturalPriceRepository agriculturalPriceRepository;
    private final MapStructMapper mapStructMapper;
    public List<Agricultural> getAllAgricultural(){
        return agriculturalRepository.findAll();
    }
    public List<Market> getAllMarket(){
        return marketRepository.findAll();
    }
    public Agricultural getAgricultural(Long id){
        return agriculturalRepository.findById(id).orElseThrow();
    }
    public Market getMarket(String id){
        return marketRepository.findById(id).orElseThrow();
    }
    public Agricultural addAgricultural(Agricultural agricultural){
        return agriculturalRepository.save(agricultural);
    }
    public Market addMarket(Market market){
        return marketRepository.save(market);
    }
    public AgriculturalPrice addAgriculturalPrice(AgriculturalPrice agriculturalPrice){
        return agriculturalPriceRepository.save(agriculturalPrice);
    }
    public List<AgriculturalPrice> getAllWithMarketAndAgricultural(){
        return agriculturalPriceRepository.findAllWithMarketAndAgricultural();
    }
    public PriceByDayResponse getPriceByDay(LocalDate date){
        List<AgriculturalPrice> agriculturalPriceList = agriculturalPriceRepository.findByUpdateDate(date).orElseThrow();
        List<AgriculturalPriceDTO> agriculturalPriceDTOList = mapStructMapper.agriculturalPricesToDTOs(agriculturalPriceList);
        AgriculturalPriceByDay agriculturalPriceByDay = new AgriculturalPriceByDay();
        agriculturalPriceByDay.setAgriculturalPriceDTOs(agriculturalPriceDTOList);
        StringBuilder dayWantToGet = new StringBuilder();
        dayWantToGet.append(date.getYear());
        if(date.getMonthValue() < 10){
            dayWantToGet.append("0" +date.getMonthValue());
        } else{
            dayWantToGet.append(date.getMonthValue());
        }

        if(date.getDayOfMonth() < 10){
            dayWantToGet.append("0"+date.getDayOfMonth());
        } else{
            dayWantToGet.append(date.getDayOfMonth());
        }
        agriculturalPriceByDay.setDate(dayWantToGet.toString());
        agriculturalPriceByDay.setUpdateTime(agriculturalPriceList.get(0).getAgriculturalPriceKey().getUpdateDate());
        return new PriceByDayResponse(agriculturalPriceByDay);
    }
    public List<AgriculturalPriceDTO> getAllAgriculturalPrice(){
        List<AgriculturalPrice> agriculturalPriceList = agriculturalPriceRepository.findAll();
        return mapStructMapper.agriculturalPricesToDTOs(agriculturalPriceList);
    }

}
