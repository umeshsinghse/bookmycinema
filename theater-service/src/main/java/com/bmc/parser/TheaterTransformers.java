package com.bmc.parser;
import com.bmc.mapper.request.TheaterEntryDto;
import com.bmc.model.Theater;

public class TheaterTransformers {
    public static Theater convertDtoToEntity(TheaterEntryDto theaterEntryDto){

        Theater theater = Theater.builder().location(theaterEntryDto.getLocation())
                .name(theaterEntryDto.getName()).build();

        return theater;
    }
}
