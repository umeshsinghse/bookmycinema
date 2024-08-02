package com.bmc.controller;

import com.bmc.mapper.request.MovieEntryDto;
import com.bmc.model.Movie;
import com.bmc.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/movie")
    public ResponseEntity<String> addMovie(@RequestBody MovieEntryDto movieEntryDto){
     try {
         String result= movieService.addMovie(movieEntryDto);
         return new ResponseEntity<>(result,HttpStatus.CREATED);
     }catch (Exception e){
          return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
     }
    }
    @GetMapping("/movie/totalCollection/{movieId}")
    public ResponseEntity<Long> totalCollection(@PathVariable Integer movieId) {
        try {
            Long result = movieService.totalCollection(movieId);
            return new ResponseEntity<>(result,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/movie")
    public List<Movie> getAllMovies()
    {
        return movieService.getAllMovies();
    }
    //creating a get mapping that retrieves the detail of a specific movie
    @GetMapping("/movie/{id}")
    public Movie getMovie(@PathVariable("id") int id)
    {
        return movieService.getMovieById(id);
    }
    //creating a delete mapping that deletes a specified movie
    @DeleteMapping("/movie/{id}")
    public void deleteBook(@PathVariable("id") int id)
    {
        movieService.delete(id);
    }
    //creating post mapping that post the movie detail in the database
    //creating put mapping that updates the movie detail
}
