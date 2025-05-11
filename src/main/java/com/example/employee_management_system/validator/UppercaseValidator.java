package com.example.employee_management_system.validator;

import com.example.employee_management_system.annotation.Uppercase;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UppercaseValidator implements ConstraintValidator<Uppercase, String> {

    @Override
    public void initialize(Uppercase constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s.isEmpty()) return true;
        if (!s.equals(s.toUpperCase())) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Expected uppercase but got: \"" + s + "\"")
                                      .addConstraintViolation();
            return false;
        }
        return true;
    }
}
