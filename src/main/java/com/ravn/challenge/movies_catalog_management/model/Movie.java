package com.ravn.challenge.movies_catalog_management.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "movie")
public class Movie implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public Movie(Long movieId) {
        this.id = movieId;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "title")
    private String title;

    @Column(name = "movie_poster")
    private String moviePoster;

    @Column(name = "synopsis")
    private String synopsis;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Column(name = "director")
    private String director;

    @Column(name = "duration")
    private Integer duration;

}
