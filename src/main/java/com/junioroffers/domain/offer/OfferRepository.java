package com.junioroffers.domain.offer;

public interface OfferRepository {

    Offer save(Offer offer);

    Offer findById(String id);
}
