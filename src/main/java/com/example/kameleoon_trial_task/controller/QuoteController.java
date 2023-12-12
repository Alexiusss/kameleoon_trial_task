package com.example.kameleoon_trial_task.controller;

import com.example.kameleoon_trial_task.model.Quote;
import com.example.kameleoon_trial_task.model.dto.QuoteDto;
import com.example.kameleoon_trial_task.service.QuoteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = QuoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class QuoteController {

    private final QuoteService quoteService;

    public static final String REST_URL = "/api/v1/quotes";

    @Operation(summary = "Get a quote by item id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<QuoteDto> get(@PathVariable String id) {
        QuoteDto quotes = quoteService.getById(id);
        return ResponseEntity.ok(quotes);
    }

    @Operation(summary = "Get a random quote")
    @GetMapping(value = "/random")
    public ResponseEntity<QuoteDto> getRandom() {
        QuoteDto quotes = quoteService.getRandomQuote();
        return ResponseEntity.ok(quotes);
    }

    @Operation(summary = "Get a list of quotes with the chosen direction and limit")
    @GetMapping
    public ResponseEntity<List<QuoteDto>> getTopOrWorseQuotes(@RequestParam String direction, @RequestParam int limit) {
        List<QuoteDto> quotes = quoteService.getQuotesSortedByScores(direction, limit);
        return ResponseEntity.ok(quotes);
    }

    @Operation(summary = "Create a new quote")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Quote> create(@Valid @RequestBody Quote quoteDto, @RequestParam String authorId) {
        Quote createdQuote = quoteService.create(quoteDto, authorId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuote);
    }

    @Operation(summary = "Update a quote by its id")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Quote quoteDto, @RequestParam String authorId) {
        quoteService.update(quoteDto, authorId);
    }

    @Operation(summary = "Delete a quote by its id")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        quoteService.deleteById(id);
    }

    @Operation(summary = "Upvote a quote using its id")
    @PatchMapping(value = "/{quoteId}/upvote")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void upvote(@PathVariable String quoteId, @RequestParam String authorId) {
        quoteService.vote(quoteId, authorId, 1);
    }

    @Operation(summary = "Downvote a quote by using its id")
    @PatchMapping(value = "/{quoteId}/downvote")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void downvote(@PathVariable String quoteId, @RequestParam String authorId) {
        quoteService.vote(quoteId, authorId, -1);
    }
}