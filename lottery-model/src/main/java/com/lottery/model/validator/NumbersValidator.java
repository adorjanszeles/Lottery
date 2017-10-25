package com.lottery.model.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumbersValidator implements ConstraintValidator<DrawnNumbers, Integer[]> {

    @Override
    public void initialize(DrawnNumbers constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer[] drawNums, ConstraintValidatorContext constraintValidatorContext) {
        for (Integer num : drawNums) {
            if (num < 0 || num > 90) {
                return false;
            }
        }
        return true;
    }
}
