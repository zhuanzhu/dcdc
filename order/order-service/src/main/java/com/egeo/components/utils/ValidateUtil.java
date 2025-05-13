package com.egeo.components.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidateUtil {

    /**
     * 校验器
     * @param t         参数
     * @param <T>       参数类型
     * @return
     */
    public static <T> Set<String> valid(T t){
        Validator validatorFactory = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> errors = validatorFactory.validate(t);
        return errors.stream().map(error -> error.getMessage()).collect(Collectors.toSet());
    }

}
