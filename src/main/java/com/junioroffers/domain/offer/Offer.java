package com.junioroffers.domain.offer;

import lombok.Builder;

@Builder
record Offer(
        String id,
        String companyName,
        String salary,
        String url,
        String position
) {

}
