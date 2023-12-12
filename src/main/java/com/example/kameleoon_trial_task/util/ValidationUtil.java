package com.example.kameleoon_trial_task.util;

import com.example.kameleoon_trial_task.error.IllegalRequestDataException;
import com.example.kameleoon_trial_task.model.HasId;
import com.example.kameleoon_trial_task.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtil {
    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must be new (id=null)");
        }
    }

    public static void verifyAuthor(User author, String userId) {
        if (!author.id().equals(userId)) {
            throw new IllegalRequestDataException(String.format("Current user ID %s does not match the author ID %s from the quote", userId, author.id()));
        }
    }

    public static void assureIdConsistent(HasId bean, String id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (!bean.id().equals(id)) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must has id=" + id);
        }
    }

}