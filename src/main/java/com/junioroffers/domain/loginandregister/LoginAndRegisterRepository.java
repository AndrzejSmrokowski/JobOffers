package com.junioroffers.domain.loginandregister;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LoginAndRegisterRepository extends MongoRepository<User, String> {
    Optional<User> findUserByUsername(String userId);
}
