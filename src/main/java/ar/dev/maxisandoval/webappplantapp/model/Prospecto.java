package ar.dev.maxisandoval.webappplantapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Entity
@Data //toString, equals, hashcode, getters y los setters
@NoArgsConstructor
@AllArgsConstructor
public class Prospecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombre;// Por ej: tierra, fertilizante, agua, etc.

    @NotNull(message = "La cantidad no puede ser nula")
    private Double cantidad;//1

    @NotBlank(message = "La unidad no puede estar en blanco")
    private String unidad;// KG, LITROS, ETC

    @ManyToMany(mappedBy = "prospectosAsociados", fetch = FetchType.EAGER)
    private List<Planta> plantas = new ArrayList<>();
}
