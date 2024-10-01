package ar.dev.maxisandoval.webappplantapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data //toString, equals, hashcode, getters y los setters
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;
    private String contrasena;
    private String rol;
    private String nombre;
    private String apellido;

    //si borro al usuario, tambien borro al jardinero
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Jardinero jardinero;
}