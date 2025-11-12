package projetoalfa.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupByDTO {
    private String key;
    private long totalTickets;
}
