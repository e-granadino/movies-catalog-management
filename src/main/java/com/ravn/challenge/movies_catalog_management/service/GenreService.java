package com.ravn.challenge.movies_catalog_management.service;

import com.ravn.challenge.movies_catalog_management.model.Genre;
import com.ravn.challenge.movies_catalog_management.utils.GenericRestListResponse;

public interface GenreService {

    GenericRestListResponse<Genre> listGenres();
}
