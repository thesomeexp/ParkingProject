package com.someexp.common.validator;

import com.someexp.common.exception.ParamsException;
import com.someexp.common.utils.MsgUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 *
 * @author someexp
 * @date 2020/12/2
 */
@Component
public class ValidatorUtils {

    private ValidatorUtils() {

    }

    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     * @throws ParamsException 校验不通过
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws ParamsException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<Object> constraint : constraintViolations) {
                msg.append(MsgUtils.get(constraint.getMessage()) + ", ");
            }
            String result = msg.substring(0, msg.length() - 2);
            throw new ParamsException(result);
        }
    }

}
