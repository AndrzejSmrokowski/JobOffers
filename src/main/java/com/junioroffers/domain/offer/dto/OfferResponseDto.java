package com.junioroffers.domain.offer.dto;

import lombok.Builder;

@Builder
public record OfferResponseDto(
        String id,
        String companyName,
        String salary,
        String url,
        String position
) {
}
