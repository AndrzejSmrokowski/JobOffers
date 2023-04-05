package com.junioroffers.domain.offer;

import com.junioroffers.domain.offer.dto.OfferRequestDto;
import com.junioroffers.domain.offer.dto.OfferResponseDto;
import lombok.AllArgsConstructor;

import java.util.*;

@AllArgsConstructor
public class OfferFacade {

    private final OfferRepository offerRepository;

    public OfferResponseDto findOfferById(String offerId) {
        Offer offerById = offerRepository.findById(offerId);
        return OfferMapper.mapFromOfferToOfferDto(offerById);
    }
    public OfferResponseDto saveOffer(OfferRequestDto offerRequestDto) {
        Offer offer = offerRepository.save(OfferMapper.mapFromOfferDtoToOffer(offerRequestDto));
        return OfferMapper.mapFromOfferToOfferDto(offer);
    }

    public List<OfferResponseDto> findAllOffers() {
        return offerRepository.findAll()
                .stream()
                .map(OfferMapper::mapFromOfferToOfferDto)
                .toList();
    }
}
