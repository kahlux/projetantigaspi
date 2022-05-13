package com.islamlucas.projetantigaspi.registration;

import com.islamlucas.projetantigaspi.users.User;
import com.islamlucas.projetantigaspi.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
public class RegistrationValidator implements Validator {

    private final UserRepository userRepository;

    private final EmailValidator emailValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println(target);
        User user = (User) target;


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");

        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }

        if(!emailValidator.test(user.getEmail())) {
            errors.rejectValue("email", "Malformed.email");
        }

        if(user.getEmail().length() > 64) {
            errors.rejectValue("email", "Size.userForm");
        }

        if (user.getPassword().length() < 8) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if(user.getPassword().length() > 64) {
            errors.rejectValue("password", "Size.userForm");
        }

        if(user.getFirstName().length() > 64) {
            errors.rejectValue("firstName", "Size.userForm");
        }

        if(user.getLastName().length() > 64) {
            errors.rejectValue("lastName", "Size.userForm");
        }
    }
}
