package com.ravn.challenge.movies_catalog_management.service;

import com.ravn.challenge.movies_catalog_management.model.Genre;
import com.ravn.challenge.movies_catalog_management.repository.GenreRepository;
import com.ravn.challenge.movies_catalog_management.utils.Constants;
import com.ravn.challenge.movies_catalog_management.utils.GenericRestListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    public GenericRestListResponse<Genre> listGenres() {
        List<Genre> results = genreRepository.findAll();

        GenericRestListResponse<Genre> response = new GenericRestListResponse<>();
        response.setRecords(results);
        response.setMessage("The Genres have been retrieved successfully...");
        response.setStatusCode(200);
        response.setStatus(Constants.SUCCESS_RESPONSE);

        return response;
    }

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    private final GenreRepository genreRepository;
}
