package com.example.kameleoon_trial_task.service;

import com.example.kameleoon_trial_task.model.User;
import com.example.kameleoon_trial_task.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.kameleoon_trial_task.util.ValidationUtil.checkNew;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User save(User user) {
        checkNew(user);
        return userRepository.save(prepareToSave(user));
    }

    private User prepareToSave(User user) {
        String lowerCaseEmail = user.getEmail().toLowerCase();
        user.setEmail(lowerCaseEmail);
        return user;
    }
}