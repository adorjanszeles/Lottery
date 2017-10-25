package com.lottery.model.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = NumbersValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DrawnNumbers {

    String message() default "Invalid Numbers. Please enter five numbers between 1 and 90!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
