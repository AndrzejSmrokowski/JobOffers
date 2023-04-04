package com.junioroffers.domain.loginandregister;

import com.junioroffers.domain.loginandregister.dto.RegistrationResultDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LoginAndRegisterFacadeTest {
    @Test
    void shouldRegisterUser() {
        //given
        LoginAndRegisterFacade loginAndRegisterFacade = new LoginAndRegisterFacade(new InMemoryLoginAndRegisterRepository());
        //when
        RegistrationResultDto registrationResult = loginAndRegisterFacade.registerUser(new RegisterUserDto("username", "password"));
        //then
        assertThat(registrationResult.created()).isTrue();
    }
}