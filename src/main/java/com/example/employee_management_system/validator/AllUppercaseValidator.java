package com.example.employee_management_system.validator;

import com.example.employee_management_system.annotation.AllUppercase;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;
import java.util.Set;

public class AllUppercaseValidator implements ConstraintValidator<AllUppercase, Set<String>> {
    @Override
    public void initialize(AllUppercase constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Set<String> value, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(value)) return true;
        if (!value.stream()
                  .allMatch(item -> item.equals(item.toUpperCase()))) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Expected uppercase but got: \"" + value.toString() + "\"")
                                      .addConstraintViolation();
            return false;
        }
        return true;
    }
}
