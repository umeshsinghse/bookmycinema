package com.bmc.service;

import com.bmc.exception.MovieAlreadyPresentWithSameNameAndLanguage;
import com.bmc.exception.MovieNotFound;
import com.bmc.mapper.request.MovieEntryDto;
import com.bmc.model.Movie;

import java.util.List;


public interface MovieService {

    public String addMovie(MovieEntryDto movieEntryDto) throws MovieAlreadyPresentWithSameNameAndLanguage;

    public Long totalCollection(Integer movieId) throws MovieNotFound;


    public List<Movie> getAllMovies();

    public Movie getMovieById(int id);


    public void delete(int id);

    public void update(Movie movie, int id);
}