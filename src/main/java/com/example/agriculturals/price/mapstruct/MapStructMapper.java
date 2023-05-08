package com.example.agriculturals.price.mapstruct;

import com.example.agriculturals.price.domain.Agricultural;
import com.example.agriculturals.price.domain.AgriculturalPrice;
import com.example.agriculturals.price.domain.Market;
import com.example.agriculturals.price.dto.AgriculturalDTO;
import com.example.agriculturals.price.dto.AgriculturalPriceDTO;
import com.example.agriculturals.price.dto.MarketDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    @Mapping(target = "unit", expression = "java(agriculturalPrice.getAgricultural().getUnit())")
    @Mapping(target = "product", expression = "java(agriculturalPrice.getAgricultural().getProduct())")
    @Mapping(target = "type", expression = "java(agriculturalPrice.getAgricultural().getType())")
    @Mapping(target = "marketId", expression = "java(agriculturalPrice.getMarket().getId())")
    @Mapping(target = "marketName", expression = "java(agriculturalPrice.getMarket().getName())")
    @Mapping(target = "updateDate", expression = "java(agriculturalPrice.getAgriculturalPriceKey().getUpdateDate())")
    AgriculturalPriceDTO agriculturalPriceToDTO(AgriculturalPrice agriculturalPrice);
    AgriculturalPrice DTOToAgriculturalPrice(AgriculturalPriceDTO agriculturalPriceDTO);
    List<AgriculturalPriceDTO> agriculturalPricesToDTOs(List<AgriculturalPrice> agriculturalPriceList);
    List<AgriculturalPrice> DTOsToAgriculturalPrice(List<AgriculturalPriceDTO> agriculturalPriceDTOList);
    MarketDTO marketToDTO(Market market);
    Market DTOToMarket(MarketDTO marketDTO);
    Agricultural DTOToAgricultural(AgriculturalDTO agriculturalDTO);
    AgriculturalDTO agriculturalToDTO(Agricultural agricultural);
}
