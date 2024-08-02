package com.bmc.repository;

import com.bmc.model.TheaterSeat;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TheaterSeatRepository extends JpaRepository<TheaterSeat,Integer> {
}
