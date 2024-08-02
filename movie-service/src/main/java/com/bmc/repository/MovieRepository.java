package com.bmc.repository;

import com.bmc.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie,Integer> {

    Movie findByMovieName(String name);

}
