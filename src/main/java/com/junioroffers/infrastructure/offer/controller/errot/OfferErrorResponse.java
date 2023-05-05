package com.junioroffers.infrastructure.offer.controller.errot;

import org.springframework.http.HttpStatus;

public record OfferErrorResponse(
        String message,
        HttpStatus status) {
}
