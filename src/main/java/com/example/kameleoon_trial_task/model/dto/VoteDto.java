package com.example.kameleoon_trial_task.model.dto;

import com.example.kameleoon_trial_task.model.Vote;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VoteDto {

    private Instant createdAt;

    private int vote;

    public VoteDto(Vote vote) {
        this.createdAt = vote.getCreatedAt();
        this.vote = vote.getVote();
    }
}