package projetoalfa.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "module")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Module {
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;
}
