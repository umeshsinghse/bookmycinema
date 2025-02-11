package com.bmc.parser;
import com.bmc.mapper.request.MovieEntryDto;
import com.bmc.model.Movie;

public class MovieTransformer {
    public static Movie convertDtoToEntity(MovieEntryDto movieEntryDto){

        Movie movie = Movie.builder().movieName(movieEntryDto.getMovieName())
                .duration(movieEntryDto.getDuration())
                .genre(movieEntryDto.getGenre())
                .language(movieEntryDto.getLanguage())
                .rating(movieEntryDto.getRating()).releaseDate(movieEntryDto.getReleaseDate())
                .build();

        return movie;
    }
}
