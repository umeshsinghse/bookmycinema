package com.bmc.mapper.request;

import lombok.Data;

@Data
public class TheaterSeatsEntryDto {

    private int noOfSeatsIn1Row;
    private int nofOfClassicSeats;
    private int noOfPremiumSeats;
    private String location;
}
