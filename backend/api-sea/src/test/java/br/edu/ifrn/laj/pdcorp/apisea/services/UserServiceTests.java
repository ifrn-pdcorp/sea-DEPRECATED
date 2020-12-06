package br.edu.ifrn.laj.pdcorp.apisea.services;


import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiException;
import br.edu.ifrn.laj.pdcorp.apisea.models.User;
import br.edu.ifrn.laj.pdcorp.apisea.models.enums.UserType;
import br.edu.ifrn.laj.pdcorp.apisea.repositories.UserRepository;

import br.edu.ifrn.laj.pdcorp.apisea.validators.user.UserValidationMediator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class UserServiceTests {

    @Mock
    private UserRepository repository;
    @Mock
    private UserValidationMediator validator;
    
    @InjectMocks
    private UserService userService;

    private User getUserOk() {
        User user = new User();
        user.setEmail("email@email.com.br");
        user.setCity("City");
        user.setName("UserName");
        user.setSchool("School");
        user.setPassword("438474838");
        user.setType(UserType.CHEER);
        return user;
    }

    private User getUserWithoutEmail() {
        User user = new User();
        user.setEmail(null);
        user.setCity("City");
        user.setName("UserName");
        user.setSchool("School");
        user.setPassword("438474838");
        user.setType(UserType.CHEER);
        return user;
    }

    @Test
    public void shouldSavedUserSuccessfully() {
        User userforSave = this.getUserOk();
        BDDMockito.given(repository.save(userforSave)).willReturn(userforSave);
        try {
            userService.save(userforSave);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        BDDMockito.then(repository).should().save(userforSave);
    }

    @Test
    public void shouldThrowErrorForAnUserWithDuplicatedEmail() {
        User userforSave = this.getUserWithoutEmail();
        BDDMockito.given(repository.findByEmail(userforSave.getEmail())).willReturn(userforSave);
        Assertions.assertThrows(ApiException.class, () -> userService.save(userforSave));
        BDDMockito.then(repository).should(BDDMockito.never()).save(userforSave);
    }
}
