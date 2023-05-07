package com.junioroffers.domain.offer;

import com.junioroffers.domain.offer.dto.JobOfferResponse;
import com.junioroffers.domain.offer.dto.OfferRequestDto;
import com.junioroffers.domain.offer.dto.OfferResponseDto;


public class OfferMapper {

    public static Object mapToExpected(Object offer) {
        if (offer instanceof Offer) {
            return mapFromOfferToOfferDto((Offer) offer);
        } else if (offer instanceof OfferRequestDto) {
            return mapFromOfferDtoToOffer((OfferRequestDto) offer);
        } else if (offer instanceof JobOfferResponse) {
            return mapFromJobOfferResponseToOffer((JobOfferResponse) offer);
        }
            return null;
    }

    private static Offer mapFromJobOfferResponseToOffer(JobOfferResponse offer) {
        return Offer.builder()
                .offerUrl(offer.offerUrl())
                .salary(offer.salary())
                .position(offer.title())
                .companyName(offer.company())
                .build();
    }

    private static OfferResponseDto mapFromOfferToOfferDto(Offer offer) {
        return OfferResponseDto.builder()
                .companyName(offer.companyName())
                .position(offer.position())
                .salary(offer.salary())
                .offerUrl(offer.offerUrl())
                .id(offer.id())
                .build();
    }

    private static Offer mapFromOfferDtoToOffer(OfferRequestDto offerDto) {
        return Offer.builder()
                .companyName(offerDto.companyName())
                .position(offerDto.position())
                .salary(offerDto.salary())
                .offerUrl(offerDto.offerUrl())
                .build();
    }
}
