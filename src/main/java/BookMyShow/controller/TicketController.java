package BookMyShow.controller;



import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/buyticket")
    public ResponseEntity buyTicket(@RequestParam String email, @RequestParam UUID showID){
        Ticket ticket = ticketService.buyTicket(email, showID);
        return new ResponseEntity("Ticket created successfully !!", HttpStatus.CREATED);
    }
}