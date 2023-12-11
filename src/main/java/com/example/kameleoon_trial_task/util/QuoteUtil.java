package com.example.kameleoon_trial_task.util;

import com.example.kameleoon_trial_task.model.Quote;
import com.example.kameleoon_trial_task.model.Vote;
import com.example.kameleoon_trial_task.model.dto.QuoteDto;
import com.example.kameleoon_trial_task.model.dto.UserDto;
import com.example.kameleoon_trial_task.model.dto.VoteDto;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class QuoteUtil {
    public static QuoteDto convertToDto(Quote quote) {
        QuoteDto quoteDto = new QuoteDto();
        quoteDto.setId(quote.id());
        quoteDto.setCreatedAt(quote.getCreatedAt());
        quoteDto.setModifiedAt(quote.getModifiedAt());
        quoteDto.setContent(quote.getContent());
        quoteDto.setScore(calculateScore(quote.getVotes()));
        quoteDto.setAuthor(new UserDto(quote.getAuthor()));
        quoteDto.setVotes(convertToVoteDto(quote.getVotes()));
        return quoteDto;
    }

    private int calculateScore(List<Vote> votes) {
        if (votes != null) {
            return votes.stream()
                    .map(Vote::getVote)
                    .reduce(Integer::sum).orElse(0);
        } else return 0;
    }

    private List<VoteDto> convertToVoteDto(List<Vote> votes) {
        return votes.stream()
                .map(VoteDto::new)
                .toList();
    }
}