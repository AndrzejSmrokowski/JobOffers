package com.junioroffers.domain.loginandregister;

import lombok.Builder;

@Builder
public
record User(
        String userId,
        String password,
        String username) {

}
