package com.bmc.repository;

import com.bmc.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {

    Movie findByMovieName(String name);

}
