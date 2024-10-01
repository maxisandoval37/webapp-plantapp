package ar.dev.maxisandoval.webappplantapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "La especialidad no puede estar en blanco")
    private String especialidad;

    @NotBlank(message = "El email no puede estar blanco")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "El formato del email no es válido")
    private String email;

    @OneToMany(mappedBy = "jardinero", fetch = FetchType.EAGER)
    private List<Planta> plantasAsociadas;

    @OneToOne(mappedBy = "jardinero", cascade = CascadeType.ALL)
    private Usuario usuario;
}
