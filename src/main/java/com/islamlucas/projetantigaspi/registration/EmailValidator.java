package com.islamlucas.projetantigaspi.registration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Predicate;

@Configuration
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        //TODO: regex check email
        return true;
    }
}
