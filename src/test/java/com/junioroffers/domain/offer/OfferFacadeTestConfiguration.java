package com.junioroffers.domain.offer;

import com.junioroffers.domain.offer.dto.JobOfferResponse;

import java.util.List;

public class OfferFacadeTestConfiguration {

    private final InMemoryFetcherTestImpl inMemoryFetcherTest;
    private final InMemoryOfferRepository offerRepository;

    OfferFacadeTestConfiguration() {
        this.inMemoryFetcherTest = new InMemoryFetcherTestImpl(
                List.of(
                        new JobOfferResponse("title1", "id", "1000", "1"),
                        new JobOfferResponse("title2", "id", "1000", "2"),
                        new JobOfferResponse("title3", "id", "1000", "3"),
                        new JobOfferResponse("title4", "id", "1000", "4"),
                        new JobOfferResponse("title5", "id", "1000", "5"),
                        new JobOfferResponse("title6", "id", "1000", "6")
                )
        );
        this.offerRepository = new InMemoryOfferRepository();
    }

    OfferFacadeTestConfiguration(List<JobOfferResponse> remoteClientOffers) {
        this.inMemoryFetcherTest = new InMemoryFetcherTestImpl(remoteClientOffers);
        this.offerRepository = new InMemoryOfferRepository();
    }

    OfferFacade offerFacadeForTests() {
        return new OfferFacade(offerRepository, new OfferService(offerRepository, inMemoryFetcherTest));
    }
}
