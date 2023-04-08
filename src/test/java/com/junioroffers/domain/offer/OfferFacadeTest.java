package com.junioroffers.domain.offer;

import com.junioroffers.domain.offer.dto.OfferRequestDto;
import com.junioroffers.domain.offer.dto.OfferResponseDto;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class OfferFacadeTest {

    OfferFacade offerFacade = new OfferFacadeTestConfiguration().offerFacadeForTests();

    @Test
    void shouldFindOfferByIdWhenOfferWasSaved() {
        //given
        OfferResponseDto offerResponseDto = offerFacade.saveOffer(new OfferRequestDto("name", "100", "example.com", "position"));
        //when
        OfferResponseDto offerById = offerFacade.findOfferById(offerResponseDto.id());
        //then
        assertThat(offerById).isEqualTo(offerResponseDto);
    }

    @Test
    void shouldFetchFromJobsFromRemoteAndSaveAllOffersWhenRepositoryIsEmpty() {
        //given
        assertThat(offerFacade.findAllOffers()).isEmpty();
        //when
        List<OfferResponseDto> result = offerFacade.fetchAllOffersAndSaveAllIfNotExists();
        //then
        assertThat(result).hasSize(6);
    }
}