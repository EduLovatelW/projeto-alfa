package projetoalfa.service;

import projetoalfa.dto.*;
import projetoalfa.model.Ticket;
import projetoalfa.repository.ClientRepository;
import projetoalfa.repository.ModuleRepository;
import projetoalfa.repository.TicketRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ClientRepository clientRepository;
    private final ModuleRepository moduleRepository;

    public TicketService(TicketRepository ticketRepository, ClientRepository clientRepository, ModuleRepository moduleRepository) {
        this.ticketRepository = ticketRepository;
        this.clientRepository = clientRepository;
        this.moduleRepository = moduleRepository;
    }

    // Função principal do dashboard
    public DashboardResponseDTO getDashboard(int month, int year) {
        YearMonth ym = YearMonth.of(year, month);
        LocalDate start = ym.atDay(1);
        LocalDate end = ym.atEndOfMonth();

        List<Ticket> tickets = ticketRepository.findByOpeningDateBetween(start, end);

        List<TicketDTO> ticketDTOs = tickets.stream().map(t ->
                new TicketDTO(
                        t.getId(),
                        t.getTitle(),
                        t.getClient() != null ? t.getClient().getName() : null,
                        t.getModule() != null ? t.getModule().getName() : null,
                        t.getOpeningDate(),
                        t.getClosingDate()
                )
        ).collect(Collectors.toList());

        
        Map<String, Long> byClientMap = ticketDTOs.stream()
                .collect(Collectors.groupingBy(
                        td -> td.getClient() == null ? "Desconhecido" : td.getClient(),
                        Collectors.counting()
                ));

        List<GroupByDTO> byClient = byClientMap.entrySet().stream()
                .map(e -> new GroupByDTO(e.getKey(), e.getValue()))
                .sorted(Comparator.comparingLong(GroupByDTO::getTotalTickets).reversed())
                .collect(Collectors.toList());

        
        Map<String, Long> byModuleMap = ticketDTOs.stream()
                .collect(Collectors.groupingBy(
                        td -> td.getModule() == null ? "Desconhecido" : td.getModule(),
                        Collectors.counting()
                ));

        List<GroupByDTO> byModule = byModuleMap.entrySet().stream()
                .map(e -> new GroupByDTO(e.getKey(), e.getValue()))
                .sorted(Comparator.comparingLong(GroupByDTO::getTotalTickets).reversed())
                .collect(Collectors.toList());

        return new DashboardResponseDTO(ticketDTOs, byClient, byModule);
    }

    
    public TicketDTO createTicket(Ticket ticket) {
        if (ticket.getClient() != null && ticket.getClient().getId() != null) {
            clientRepository.findById(ticket.getClient().getId())
                    .ifPresent(ticket::setClient);
        }

        if (ticket.getModule() != null && ticket.getModule().getId() != null) {
            moduleRepository.findById(ticket.getModule().getId())
                    .ifPresent(ticket::setModule);
        }

        if (ticket.getOpeningDate() == null) {
            ticket.setOpeningDate(LocalDate.now());
        }

        Ticket saved = ticketRepository.save(ticket);
        return new TicketDTO(
                saved.getId(),
                saved.getTitle(),
                saved.getClient() != null ? saved.getClient().getName() : null,
                saved.getModule() != null ? saved.getModule().getName() : null,
                saved.getOpeningDate(),
                saved.getClosingDate()
        );
    }
}
