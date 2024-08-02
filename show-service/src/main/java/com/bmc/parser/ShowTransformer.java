package com.bmc.parser;

import com.bmc.mapper.request.AddShowDto;
import com.bmc.model.Show;

public class ShowTransformer {
    public static Show convertDtoToEntity(AddShowDto addShowDto){
        Show show = Show.builder().time(addShowDto.getShowStartTime())
                .date(addShowDto.getShowDate()).build();
        return show;

    }
}
