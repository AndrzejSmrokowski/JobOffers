package com.junioroffers.domain.loginandregister;

import com.junioroffers.domain.loginandregister.LoginAndRegisterRepository;
import com.junioroffers.domain.loginandregister.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryLoginAndRegisterRepository implements LoginAndRegisterRepository {

    Map<String, User> database = new HashMap<>();
    @Override
    public User save(User user) {
        String userId = UUID.randomUUID().toString();
        User savedUser = User.builder()
                .userId(userId)
                .username(user.username())
                .password(user.password())
                .build();
        database.put(savedUser.username(), savedUser);
        return savedUser;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return Optional.ofNullable(database.get(username));
    }
}
