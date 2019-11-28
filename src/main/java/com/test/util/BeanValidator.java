package com.test.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * 请求参数校验类
 *
 * @author
 * @create 2019-11-17 14:55
 */
public class BeanValidator {

    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    /*校验单个对象*/
    public static <T> Map<String, String> validate(T t, Class... groups) {
        Validator validator = validatorFactory.getValidator();
        Set validateResult = validator.validate(t, groups);
        if (validateResult.isEmpty()) {
            //没有错误，返回一个空map
            return Collections.emptyMap();
        } else {
            //谷歌Guava提供的方法，相当于newLinkedHashMap()
            LinkedHashMap errors = Maps.newLinkedHashMap();
            Iterator iterator = validateResult.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation violation = (ConstraintViolation) iterator.next();
                //key校验错误的字段，value错误信息
                errors.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
            return errors;
        }
    }

    /*校验多个对象*/
    public static Map<String, String> validateList(Collection<?> collection) {
        //谷歌Guava提供的校验类，被校验的对象为null抛出空指针异常,不为null直接返回
        Preconditions.checkNotNull(collection);
        Iterator iterator = collection.iterator();
        Map errors;
        do {
            if (!iterator.hasNext()) {
                //返回空map
                return Collections.emptyMap();
            }
            Object object = iterator.next();
            errors = validate(object, new Class[0]);
        } while (errors.isEmpty());

        return errors;
    }

    /**
     * 校验一个类是否合法
     *
     * @return
     */
    public static Map<String, String> validateObject(Object first, Object... objects) {
        if (objects != null && objects.length > 0) {
            //传入多个校验值
            return validateList(Lists.asList(first, objects));
        } else {
            //传入一个校验值
            return validate(first, new Class[0]);
        }
    }

}
