package ar.dev.maxisandoval.webappplantapp.service;

import ar.dev.maxisandoval.webappplantapp.model.Prospecto;
import ar.dev.maxisandoval.webappplantapp.repository.ProspectoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ProspectoService {

    ProspectoRepository prospectoRepository;

    public Prospecto obtenerProspectoPorId(Long id) {
        return prospectoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No se encontró el prospecto"+id));
    }

    public List<Prospecto> listarProspectos() {
        return prospectoRepository.findAll();
    }

    public Prospecto guardarProspecto(Prospecto prospecto) {
        return prospectoRepository.save(prospecto);
    }

    public void eliminarProspecto(Long id) {
        prospectoRepository.deleteById(id);
    }
}
