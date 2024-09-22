package com.bmc.mapper.request;

import lombok.Data;

import java.util.List;

@Data
public class TicketRequest {
    private String showId;
    private String userId;
    private double ticketPrice;
    private List<Ticket> tickets;
    
    // Getters and setters
}