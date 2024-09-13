package ar.dev.maxisandoval.webappplantapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data //toString, equals, hashcode, getters y los setters
@NoArgsConstructor
@AllArgsConstructor
public class Jardinero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String especialidad;

    private String email;

    @OneToMany(mappedBy = "jardinero", fetch = FetchType.EAGER)
    private List<Planta> plantasAsociadas;
}
