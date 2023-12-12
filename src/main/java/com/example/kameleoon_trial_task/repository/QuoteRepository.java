package com.example.kameleoon_trial_task.repository;

import com.example.kameleoon_trial_task.model.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuoteRepository extends JpaRepository<Quote, String> {

    @EntityGraph(attributePaths = {"author", "votes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT q FROM Quote q " +
            "ORDER BY random() " +
            "LIMIT 1")
    Quote findRandom();

    @EntityGraph(attributePaths = {"author", "votes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT q FROM Quote q")
    Page<Quote> findWithAuthorsAndVotes(Pageable pageable);

}