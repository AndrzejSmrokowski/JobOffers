package com.junioroffers.domain.offer;

import com.junioroffers.domain.offer.dto.OfferRequestDto;
import com.junioroffers.domain.offer.dto.OfferResponseDto;
import lombok.AllArgsConstructor;

import java.util.*;

@AllArgsConstructor
public class OfferFacade {

    private final OfferRepository offerRepository;
    private final OfferService offerService;
    public OfferResponseDto findOfferById(String offerId) {
        Offer offerById = offerRepository.findById(offerId);
        return (OfferResponseDto) OfferMapper.mapToExpected(offerById);
    }
    public OfferResponseDto saveOffer(OfferRequestDto offerRequestDto) {
        Offer offer = offerRepository.save((Offer) OfferMapper.mapToExpected(offerRequestDto));
        return (OfferResponseDto) OfferMapper.mapToExpected(offer);
    }

    public List<OfferResponseDto> findAllOffers() {
        return offerRepository.findAll()
                .stream()
                .map(OfferMapper::mapToExpected)
                .map(obj -> (OfferResponseDto) obj)
                .toList();
    }

    public List<OfferResponseDto> fetchAllOffersAndSaveAllIfNotExists() {
        return offerService.fetchAllOffersAndSaveAllIfNotExists()
                .stream()
                .map(OfferMapper::mapToExpected)
                .map(offer -> (OfferResponseDto) offer)
                .toList();
    }
}
