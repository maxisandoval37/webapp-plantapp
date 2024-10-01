package ar.dev.maxisandoval.webappplantapp.service;

import ar.dev.maxisandoval.webappplantapp.model.Usuario;
import ar.dev.maxisandoval.webappplantapp.repository.PlantaRepository;
import ar.dev.maxisandoval.webappplantapp.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
