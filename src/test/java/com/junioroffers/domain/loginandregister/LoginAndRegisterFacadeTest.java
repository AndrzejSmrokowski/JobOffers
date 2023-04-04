package com.junioroffers.domain.loginandregister;

import com.junioroffers.domain.loginandregister.dto.RegisterUserDto;
import com.junioroffers.domain.loginandregister.dto.RegistrationResultDto;
import com.junioroffers.domain.loginandregister.dto.UserDto;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertAll;

import static org.assertj.core.api.Assertions.assertThat;

class LoginAndRegisterFacadeTest {
    LoginAndRegisterFacade loginAndRegisterFacade = new LoginAndRegisterFacade(new InMemoryLoginAndRegisterRepository());

    @Test
    void shouldRegisterUser() {
        //given
        RegisterUserDto registerUser = new RegisterUserDto("username", "password");
        //when
        RegistrationResultDto registrationResult = loginAndRegisterFacade.registerUser(registerUser);
        //then
        assertAll(
                () ->  assertThat(registrationResult.created()).isTrue(),
                () ->  assertThat(registrationResult.username()).isEqualTo("username")
        );
    }

    @Test
    void ShouldFindUserByUsername() {
        //given
        RegisterUserDto registerUser = new RegisterUserDto("username", "password");
        RegistrationResultDto registered = loginAndRegisterFacade.registerUser(registerUser);
        //when
        UserDto userByName = loginAndRegisterFacade.findUserByUsername("username");
        //then
        assertThat(userByName).isEqualTo(new UserDto(registered.id(), "password", "username"));
    }
    @Test
    public void shouldThrowExceptionWhenUserNotFound() {
        // given
        String username = "someUser";

        // when
        Throwable thrown = catchThrowable(() -> loginAndRegisterFacade.findUserByUsername(username));

        // then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("User not found");
    }
}