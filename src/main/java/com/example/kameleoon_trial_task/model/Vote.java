package com.example.kameleoon_trial_task.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "votes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Vote extends BaseEntity{
    private String userId;

    private String quoteId;

    private boolean isUpvote;
}