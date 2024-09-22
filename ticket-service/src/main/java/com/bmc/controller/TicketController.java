package com.bmc.controller;
import com.bmc.mapper.request.TicketRequestDto;
import com.bmc.mapper.response.TicketResponseDto;
import com.bmc.model.Ticket;
import com.bmc.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @PostMapping("/ticket")
    public ResponseEntity<TicketResponseDto> bookTicket(@RequestBody TicketRequestDto ticketRequestDto){
        try{
            TicketResponseDto response =  ticketService.bookTicketWithDiscount(ticketRequestDto);
            response.setResponseMessage("Ticket booked successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            TicketResponseDto ticketResponseDto = new TicketResponseDto();
            ticketResponseDto.setResponseMessage(e.getMessage());
            return new ResponseEntity<>(ticketResponseDto,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/ticket")
    public List<Ticket> getAllBooks()
    {
        return ticketService.getAllTickets();
    }
    //creating a get mapping that retrieves the detail of a specific ticket
    @GetMapping("/ticket/{id}")
    public Ticket getTickets(@PathVariable("id") int id)
    {
        return ticketService.getTicketById(id);
    }
    //creating a delete mapping that deletes a specified ticket
    @DeleteMapping("/ticket/{id}")
    public void deleteBook(@PathVariable("id") int id)
    {
        ticketService.delete(id);
    }
    //creating put mapping that updates the ticket detail
    @PutMapping("/ticket")
    public Ticket update(@RequestBody Ticket ticket)
    {
        ticketService.update(ticket);
        return ticket;
    }
}
