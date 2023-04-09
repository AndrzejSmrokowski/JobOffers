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
                        new JobOfferResponse("id", "id", "200", "1"),
                        new JobOfferResponse("12", "id", "200", "2"),
                        new JobOfferResponse("123", "id", "200", "3"),
                        new JobOfferResponse("1234", "id", "200`", "4"),
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

    //should_save_4_offers_when_there_are_no_offers_in_database
    @Test
    void shouldSave4OffersWhenThereAreNoOffersInDatabase() {
        //given
        assertThat(offerFacade.findAllOffers()).isEmpty();
        //when
        offerFacade.saveOffer(new OfferRequestDto("name1", "position1", "100", "url"));
        offerFacade.saveOffer(new OfferRequestDto("name2", "position2", "100", "url2"));
        offerFacade.saveOffer(new OfferRequestDto("name3", "position3", "100", "url3"));
        offerFacade.saveOffer(new OfferRequestDto("name4", "position4", "100", "url4"));
        //then
        assertThat(offerFacade.findAllOffers()).hasSize(4);
    }

    //    should_throw_not_found_exception_when_offer_not_found
    @Test
    void shouldThrowNotFoundExceptionWhenOfferNotFound() {
        //given
    }

}