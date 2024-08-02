package com.bmc.repository;

import com.bmc.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TheaterRepository extends JpaRepository<Theater,Integer> {

    Theater findByLocation(String location);
}
