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
    public Optional<Offer> findById(String id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<Offer> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public boolean existsByOfferUrl(String url) {
        long count = database.values()
                .stream()
                .filter(offer -> offer.url().equals(url))
                .count();
        return count == 1;    }

    @Override
    public List<Offer> saveAll(List<Offer> offers) {
        return offers.stream()
                .map(this::save)
                .toList();
    }
}
