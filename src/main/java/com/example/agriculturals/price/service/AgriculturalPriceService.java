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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
    public List<AgriculturalPriceDTO> getAllWithMarketAndAgricultural(){
        return mapStructMapper.agriculturalPricesToDTOs(agriculturalPriceRepository.findAllWithMarketAndAgricultural());
    }
    public PriceByDayResponse getPriceByDay(LocalDate date){
        List<AgriculturalPrice> agriculturalPriceList = agriculturalPriceRepository.findAll();
        List<AgriculturalPriceDTO> agriculturalPriceDTOList = mapStructMapper.agriculturalPricesToDTOs(agriculturalPriceList);
        for(AgriculturalPriceDTO agriculturalPriceDTO : agriculturalPriceDTOList){
            agriculturalPriceDTO.setUpdateDate(LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), 0, 0, 0));
            agriculturalPriceDTO.setPrice(randomPrice(agriculturalPriceDTO.getPrice().trim(), agriculturalPriceDTO.getType().trim()));
        }
        AgriculturalPriceByDay agriculturalPriceByDay = new AgriculturalPriceByDay();
        agriculturalPriceByDay.setAgriculturalPriceDTOs(agriculturalPriceDTOList);
        StringBuilder dayWantToGet = new StringBuilder();
        dayWantToGet.append(date.getYear());
        if(date.getMonthValue() < 10){
            dayWantToGet.append("0").append(date.getMonthValue());
        } else{
            dayWantToGet.append(date.getMonthValue());
        }

        if(date.getDayOfMonth() < 10){
            dayWantToGet.append("0").append(date.getDayOfMonth());
        } else{
            dayWantToGet.append(date.getDayOfMonth());
        }
        agriculturalPriceByDay.setDate(dayWantToGet.toString());
        agriculturalPriceByDay.setUpdateTime(agriculturalPriceList.get(0).getAgriculturalPriceKey().getUpdateDate());
        agriculturalPriceByDay.setUpdateTime(LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), 0, 0, 0));
        return new PriceByDayResponse(agriculturalPriceByDay);
    }
    public List<AgriculturalPriceDTO> getAllAgriculturalPrice(){
        List<AgriculturalPrice> agriculturalPriceList = agriculturalPriceRepository.findAll();
        return mapStructMapper.agriculturalPricesToDTOs(agriculturalPriceList);
    }
    public AgriculturalPriceDTO updatePrice(UpdatePriceRequest request){
        String price;
        if(request.getMaxPrice() == request.getMinPrice()){
            price = Double.toString(request.getMinPrice()/1000) + "00đ" ;
        } else{
            price = Double.toString(request.getMinPrice()/1000) + "00 - " + Double.toString(request.getMaxPrice()/1000) + "00đ";
        }
        String marketName = request.getMarketName();
        String product = request.getProduct();
        Market market = marketRepository.findByName(marketName).orElseThrow();
        Agricultural agricultural = agriculturalRepository.findByProduct(product).orElseThrow();
        agriculturalPriceRepository.updateAgriculturalPriceByMarketAndAgricultural(agricultural.getId(), market.getId(), price);
        return mapStructMapper.agriculturalPriceToDTO(agriculturalPriceRepository.findByMarketAndProduct(marketName, product).orElseThrow());
    }
    public List<String> randomPriceByProductType(String type){
        List<AgriculturalPrice> agriculturalPrices = agriculturalPriceRepository.findByAgricultural_Type(type);
        List<String> result = new ArrayList<>();
        for(AgriculturalPrice agriculturalPrice: agriculturalPrices){
            result.add(randomPrice(agriculturalPrice.getPrice(), agriculturalPrice.getAgricultural().getType()));
        }
        return result;
    }
    public List<String> randomPriceSpecificProduct(Long id){
        AgriculturalPrice agriculturalPrice = agriculturalPriceRepository.findByAgriculturalId(id);
        String price = agriculturalPrice.getPrice();
        String type = agriculturalPrice.getAgricultural().getType();
        List<String> result = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            result.add(randomPrice(price, type));
        }
        return result;
    }
    public List<String> randomPriceV1(){
        List<AgriculturalPrice> agriculturalPrices = agriculturalPriceRepository.findAll();
        List<String> price = new ArrayList<>();
        for(AgriculturalPrice agriculturalPrice: agriculturalPrices){
            price.add(randomPrice(agriculturalPrice.getPrice(), agriculturalPrice.getAgricultural().getType()));
        }

        return price;
    }

    public List<String> findTop10(){
        List<AgriculturalPrice> agriculturalPrices = agriculturalPriceRepository.findTop10();
        List<String> result = new ArrayList<>();
        for(AgriculturalPrice agriculturalPrice: agriculturalPrices){
            result.add(randomPrice(agriculturalPrice.getPrice(), agriculturalPrice.getAgricultural().getType()));
        }
        return result;
    }

    private String randomPrice(String price, String type){
        String result = null;
        Random random = new Random();
        if(price.contains("-") || price.contains("–")) {
            String[] pieces = price.split(" ");
            double minPrice = 0,  maxPrice = 0;
            String maxPriceInString = null;
            switch (type){
                case "Rau củ quả" :
                    minPrice = Double.parseDouble(pieces[0]) + Math.round(Math.random()*3);
                    maxPriceInString = pieces[2].split("đ")[0];
                    maxPrice = Double.parseDouble(maxPriceInString) + Math.round(Math.random()*3);
                    break;
                case "Thủy hải sản" :
                    minPrice = Double.parseDouble(pieces[0]) + Math.round(Math.random()*5 + 5);
                    maxPriceInString = pieces[2].split("đ")[0];
                    maxPrice = Double.parseDouble(maxPriceInString) + Math.round(Math.random()*5 + 5);
                    break;
                case "Thịt":
                    minPrice = Double.parseDouble(pieces[0]) + Math.round(Math.random()*10 + 5);
                    maxPriceInString = pieces[2].split("đ")[0];
                    maxPrice = Double.parseDouble(maxPriceInString) + Math.round(Math.random()*10 + 5);
                    break;
                default:
                    maxPrice = minPrice = 0;
                    break;
            }
            if(minPrice == maxPrice){
                result = Double.toString(minPrice) + "00đ";
            } else{
                result = Double.toString(minPrice) + "00 - " + Double.toString(maxPrice) + "00đ";
            }
        } else{
            String priceInString = price.split("đ")[0];
            double randomPrice  = 0;
            switch (type){
                case "Rau củ quả" :
                    randomPrice = Double.parseDouble(priceInString) + Math.round(Math.random()*3);
                    break;
                case "Thủy hải sản" :
                    randomPrice = Double.parseDouble(priceInString) + Math.round(Math.random()*5 + 5);
                    break;
                case "Thịt":
                    randomPrice = Double.parseDouble(priceInString) + Math.round(Math.random()*10 + 10);
                    break;
                default:
                    randomPrice = 0;
                    break;
            }
            result = Double.toString(randomPrice) + "00đ";
        }
        return result;
    }


}
