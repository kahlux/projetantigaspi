package com.islamlucas.projetantigaspi.registration;

import com.islamlucas.projetantigaspi.shop.Shop;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
public class ShopValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Shop.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println(target);
        if(target instanceof Shop) {
            Shop shop = (Shop) target;

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "NotEmpty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "NotEmpty");
        }
    }
}
