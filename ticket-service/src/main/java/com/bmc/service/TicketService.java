package com.bmc.service;


import com.bmc.mapper.request.TicketRequestDto;
import com.bmc.mapper.response.TicketResponseDto;
import com.bmc.model.Ticket;

import java.util.List;

public interface TicketService {
    public TicketResponseDto bookTicket(TicketRequestDto ticketRequestDto) throws Exception;

    public List<Ticket> getAllTickets();

    public Ticket getTicketById(int id);

    public void delete(int id);

    public void update(Ticket ticket);
}
