package com.bmc.mapper.request;

import com.bmc.enums.Genre;
import com.bmc.enums.Language;
import lombok.Data;

import java.util.Date;

@Data
public class MovieEntryDto {

    private String movieName;
    private double duration;
    private double rating;
    private Date releaseDate;
    private Genre genre;
    private Language language;

}
