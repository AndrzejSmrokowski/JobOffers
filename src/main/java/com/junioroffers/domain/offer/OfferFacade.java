package com.junioroffers.domain.offer;

import com.junioroffers.domain.offer.dto.OfferRequestDto;
import com.junioroffers.domain.offer.dto.OfferResponseDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OfferFacade {

    private final OfferRepository offerRepository;

    public OfferResponseDto findOfferById(String offerId) {
        Offer offerById = offerRepository.findById(offerId);
        return OfferResponseDto.builder()
                .companyName(offerById.companyName())
                .salary(offerById.salary())
                .position(offerById.position())
                .url(offerById.url())
                .id(offerById.id())
                .build();
    }
    public OfferResponseDto saveOffer(OfferRequestDto offerRequestDto) {
        Offer offer = offerRepository.save(Offer.builder()
                .companyName(offerRequestDto.companyName())
                .position(offerRequestDto.position())
                .salary(offerRequestDto.salary())
                .url(offerRequestDto.url())
                .build());
        return OfferResponseDto.builder()
                .companyName(offer.companyName())
                .position(offer.position())
                .salary(offer.salary())
                .url(offer.url())
                .id(offer.id())
                .build();
    }
}
