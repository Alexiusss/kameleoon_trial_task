package com.example.kameleoon_trial_task.repository;

import com.example.kameleoon_trial_task.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}