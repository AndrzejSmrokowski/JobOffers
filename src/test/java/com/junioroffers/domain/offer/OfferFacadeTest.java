package com.junioroffers.domain.offer;

import com.junioroffers.domain.offer.dto.JobOfferResponse;
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
    @Test
    void shouldSaveOnly2OffersWhenRepositoryHad4AddedWithUrls() {
        //given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration(
                List.of(
                        new JobOfferResponse("id", "id", "asds", "1"),
                        new JobOfferResponse("assd", "id", "asds", "2"),
                        new JobOfferResponse("asddd", "id", "asds", "3"),
                        new JobOfferResponse("asfd", "id", "asds", "4"),
                        new JobOfferResponse("Junior", "Comarch", "1000", "https://someurl.pl/5"),
                        new JobOfferResponse("Mid", "Finanteq", "2000", "https://someother.pl/6")
                )
        ).offerFacadeForTests();
        offerFacade.saveOffer(new OfferRequestDto("id", "position1", "500", "1"));
        offerFacade.saveOffer(new OfferRequestDto("id", "position2", "300", "2"));
        offerFacade.saveOffer(new OfferRequestDto("id", "position3", "200", "3"));
        offerFacade.saveOffer(new OfferRequestDto("id", "position4", "100", "4"));
        assertThat(offerFacade.findAllOffers()).hasSize(4);
        //when
        List<OfferResponseDto> response = offerFacade.fetchAllOffersAndSaveAllIfNotExists();
        //then
        assertThat(List.of(
                response.get(0).url(),
                response.get(1).url()
        )).containsExactlyInAnyOrder("https://someurl.pl/5", "https://someother.pl/6");

    }
}