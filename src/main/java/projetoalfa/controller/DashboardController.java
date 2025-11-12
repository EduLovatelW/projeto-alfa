package projetoalfa.controller;

import org.springframework.web.bind.annotation.*;
import projetoalfa.dto.DashboardResponseDTO;
import projetoalfa.service.TicketService;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:5173") 
public class DashboardController {

    private final TicketService ticketService;

    public DashboardController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public DashboardResponseDTO getDashboard(
            @RequestParam int month,
            @RequestParam int year
    ) {
        return ticketService.getDashboard(month, year);
    }
}
