package com.junioroffers.domain.loginandregister;

import com.junioroffers.domain.loginandregister.dto.RegistrationResultDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginAndRegisterFacade {
    private final LoginAndRegisterRepository repository;

    public RegistrationResultDto registerUser(RegisterUserDto registerUserDto) {
        User user = User.builder()
                .username(registerUserDto.username())
                .password(registerUserDto.password()).
                build();
        User savedUser = repository.save(user);
    return new RegistrationResultDto(savedUser.userId(), true, savedUser.username());
    }
}
