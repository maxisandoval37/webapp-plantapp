package ar.dev.maxisandoval.webappplantapp.service;

import ar.dev.maxisandoval.webappplantapp.model.Jardinero;
import ar.dev.maxisandoval.webappplantapp.model.Usuario;
import ar.dev.maxisandoval.webappplantapp.repository.PlantaRepository;
import ar.dev.maxisandoval.webappplantapp.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PlantaRepository plantaRepository;

    private final String ROL_LECTURA = "ROL_LECTURA";
    private final String ROL_JARDINERO = "ROL_JARDINERO";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("loadUserByUsername: Usuario no encontrado: "+username);
        }

        return User.withUsername(usuario.getUsername())
                .password(usuario.getContrasena())
                .authorities(List.of(new SimpleGrantedAuthority(usuario.getRol())))
                .build();
    }

    public Usuario guardarUsuario(Usuario usuario) {

        Usuario usuarioExistente = usuarioRepository.findByUsername(usuario.getUsername());

        if (usuarioExistente != null) {
            throw new DataIntegrityViolationException("El usuario ya se encuentra registrado!");
        }

        usuario.setRol(ROL_LECTURA);
        usuario.setContrasena(passwordEncoder().encode(usuario.getContrasena()));

        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarRolUsuario(Long id, String nuevoRol) {
        Usuario usuario = obtenerUsuarioPorId(id);

        if (usuario == null) {
            throw new UsernameNotFoundException("actualizarRolUsuario: Usuario no encontrado:" + id);
        }

        usuario.setRol(nuevoRol);
        return usuarioRepository.save(usuario);
    }

    public void actualizarRolUsuarioJardinero(Long id, Jardinero jardinero) {
        Usuario usuario = obtenerUsuarioPorId(id);

        if (usuario == null) {
            throw new UsernameNotFoundException("actualizarRolUsuarioJardinero: Usuario no encontrado:" + id);
        }

        usuario.setRol(ROL_JARDINERO);
        usuario.setJardinero(jardinero);
        usuarioRepository.save(usuario);
    }

    @Transactional //Se concreta la operación si todas las operaciones salen ok.
    public void eliminarUsuario(Long id) {
        Optional<Usuario> usuarioActual = usuarioRepository.findById(id);
        String usernameActual = SecurityContextHolder.getContext().getAuthentication().getName();

        if (usuarioActual.isPresent()) {

            //Sesión Activa: No permitimos eliminar el usuario que tiene la sesión actual abierta
            if (usernameActual.equals(usuarioActual.get().getUsername())) {
                throw new IllegalArgumentException("No se puede eliminar el asuario actualmente autenticado");
            }

            //Es Jardinero: Borramos las plantas asociadas que tiene el jardinero
            if (usuarioActual.get().getJardinero() != null) {
                plantaRepository.deleteByJardinero(usuarioActual.get().getJardinero());
            }
        }

        usuarioRepository.deleteById(id);
    }

    public List<Usuario> listarUsuariosRegistrados() {
        return usuarioRepository.findAll();
    }

    private Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontró el usuario: " + id));
    }

    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
