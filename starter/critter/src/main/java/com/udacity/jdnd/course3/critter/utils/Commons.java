package com.udacity.jdnd.course3.critter.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class Commons {

    public Object convertObjectToObject(Object entryClass, Object destinationClass) {
        BeanUtils.copyProperties(entryClass, destinationClass);
        return destinationClass;
    }
}
