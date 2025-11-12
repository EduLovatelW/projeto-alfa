package projetoalfa.repository;

import projetoalfa.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByOpeningDateBetween(LocalDate start, LocalDate end);
}
