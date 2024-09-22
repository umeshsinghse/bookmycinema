package com.bmc.service;

import com.bmc.mapper.request.TheaterEntryDto;
import com.bmc.mapper.request.TheaterSeatsEntryDto;
public interface TheaterService {

    public Theater createTheater(TheaterEntryDto theaterEntryDto);
    public List<Theater> getAllTheaters();
    public Theater getTheaterById(Long id);
    public Theater updateTheater(Long id, TheaterEntryDto theaterEntryDto);
    public void deleteTheater(Long id);

    public String addTheaterSeats(TheaterSeatsEntryDto theaterSeatsEntryDto);

}
