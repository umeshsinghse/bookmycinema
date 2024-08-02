package com.bmc.service;

import com.bmc.mapper.request.TheaterEntryDto;
import com.bmc.mapper.request.TheaterSeatsEntryDto;
public interface TheaterService {

    public String addTheater(TheaterEntryDto theaterEntryDto);

    public String addTheaterSeats(TheaterSeatsEntryDto theaterSeatsEntryDto);
}
