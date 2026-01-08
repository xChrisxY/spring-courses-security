package com.chris.authentication.auth.validators;

import com.chris.authentication.auth.services.users.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class ExistsByUsernameValidation implements ConstraintValidator<ExistsByUsername, String> {

    private UserService userService;

    public ExistsByUsernameValidation(UserService userService){
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if (userService == null) {
            return true;
        }

        return !userService.existsByUsername(value);
    }
}
