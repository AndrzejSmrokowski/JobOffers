package com.junioroffers.domain.offer;

import com.junioroffers.domain.offer.dto.OfferRequestDto;
import com.junioroffers.domain.offer.dto.OfferResponseDto;
import lombok.AllArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OfferFacade {

    private final OfferRepository offerRepository;
    private final OfferService offerService;
    public OfferResponseDto findOfferById(String id) {
        return offerRepository.findById(id)
                .map(OfferMapper::mapToExpected)
                .map(obj -> (OfferResponseDto) obj)
                .orElseThrow(() -> new OfferNotFoundException(id));
    }
    public OfferResponseDto saveOffer(OfferRequestDto offerDto) {
        final Offer offer = (Offer) OfferMapper.mapToExpected(offerDto);
        assert offer != null;
        final Offer save = offerRepository.save(offer);
        return (OfferResponseDto) OfferMapper.mapToExpected(save);
    }

    public List<OfferResponseDto> findAllOffers() {
        return offerRepository.findAll()
                .stream()
                .map(OfferMapper::mapToExpected)
                .map(obj -> (OfferResponseDto) obj)
                .collect(Collectors.toList());
    }

    public List<OfferResponseDto> fetchAllOffersAndSaveAllIfNotExists() {
        return offerService.fetchAllOffersAndSaveAllIfNotExists()
                .stream()
                .map(OfferMapper::mapToExpected)
                .map(offer -> (OfferResponseDto) offer)
                .toList();
    }
}
