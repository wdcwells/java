package com.wdc.study.utils;

import org.springframework.util.Assert;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

public class ValidateUtil {
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    public static void validate(Object object){
        final StringBuilder errMsgBuilder = new StringBuilder();
        validator.validate(object, Default.class).forEach(
                it -> {
                    errMsgBuilder
                            .append("\n")
                            .append(it.getLeafBean().getClass().getName()).append(".")
                            .append(it.getPropertyPath()).append(":")
                            .append(it.getMessage()).append(".")
                            .append("实际值:").append(it.getInvalidValue()).append(".")
                    ;
                }
        );
        Assert.isTrue(errMsgBuilder.length()==0,errMsgBuilder.toString());
    }
}
