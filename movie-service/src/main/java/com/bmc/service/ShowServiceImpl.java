package com.bmc.service;

import com.bmc.mapper.request.AddShowDto;
import com.bmc.mapper.request.ShowSeatsDto;
import com.bmc.mapper.request.AddShowDto;
import com.bmc.mapper.request.ShowSeatsDto;
import com.bmc.enums.SeatType;
import com.bmc.exception.MovieNotFound;
import com.bmc.exception.ShowNotFound;
import com.bmc.exception.TheaterNotFound;
import com.bmc.model.*;
import com.bmc.repository.MovieRepository;
import com.bmc.repository.ShowRepository;
import com.bmc.repository.TheaterRepository;
import com.bmc.parser.ShowTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowServiceImpl implements ShowService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheaterRepository theaterRepository;
    @Autowired
    private ShowRepository showRepository;
    @Override
    public String addShow(AddShowDto showDto)throws TheaterNotFound, MovieNotFound {

        Show show = ShowTransformer.convertDtoToEntity(showDto);

        //Set the movie and theater entity
        Optional<Movie> movieOptional = movieRepository.findById(showDto.getMovieId());

        if(!movieOptional.isPresent()){
            throw new MovieNotFound("Movie is not found");
        }

        Optional<Theater> theaterOptional = theaterRepository.findById(showDto.getTheaterId());

        if(!theaterOptional.isPresent()){
            throw new TheaterNotFound("Theater is not found");
        }

        Movie movie = movieOptional.get();
        Theater theater = theaterOptional.get();

        show.setMovie(movie);
        show.setTheater(theater);

        //we save show here so that we can avoid saving showing two times
        show = showRepository.save(show);

        movie.getShowList().add(show);
        theater.getShowList().add(show);

        movieRepository.save(movie);

        theaterRepository.save(theater);

        return "Show has been added and showId is "+show.getId();
    }
    @Override
    public String associateShowSeats(ShowSeatsDto showSeatsDto)throws ShowNotFound {

        Optional<Show> optionalShow = showRepository.findById(showSeatsDto.getShowId());

        //Validation check
        if(!optionalShow.isPresent()){
            throw new ShowNotFound("Show Id incorrect!!");
        }

        //Valid Show Now
        Show show = optionalShow.get();


        //We need to theaterSeats
        Theater theater = show.getTheater();

        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();

        //Each seat needs to be added in the ?????? -->

        List<ShowSeat> showSeatList = show.getShowSeatList();

        for(TheaterSeat theaterSeat : theaterSeatList){

            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeatNo(theaterSeat.getSeatNo());
            showSeat.setSeatType(theaterSeat.getSeatType());

            if(showSeat.getSeatType().equals(SeatType.CLASSIC))
                showSeat.setPrice(showSeatsDto.getPriceForClassicSeats());
            else
                showSeat.setPrice(showSeatsDto.getPriceForPremiumSeats());
            //Foreign key mapping
            showSeat.setShow(show);
            showSeat.setAvailable(true);
            showSeat.setFoodAttached(false);

            showSeatList.add(showSeat);
        }
        showRepository.save(show);
        //Child will automatically get saved.....
        return "Show seats has been successfully added";
    }
    @Override
    public String getMovieName(AddShowDto showDto) {

        Integer movieId = showRepository.getMostShowedMovie(showDto.getShowDate());
        Movie movie = movieRepository.findById(movieId).get();
        return movie.getMovieName();

    }
}
