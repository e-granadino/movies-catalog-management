package com.ravn.challenge.movies_catalog_management.service;

import com.ravn.challenge.movies_catalog_management.dto.RatingDto;
import com.ravn.challenge.movies_catalog_management.dto.UserDto;
import com.ravn.challenge.movies_catalog_management.exception.InvalidDeleteOperationException;
import com.ravn.challenge.movies_catalog_management.exception.MovieNotFoundException;
import com.ravn.challenge.movies_catalog_management.exception.SessionExpiredException;
import com.ravn.challenge.movies_catalog_management.model.Movie;
import com.ravn.challenge.movies_catalog_management.model.Rating;
import com.ravn.challenge.movies_catalog_management.repository.MovieRepository;
import com.ravn.challenge.movies_catalog_management.repository.RatingRepository;
import com.ravn.challenge.movies_catalog_management.utils.Constants;
import com.ravn.challenge.movies_catalog_management.utils.GenericRestListResponse;
import com.ravn.challenge.movies_catalog_management.utils.GenericRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService{
    public void listRatingByUserName(GenericRestListResponse<Rating> response) {
        UserDto userLogged = sessionDataService.getUserSession();

        if(userLogged == null){
            throw new SessionExpiredException("The session has expired.");
        }

        List<Rating> ratingList = ratingRepository.findByCreatedBy(userLogged.getUserName());
        response.setRecords(ratingList);
        response.setMessage("The rating has been authenticated successfully...");
        response.setStatusCode(HttpStatus.OK.value());
        response.setStatus(Constants.SUCCESS_RESPONSE);
    }

    public void createUserRating(RatingDto ratingDto, GenericRestResponse<Rating> response) {
        UserDto userLogged = sessionDataService.getUserSession();

        if(userLogged == null){
            throw new SessionExpiredException("The session has expired.");
        }

        // Checking if movie exists
        Optional<Movie> movie = movieRepository.findById(ratingDto.getMovieId());
        if(movie.isEmpty()){
            throw new MovieNotFoundException(ratingDto.getMovieId());
        }

        //Verifying user hasn't rated the movie before
        List<Rating> ratigList = ratingRepository.findByMovieIdAndCreatedBy(ratingDto.getMovieId(), userLogged.getUserName());

        if(ratigList.isEmpty()){
            Rating ratingToSave = new Rating();
            ratingToSave.setMovieId(ratingDto.getMovieId());
            ratingToSave.setCreatedBy(userLogged.getUserName());
            ratingToSave.setCreatedAt(LocalDateTime.now());
            ratingToSave.setRate(ratingDto.getRate());
            ratingToSave.setComment(ratingDto.getComment());

           ratingRepository.save(ratingToSave);

            response.setResponse(ratingToSave);
            response.setMessage("The rating has been authenticated successfully...");
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatus(Constants.SUCCESS_RESPONSE);
        } else {
            Rating rating = ratigList.get(0);
            response.setResponse(rating);
            response.setMessage("User already rate this movie, so not changes were saved");
            response.setStatus(Constants.SUCCESS_RESPONSE);
            response.setStatusCode(HttpStatus.CONFLICT.value());
        }

    }

    public void deleteUserRating(Long ratingId, GenericRestResponse<Rating> response) {
        // Checking if the rating exists
        Optional<Rating> ratingCheck = ratingRepository.findById(ratingId);
        if(ratingCheck.isEmpty()){
            throw new InvalidDeleteOperationException(ratingId);
        }

        Rating ratingToDelete = ratingCheck.get();
        ratingRepository.delete(ratingToDelete);

        response.setResponse(ratingToDelete);
        response.setMessage("The rating has been deleted successfully.");
        response.setStatusCode(HttpStatus.OK.value());
        response.setStatus(Constants.SUCCESS_RESPONSE);
    }

    public List<Rating> findByMovieId(Long movieId) {
        return ratingRepository.findByMovieId(movieId);
    }

    public void deleteById(Long id) {
        ratingRepository.deleteById(id);
    }

    @Autowired
    public RatingServiceImpl(SessionDataService sessionDataService,
                             RatingRepository ratingRepository,
                             MovieRepository movieRepository){
        this.sessionDataService = sessionDataService;
        this.ratingRepository = ratingRepository;
        this.movieRepository = movieRepository;
    }

    private final RatingRepository ratingRepository;

    private final SessionDataService sessionDataService;

    private final MovieRepository movieRepository;
}
