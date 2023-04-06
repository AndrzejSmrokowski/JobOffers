package com.junioroffers.domain.offer;

import java.util.*;

public class InMemoryOfferRepository implements OfferRepository {

    Map<String, Offer> database = new HashMap<>();

    @Override
    public Offer save(Offer offer) {
        String offerId = UUID.randomUUID().toString();
        Offer savedOffer = Offer.builder()
                .id(offerId)
                .companyName(offer.companyName())
                .position(offer.position())
                .salary(offer.salary())
                .url(offer.url())
                .build();
        database.put(offerId, savedOffer);
        return savedOffer;
    }

    @Override
    public Offer findById(String id) {
        return database.get(id);
    }

    @Override
    public List<Offer> findAll() {
        return new ArrayList<>(database.values());
    }
}
