package ar.dev.maxisandoval.webappplantapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "La especie no puede estar en blanco")
    private String especie;

    @NotBlank(message = "El color de las hojas no pueden estar en blanco")
    private String colorHojas;

    @NotNull(message = "La fecha de plantación no puede ser nula")
    private LocalDate fechaPlantacion;

    @ManyToOne
    @JoinColumn(name = "jardinero_id")
    @ToString.Exclude //solucion loop infinito lombok
    @NotNull(message = "El jardinero no puede estar ser nulo")
    private Jardinero jardinero;

    @ManyToMany
    @JoinTable(name = "Planta_Prospecto",
            joinColumns = @JoinColumn(name = "planta_id"),
            inverseJoinColumns = @JoinColumn(name = "prospecto_id"))
    @Builder.Default
    @ToString.Exclude //solucion loop infinito lombok
    private List<Prospecto> prospectosAsociados = new ArrayList<>();
}