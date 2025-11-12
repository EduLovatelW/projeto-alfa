package projetoalfa.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "client")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Client {
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;
}
