package com.islamlucas.projetantigaspi.registration;

import org.springframework.context.annotation.Configuration;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Configuration
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);

        return pattern.asMatchPredicate().test(email);
    }
}
