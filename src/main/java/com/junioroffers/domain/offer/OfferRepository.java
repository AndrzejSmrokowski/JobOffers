package com.junioroffers.domain.offer;


import java.util.List;

public interface OfferRepository {

    Offer save(Offer offer);

    Offer findById(String id);

    List<Offer> findAll();
}
