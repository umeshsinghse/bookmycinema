package com.bmc.mapper.request;


import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class AddShowDto {

    private LocalTime showStartTime;
    private Date showDate;
    private int theaterId;
    private int movieId;

}
