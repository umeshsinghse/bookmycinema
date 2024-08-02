package com.bmc.service;
import com.bmc.exception.NoUserFoundException;
import com.bmc.exception.ShowNotFound;
import com.bmc.mapper.request.TicketRequestDto;
import com.bmc.mapper.response.TicketResponseDto;
import com.bmc.model.Show;
import com.bmc.model.ShowSeat;
import com.bmc.model.Ticket;
import com.bmc.model.User;
import com.bmc.repository.ShowRepository;
import com.bmc.repository.TicketRepository;
import com.bmc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl  implements TicketService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private TicketRepository ticketRepository;
    public TicketResponseDto bookTicket(TicketRequestDto ticketRequestDto)throws NoUserFoundException, ShowNotFound,Exception {

        //User validation
        int userId = ticketRequestDto.getUserId();
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new NoUserFoundException("User Id is incorrect");
        }
        //Show Validation
        int showId = ticketRequestDto.getShowId();
        Optional<Show> showOptional = showRepository.findById(showId);
        if(showOptional.isEmpty()){
            throw new ShowNotFound("Show is not found");
        }

        Show show = showOptional.get();

        //Validation for the requested Seats are available or not
        boolean isValid = validateShowAvailability(show,ticketRequestDto.getRequestedSeats());

        if(!isValid){
            throw new Exception("Requested Seats entered are not available");
        }

        Ticket ticket = new Ticket();
        //calculate the total price

        int totalPrice = calculateTotalPrice(show,ticketRequestDto.getRequestedSeats());

        ticket.setTotalTicketsPrice(totalPrice);

        //Convert the list of booked seats into string from list
        String bookedSeats = convertListToString(ticketRequestDto.getRequestedSeats());

        ticket.setBookedSeats(bookedSeats);
        //Do bidirectional mapping

        User user = userOptional.get();

        ticket.setUser(user);
        ticket.setShow(show);

        ticket = ticketRepository.save(ticket);

        user.getTicketList().add(ticket);
        //Saving the relevant repositories

        userRepository.save(user);

        show.getTicketList().add(ticket);

        showRepository.save(show);

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();

        String body="Hi"+user.getName()+" ! /n "+
             "You have successfully booked a ticket. Please find the following details No's"+ bookedSeats
              +"movie Name"+ show.getMovie().getMovieName()
              +"show Date is"+show.getDate()+
              "And show time is "+show.getTime()+
              "Enjoy the show!!! "+show.getTime()+
              "Enjoy the show !!!";

        simpleMailMessage.setSubject("Ticket confirmation Mail");
        simpleMailMessage.setFrom("aimofficer1963@gmail.com");
        simpleMailMessage.setText(body);
        simpleMailMessage.setTo(user.getEmail());

        return createTicketReponseDto(show,ticket);
    }
    private boolean validateShowAvailability(Show show, List<String> requestedSeats){

        List<ShowSeat> showSeatList = show.getShowSeatList();
        for(ShowSeat showSeat : showSeatList){
            String seatNo = showSeat.getSeatNo();
            if(requestedSeats.contains(seatNo)){
                if(showSeat.isAvailable()==false)
                    return false;
            }
        }
        return true;
    }
    private int calculateTotalPrice(Show show, List<String> requestedSeats){
        int totalPrice = 0;
        List<ShowSeat> showSeatList = show.getShowSeatList();
        for(ShowSeat showSeat : showSeatList){
            if(requestedSeats.contains(showSeat.getSeatNo())){
                totalPrice = totalPrice + showSeat.getPrice();
                showSeat.setAvailable(false);
            }
        }
        return totalPrice;
    }

    String convertListToString(List<String> seats){
        String result = "";
        for(String seatNo : seats){
            result = result + seatNo+", ";
        }
        return result;
    }
    private TicketResponseDto createTicketReponseDto(Show show, Ticket ticket){
        TicketResponseDto ticketResponseDto = TicketResponseDto.builder()
                .bookedSeats(ticket.getBookedSeats())
                .location(show.getTheater().getLocation())
                .theaterName(show.getTheater().getName())
                .movieName(show.getMovie().getMovieName())
                .showDate(show.getDate())
                .showTime(show.getTime())
                .totalPrice(ticket.getTotalTicketsPrice())
                .build();
        return ticketResponseDto;
    }
    @Override
    public List<Ticket> getAllTickets() {
        List<Ticket> ticket = new ArrayList<>();
        ticketRepository.findAll().forEach(ticket::add);
        return ticket;
    }

    @Override
    public Ticket getTicketById(int id) {
        return ticketRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public void update(Ticket ticket) {
        ticketRepository.save(ticket);
    }
}
