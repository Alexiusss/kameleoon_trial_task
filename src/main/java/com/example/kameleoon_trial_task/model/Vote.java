package com.example.kameleoon_trial_task.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "votes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Vote extends BaseEntity {

    @NotBlank
    private String userId;

    @NotBlank
    private String quoteId;

    @Range(min = -1, max = 1)
    private int vote;
}