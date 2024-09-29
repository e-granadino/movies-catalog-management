package com.ravn.challenge.movies_catalog_management.service;

import com.ravn.challenge.movies_catalog_management.dto.*;
import com.ravn.challenge.movies_catalog_management.exception.InvalidDeleteOperationException;
import com.ravn.challenge.movies_catalog_management.exception.InvalidUpdateOperationException;
import com.ravn.challenge.movies_catalog_management.model.Movie;
import com.ravn.challenge.movies_catalog_management.model.MovieGenre;
import com.ravn.challenge.movies_catalog_management.model.Rating;
import com.ravn.challenge.movies_catalog_management.repository.MovieGenreRepository;
import com.ravn.challenge.movies_catalog_management.repository.MovieRepository;
import com.ravn.challenge.movies_catalog_management.utils.Constants;
import com.ravn.challenge.movies_catalog_management.utils.GenericRestListResponse;
import com.ravn.challenge.movies_catalog_management.utils.GenericRestResponse;
import com.ravn.challenge.movies_catalog_management.utils.query.Filter;
import com.ravn.challenge.movies_catalog_management.utils.query.QuerySupport;
import com.ravn.challenge.movies_catalog_management.utils.query.SearchCriteria;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{
    static final Logger logger = LogManager.getLogger(MovieServiceImpl.class);

    public GenericRestResponse<Movie> createMovie(MovieDto movieDto) {

        UserDto userLogged = sessionDataService.getUserSession();
        LocalDateTime now = LocalDateTime.now();

        ModelMapper mapper = new ModelMapper();
        Movie movie = mapper.map(movieDto, Movie.class);
        movie.setCreatedBy(userLogged.getUserName());
        movie.setUpdatedBy(userLogged.getUserName());
        movie.setCreatedAt(now);
        movie.setUpdatedAt(now);

        movieRepository.save(movie);

        //Creating movie genres
        List<GenreDto> genres = movieDto.getGenres();
        for(GenreDto genre: genres){
            MovieGenre movieGenre = new MovieGenre();
            movieGenre.setCreatedAt(LocalDateTime.now());
            movieGenre.setMovieId(movie.getId());
            movieGenre.setGenreId(genre.getId());
            movieGenre.setName(genre.getName());

            movieGenreRepository.save(movieGenre);
        }

        GenericRestResponse<Movie> response = new GenericRestResponse<>();
        response.setResponse(movie);
        response.setMessage("The movie has been created successfully");
        response.setStatusCode(HttpStatus.OK.value());
        response.setStatus(Constants.SUCCESS_RESPONSE);
        
        return response;
    }

    public GenericRestResponse<Movie> updateMovie(UpdateMovieDto movieDto) {

        if(movieDto.getId() == null){
            throw new InvalidUpdateOperationException(movieDto.getTitle());
        }

        UserDto userLogged = sessionDataService.getUserSession();
        LocalDateTime now = LocalDateTime.now();

        ModelMapper mapper = new ModelMapper();
        Movie movieToUpdate = mapper.map(movieDto, Movie.class);
        movieToUpdate.setUpdatedAt(now);
        movieToUpdate.setUpdatedBy(userLogged.getUserName());

        movieRepository.save(movieToUpdate);

        GenericRestResponse<Movie> response = new GenericRestResponse<>();
        response.setResponse(movieToUpdate);
        response.setMessage("The movie has been updated successfully");
        response.setStatusCode(HttpStatus.OK.value());
        response.setStatus(Constants.SUCCESS_RESPONSE);
        
        return response;
    }

    public GenericRestResponse<Movie> deleteMovie(Long id) {

        // Checking if the movie with the id exists
        Optional<Movie> movieCheck = movieRepository.findById(id);

        if(movieCheck.isEmpty()){
            throw new InvalidDeleteOperationException(id);
        }

        // Deleting movie ratings
        List<Rating> ratingList = ratingService.findByMovieId(id);
        for(Rating rating: ratingList){
            ratingService.deleteById(rating.getId());
        }

        // Deleting movie genres
        List<MovieGenre> movieGenres = movieGenreRepository.findByMovieId(id);
        for(MovieGenre movieGenre: movieGenres){
            movieGenreRepository.deleteById(movieGenre.getId());
        }

        Movie movieToDelete = movieCheck.get();
        movieRepository.delete(movieToDelete);

        GenericRestResponse<Movie> response = new GenericRestResponse<>();
        response.setResponse(movieToDelete);
        response.setMessage("The movie was deleted successfully.");
        response.setStatusCode(HttpStatus.OK.value());
        response.setStatus(Constants.SUCCESS_RESPONSE);
        
        return response;
    }

    @Cacheable("SearchMoviesCached")
    public GenericRestListResponse<Movie> searchMovies(QuerySearchRequestDto query) {
        String sort = query.getSort();
        String sortBy = query.getSortBy();
        Integer page = query.getPage();
        Integer maxResults = query.getMaxResults();

        List<Filter> filters = query.getFilters();
        SearchCriteria queryParams = new SearchCriteria();

        if(sort != null){
            queryParams.setSort(sort);
        }

        if(sort != null){
            queryParams.setSortBy(sortBy);
        }

        if(page != null){
            queryParams.setPage(Math.max(1, page));
        }

        if(maxResults != null){
            queryParams.setMaxResults(maxResults);
        }

        try{
            filters.forEach(filter -> queryParams.addFilter(filter.getField(), filter.getOperator(), filter.getValue(), filter.getCondition()));
        }catch (Exception e){
            logger.error("Error while adding filters", e);
        }

        Specification<Movie> specs = QuerySupport.compileQuery(queryParams);
        PageRequest pageRequest = QuerySupport.getPageRequest(queryParams);

        Page<Movie> paginationResult = movieRepository.findAll(specs, pageRequest);
        GenericRestListResponse<Movie> response = new GenericRestListResponse<>();

        if(paginationResult.isEmpty()){
            response.setMessage("No records were found...");
            response.setRecords(new ArrayList<>());
            response.setStatus(Constants.SUCCESS_RESPONSE);
            response.setStatusCode(404);
        }else {
            response.setRecords(paginationResult.getContent());
            response.setPaginationFromPageResult(paginationResult);
            response.setMessage("The records were retrieved successfully...");
            response.setStatus(Constants.SUCCESS_RESPONSE);
            response.setStatusCode(200);
        }

        return response;
    }

    @Autowired
    public MovieServiceImpl(SessionDataService sessionDataService, MovieRepository movieRepository,
                                 RatingServiceImpl ratingService, MovieGenreRepository movieGenreRepository){
        this.ratingService = ratingService;
        this.movieRepository = movieRepository;
        this.sessionDataService = sessionDataService;
        this.movieGenreRepository = movieGenreRepository;
    }

    private final SessionDataService sessionDataService;

    private final MovieRepository movieRepository;

    private final RatingServiceImpl ratingService;

    private final MovieGenreRepository movieGenreRepository;
}
