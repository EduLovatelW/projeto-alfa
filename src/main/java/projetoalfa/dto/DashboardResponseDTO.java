package projetoalfa.dto;

import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponseDTO {
    private List<TicketDTO> tickets;
    private List<GroupByDTO> byClient;
    private List<GroupByDTO> byModule;
}
