package com.bmc.service;

import com.bmc.exception.MovieAlreadyPresentWithSameNameAndLanguage;
import com.bmc.exception.MovieNotFound;
import com.bmc.mapper.request.MovieEntryDto;
import com.bmc.model.Movie;
import com.bmc.model.Show;
import com.bmc.model.Ticket;
import com.bmc.parser.MovieTransformer;
import com.bmc.repository.MovieRepository;
import com.bmc.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ShowRepository showRepository;

    public String addMovie(MovieEntryDto movieEntryDto) throws MovieAlreadyPresentWithSameNameAndLanguage {

        Optional<Movie> movieOptional = Optional.ofNullable(movieRepository.findByMovieName(movieEntryDto.getMovieName()));
        if (movieOptional.isPresent()) {
            throw new MovieAlreadyPresentWithSameNameAndLanguage("Movie is Already present by same name");
        }
        Movie movie = MovieTransformer.convertDtoToEntity(movieEntryDto);
        movieRepository.save(movie);
        return "Movie added successfully";
    }

    public Long totalCollection(Integer movieId) throws MovieNotFound {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        if (optionalMovie.isEmpty()) {
            throw new MovieNotFound("Movie is not present with this Id" + movieId);
        }
        List<Show> showListOfMovie = showRepository.getAllShowsOfMovie(movieId);
        long amount = 0;
        for (Show show : showListOfMovie) {
            for (Ticket ticket : show.getTicketList()) {
                amount += (long) ticket.getTotalTicketsPrice();
            }
        }
        return amount;
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movie = new ArrayList<>();
        movieRepository.findAll().forEach(movie::add);
        return movie;
    }

    @Override
    public Movie getMovieById(int id) {
        return movieRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        movieRepository.deleteById(id);
    }

    @Override
    public void update(Movie movie, int id) {
        movieRepository.save(movie);
    }
}
