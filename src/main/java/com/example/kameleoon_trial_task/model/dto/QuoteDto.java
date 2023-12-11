package com.example.kameleoon_trial_task.model.dto;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuoteDto {

    private String id;

    private Instant createdAt;

    private Instant modifiedAt;

    private String content;

    private int score;

    private UserDto author;

    private List<VoteDto> votes;

}