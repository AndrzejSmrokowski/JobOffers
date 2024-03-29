package com.junioroffers.domain.offer;

import com.junioroffers.domain.offer.dto.JobOfferResponse;
import com.junioroffers.domain.offer.dto.OfferRequestDto;
import com.junioroffers.domain.offer.dto.OfferResponseDto;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DuplicateKeyException;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

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
                response.get(0).offerUrl(),
                response.get(1).offerUrl()
        )).containsExactlyInAnyOrder("https://someurl.pl/5", "https://someother.pl/6");

    }

    //should_save_4_offers_when_there_are_no_offers_in_database
    @Test
    void shouldSave4OffersWhenThereAreNoOffersInDatabase() {
        //given
        assertThat(offerFacade.findAllOffers()).isEmpty();
        //when
        offerFacade.saveOffer(new OfferRequestDto("name1", "position1", "100", "offerUrl"));
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
        assertThat(offerFacade.findAllOffers()).isEmpty();
        // when
        Throwable thrown = catchThrowable(() -> offerFacade.findOfferById("100"));
        // then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(OfferNotFoundException.class)
                .hasMessage("Offer with id 100 not found");

    }

    @Test
    public void shouldThrowDuplicateKeyExceptionWhenOfferUrlExists() {
        // given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration(List.of()).offerFacadeForTests();
        OfferResponseDto offerResponseDto = offerFacade.saveOffer(new OfferRequestDto("id", "asds", "asdasd", "hello.pl"));
        String savedId = offerResponseDto.id();
        assertThat(offerFacade.findOfferById(savedId).id()).isEqualTo(savedId);
        // when
        Throwable thrown = catchThrowable(() -> offerFacade.saveOffer(
                new OfferRequestDto("cx", "vc", "xcv", "hello.pl")));

        // then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(DuplicateKeyException.class)
                .hasMessage("Offer with offerUrl [hello.pl] already exists");
    }

}