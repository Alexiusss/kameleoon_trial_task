package com.example.kameleoon_trial_task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "quotes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = {"author", "votes"})
@Builder
public class Quote extends BaseEntity {

    @UpdateTimestamp
    private Instant modifiedAt;

    @NotBlank
    @Size(min = 10, max = 256)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @JsonIgnore
    private User author;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quoteId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Vote> votes;
}