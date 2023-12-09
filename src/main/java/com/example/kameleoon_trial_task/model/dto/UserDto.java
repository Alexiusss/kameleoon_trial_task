package com.example.kameleoon_trial_task.model.dto;

import com.example.kameleoon_trial_task.model.User;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {

    private String id;

    private Instant createdAt;

    private String name;

    private String email;

    public UserDto(User user) {
        this.id = user.getId();
        this.createdAt = user.getCreatedAt();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}