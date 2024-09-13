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
public class Prospecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nombre;// Por ej: tierra, fertilizante, agua, etc.

    private Double cantidad;//1

    private String unidad;// KG, LITROS, ETC

    @ManyToMany(mappedBy = "prospectosAsociados", fetch = FetchType.EAGER)
    private List<Planta> plantas;
}
