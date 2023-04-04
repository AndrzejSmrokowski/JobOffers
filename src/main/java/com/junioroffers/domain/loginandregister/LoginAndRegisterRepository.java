package com.junioroffers.domain.loginandregister;

import java.util.Optional;

public interface LoginAndRegisterRepository {
    User save(User user);
    Optional<User> findUserByUsername(String userId);
}
