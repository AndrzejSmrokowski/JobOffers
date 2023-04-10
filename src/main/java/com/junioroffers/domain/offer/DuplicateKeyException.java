package com.junioroffers.domain.offer;

public class DuplicateKeyException extends RuntimeException{
    public DuplicateKeyException(String offerUrl) {
        super(String.format("Offer with offerUrl [%s] already exists", offerUrl));
    }
}
