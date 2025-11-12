package projetoalfa.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private Long id;
    private String title;
    private String client;
    private String module;
    private LocalDate openingDate;
    private LocalDate closingDate;
}
