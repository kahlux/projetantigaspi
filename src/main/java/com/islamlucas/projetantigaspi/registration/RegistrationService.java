package com.islamlucas.projetantigaspi.registration;

import com.islamlucas.projetantigaspi.users.User;
import com.islamlucas.projetantigaspi.users.UserRole;
import com.islamlucas.projetantigaspi.users.UserService;
import com.islamlucas.projetantigaspi.utils.exceptions.NotValidEmailException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final UserService userService;
    public void register(RegistrationRequest request) throws NotValidEmailException {
        boolean validEmail = emailValidator.test(request.getEmail());
        if(!validEmail) {
            throw new NotValidEmailException("Email non valide !");
        }

        userService.signUpUser(new User(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                UserRole.USER
        ));
    }
}
