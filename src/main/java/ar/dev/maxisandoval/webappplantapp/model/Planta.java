package ar.dev.maxisandoval.webappplantapp.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data //toString, equals, hashcode, getters y setters
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Planta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String especie;

    private String colorHojas;

    private LocalDate fechaPlantacion;

    @ManyToOne
    @JoinColumn(name = "jardinero_id")
    @ToString.Exclude //solucion loop infinito lombok
    private Jardinero jardinero;

    @ManyToMany
    @JoinTable(name = "Planta_Prospecto",
            joinColumns = @JoinColumn(name = "planta_id"),
            inverseJoinColumns = @JoinColumn(name = "prospecto_id"))
    @Builder.Default
    @ToString.Exclude //solucion loop infinito lombok
    private List<Prospecto> prospectosAsociados = new ArrayList<>();
}