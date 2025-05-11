package com.example.employee_management_system.annotation;

import com.example.employee_management_system.validator.AllUppercaseValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = AllUppercaseValidator.class)
public @interface AllUppercase {
    String message() default "This value should be uppercase!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
