package ar.dev.maxisandoval.webappplantapp.service;

import ar.dev.maxisandoval.webappplantapp.model.Jardinero;
import ar.dev.maxisandoval.webappplantapp.repository.JardineroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class JardineroService {

    private JardineroRepository jardineroRepository;

    public Jardinero obtenerJardineroPorId(Long id){
        return jardineroRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No se encontró el jardinero: "+id));
    }

    public List<Jardinero> listarJardineros() {
        return jardineroRepository.findAll();
    }

    public Jardinero guardarJardinero(Jardinero jardinero) {
        return jardineroRepository.save(jardinero);
    }
}