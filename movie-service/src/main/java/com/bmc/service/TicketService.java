package com.bmc.service;


import com.bmc.exception.NoUserFoundException;
import com.bmc.exception.ShowNotFound;
import com.bmc.mapper.request.TicketRequestDto;
import com.bmc.mapper.response.TicketResponseDto;
public interface TicketService {
    public TicketResponseDto bookTicket(TicketRequestDto ticketRequestDto) throws Exception ;
}
