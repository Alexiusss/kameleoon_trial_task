package com.example.kameleoon_trial_task.service;

import com.example.kameleoon_trial_task.error.IllegalRequestDataException;
import com.example.kameleoon_trial_task.error.NotFoundException;
import com.example.kameleoon_trial_task.model.Quote;
import com.example.kameleoon_trial_task.model.Vote;
import com.example.kameleoon_trial_task.model.dto.QuoteDto;
import com.example.kameleoon_trial_task.repository.QuoteRepository;
import com.example.kameleoon_trial_task.repository.UserRepository;
import com.example.kameleoon_trial_task.util.QuoteUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.kameleoon_trial_task.util.QuoteUtil.convertToDto;
import static com.example.kameleoon_trial_task.util.ValidationUtil.*;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;

    public QuoteDto getById(String id) {
        Quote quote = quoteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entity with id=" + id + " not found"));
        return convertToDto(quote);
    }

    public QuoteDto getRandomQuote() {
        Quote randomQuote = quoteRepository.findRandom();
        return convertToDto(randomQuote);
    }

    public List<QuoteDto> getQuotesSortedByScores(String order, int limit) {

        Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        PageRequest pageRequest = PageRequest.of(0, limit, JpaSort.unsafe(direction, "(SELECT SUM(v.vote) FROM q.votes v)"));

        List<Quote> quotes = quoteRepository.findWithAuthorsAndVotes(pageRequest).getContent();

        return quotes.stream()
                .map(QuoteUtil::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Quote create(Quote quote, String authorId) {
        checkNew(quote);
        quote.setAuthor(userRepository.getReferenceById(authorId));
        return quoteRepository.save(quote);
    }

    @Transactional
    public void update(Quote quote, String authorId) {
        assureIdConsistent(quote, quote.id());
        Quote quoteFromDB = quoteRepository.findById(quote.id())
                .orElseThrow(() -> new NotFoundException("Entity with id=" + quote.id() + " not found"));
        verifyAuthor(quoteFromDB.getAuthor(), authorId);
        quoteFromDB.setContent(quote.getContent());
    }

    @Transactional
    public void deleteById(String id) {
        quoteRepository.deleteById(id);
    }

    @Transactional
    public void vote(String quoteId, String authorId, int vote) {
        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new NotFoundException("Entity with id=" + quoteId + " not found"));
        List<Vote> votes = quote.getVotes();
        if (isContainVote(votes, quoteId, authorId)) {
            throw new IllegalRequestDataException("Duplicate vote");
        }
        votes.add(new Vote(authorId, quoteId, vote));
        quote.setVotes(votes);
    }


    private boolean isContainVote(List<Vote> votes, String quoteId, String authorId) {
        return votes.stream()
                .anyMatch(vote -> vote.getQuoteId().equals(quoteId) && vote.getUserId().equals(authorId));
    }
}