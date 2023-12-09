package com.example.kameleoon_trial_task.util;

import com.example.kameleoon_trial_task.error.IllegalRequestDataException;
import com.example.kameleoon_trial_task.model.HasId;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtil {
    public static void checkNew(HasId bean) {
        if(!bean.isNew()) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must be new (id=null)");
        }
    }
}