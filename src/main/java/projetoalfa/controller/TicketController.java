package projetoalfa.controller;

import projetoalfa.dto.*;
import projetoalfa.model.Ticket;
import projetoalfa.service.TicketService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/dashboard")
    public DashboardResponseDTO getDashboard(
            @RequestParam int month,
            @RequestParam int year
    ) {
        return ticketService.getDashboard(month, year);
    }

    
    @PostMapping
    public TicketDTO createTicket(@RequestBody Ticket ticket) {
        return ticketService.createTicket(ticket);
    }
}
