package com.junioroffers.domain.offer.dto;

public record OfferRequestDto(
        String companyName,
        String salary,
        String url,
        String position
) {
}
