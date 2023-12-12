package com.example.kameleoon_trial_task.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = {"password"})
public class User extends BaseEntity {

    @Size(min = 5, max = 128)
    private String name;

    @Email
    @NotBlank
    @Size(min = 5, max = 128)
    private String email;

    @NotBlank
    @Size(min = 5, max = 128)
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}