package com.example.employee_management_system.annotation;

import com.example.employee_management_system.validator.UppercaseValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UppercaseValidator.class)
public @interface Uppercase {
    String message() default "This value should be uppercase!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
