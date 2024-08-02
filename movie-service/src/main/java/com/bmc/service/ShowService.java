package com.bmc.service;

import com.bmc.exception.MovieNotFound;
import com.bmc.exception.ShowNotFound;
import com.bmc.exception.TheaterNotFound;
import com.bmc.mapper.request.AddShowDto;
import com.bmc.mapper.request.ShowSeatsDto;

public interface ShowService {
    public String addShow(AddShowDto showDto)throws TheaterNotFound, MovieNotFound;
    public String associateShowSeats(ShowSeatsDto showSeatsDto)throws ShowNotFound;
    public String getMovieName(AddShowDto showDto);
}
