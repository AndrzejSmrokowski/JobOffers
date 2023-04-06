package com.junioroffers.domain.loginandregister;

import com.junioroffers.domain.loginandregister.dto.RegisterUserDto;
import com.junioroffers.domain.loginandregister.dto.RegistrationResultDto;
import com.junioroffers.domain.loginandregister.dto.UserDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginAndRegisterFacade {
    public static final String USER_NOT_FOUND = "User not found";
    private final LoginAndRegisterRepository repository;

    public UserDto findUserByUsername(String username) {
        return repository.findUserByUsername(username)
                .map(user -> new UserDto(user.userId(), user.password(), user.username()))
                .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));
    }

    public RegistrationResultDto registerUser(RegisterUserDto registerUserDto) {
        User user = User.builder()
                .username(registerUserDto.username())
                .password(registerUserDto.password()).
                build();
        User savedUser = repository.save(user);
        return new RegistrationResultDto(savedUser.userId(), true, savedUser.username());
    }
}
