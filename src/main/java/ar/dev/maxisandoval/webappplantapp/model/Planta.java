package ar.dev.maxisandoval.webappplantapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data //toString, equals, hashcode, getters y los setters
@NoArgsConstructor
@AllArgsConstructor
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
    private List<Prospecto> prospectosAsociados;
}