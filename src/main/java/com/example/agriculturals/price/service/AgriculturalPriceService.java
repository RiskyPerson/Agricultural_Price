package com.example.agriculturals.price.service;

import com.example.agriculturals.price.domain.Agricultural;
import com.example.agriculturals.price.domain.AgriculturalPrice;
import com.example.agriculturals.price.domain.Market;
import com.example.agriculturals.price.dto.*;
import com.example.agriculturals.price.mapstruct.MapStructMapper;
import com.example.agriculturals.price.repository.AgriculturalPriceRepository;
import com.example.agriculturals.price.repository.AgriculturalRepository;
import com.example.agriculturals.price.repository.MarketRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        List<AgriculturalPrice> agriculturalPriceList = agriculturalPriceRepository.findAll();
        List<AgriculturalPriceDTO> agriculturalPriceDTOList = mapStructMapper.agriculturalPricesToDTOs(agriculturalPriceList);
        for(AgriculturalPriceDTO agriculturalPriceDTO : agriculturalPriceDTOList){
            agriculturalPriceDTO.setUpdateDate(LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), 0, 0, 0));
        }
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
//        agriculturalPriceByDay.setUpdateTime(agriculturalPriceList.get(0).getAgriculturalPriceKey().getUpdateDate());
        agriculturalPriceByDay.setUpdateTime(LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), 0, 0, 0));
        return new PriceByDayResponse(agriculturalPriceByDay);
    }
    public List<AgriculturalPriceDTO> getAllAgriculturalPrice(){
        List<AgriculturalPrice> agriculturalPriceList = agriculturalPriceRepository.findAll();
        return mapStructMapper.agriculturalPricesToDTOs(agriculturalPriceList);
    }
    @Transactional
    public AgriculturalPriceDTO updatePrice(UpdatePriceRequest request){
        String price;
        if(request.getMaxPrice() == request.getMinPrice()){
            price = Double.toString(request.getMinPrice());
        } else{
            price = Double.toString(request.getMaxPrice()) + " - " + Double.toString(request.getMaxPrice());
        }
        String marketName = request.getMarketName();
        String product = request.getProduct();
        Market market = marketRepository.findByName(marketName).orElseThrow();
        Agricultural agricultural = agriculturalRepository.findByProduct(product).orElseThrow();
        agriculturalPriceRepository.updateAgriculturalPriceByMarketAndAgricultural(agricultural.getId(), market.getId(), price);
        return mapStructMapper.agriculturalPriceToDTO(agriculturalPriceRepository.findByMarketAndProduct(marketName, product).orElseThrow());
    }

}
