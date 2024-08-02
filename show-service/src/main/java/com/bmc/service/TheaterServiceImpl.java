package com.bmc.service;

import com.bmc.enums.SeatType;
import com.bmc.mapper.request.TheaterEntryDto;
import com.bmc.mapper.request.TheaterSeatsEntryDto;
import com.bmc.model.Theater;
import com.bmc.model.TheaterSeat;
import com.bmc.repository.TheaterRepository;
import com.bmc.parser.TheaterTransformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterServiceImpl implements TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;

    @Override
    public String addTheater(TheaterEntryDto theaterEntryDto){

        //Entity that saves into the db
        //Convert the entryDto --> Entity and then save it
        Theater theater = TheaterTransformers.convertDtoToEntity(theaterEntryDto);
        theaterRepository.save(theater);

        return "Theater Added succesfully";
    }
    @Override
    public String addTheaterSeats(TheaterSeatsEntryDto theaterSeatsEntryDto){

        //We need to save the TheaterSeat Entity
        int columns = theaterSeatsEntryDto.getNoOfSeatsIn1Row();
        int noOfClassicSeats = theaterSeatsEntryDto.getNofOfClassicSeats();
        int noOfPremiumSeats = theaterSeatsEntryDto.getNoOfPremiumSeats();

        String location = theaterSeatsEntryDto.getLocation();

        Theater theater = theaterRepository.findByLocation(location);

        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();

        System.out.println("The value of noOfPremium Seats"+noOfPremiumSeats);

        int counter = 1;
        char ch = 'A';

        //this is done for the classic seats
        for(int count = 1;count<=noOfClassicSeats;count++){

            String seatNo = counter+"";
            seatNo = seatNo + ch;

            ch++;

            if((ch-'A')==columns){
                ch = 'A';
                counter++;
            }


            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setTheater(theater);
            theaterSeat.setSeatType(SeatType.CLASSIC);

            //This is the bidirectional mapping...storing the child entity
            //in the parent entity
            theaterSeatList.add(theaterSeat);
        }

        //Lets to the same for the premium seats
        for(int count=1;count<=noOfPremiumSeats;count++){
            String seatNo = counter+"";
            seatNo = seatNo + ch;
            ch++;
            if((ch-'A')==columns){
                ch = 'A';
                counter++;
            }
            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setTheater(theater);
            theaterSeat.setSeatType(SeatType.PREMIUM);
            theaterSeat.setSeatNo(seatNo);
            //This is the bidirectional mapping...storing the child entity
            //in the parent entity
            System.out.println("The seatNo is "+seatNo);
            theaterSeatList.add(theaterSeat);
        }

        //We just need to save the parent : theater Entity
        //child will automatically get saved bcz of bidirectional mapping
        theaterRepository.save(theater);

        return "Theater Seats have been successfully added";
    }

}
