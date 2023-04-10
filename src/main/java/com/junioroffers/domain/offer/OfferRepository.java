package com.junioroffers.domain.offer;


import java.util.List;
import java.util.Optional;

public interface OfferRepository {

    Offer save(Offer offer);

    Optional<Offer> findById(String id);

    List<Offer> findAll();

    boolean existsByOfferUrl(String url);

    List<Offer> saveAll(List<Offer> offers);
}
