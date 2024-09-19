package ar.dev.maxisandoval.webappplantapp.service;

import ar.dev.maxisandoval.webappplantapp.model.Jardinero;
import ar.dev.maxisandoval.webappplantapp.model.Planta;
import ar.dev.maxisandoval.webappplantapp.repository.JardineroRepository;
import ar.dev.maxisandoval.webappplantapp.repository.PlantaRepository;
import ar.dev.maxisandoval.webappplantapp.repository.ProspectoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlantaService {

    private PlantaRepository plantaRepository;
    private JardineroRepository jardineroRepository;
    private ProspectoRepository prospectoRepository;

    public Planta obtenerPlantaPorId(Long id) {
        return plantaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No se encontró la planta con el id: "+id));
    }

    public List<Planta> listarPlantas() {
        return plantaRepository.findAllByOrderByEspecieIgnoreCaseAsc();
    }

    public Planta guardarPlanta(Planta planta, Long idJardinero, List<Long> idProspectos) {
        Jardinero jardinero = jardineroRepository.findById(idJardinero)
                .orElseThrow(() -> new RuntimeException("No se encontró el jardinero con el id:"
                        +idJardinero+" al momento de guardar la planta"));

        planta.setJardinero(jardinero);

        if (idProspectos !=null) {
            planta.setProspectosAsociados(prospectoRepository.findAllById(idProspectos));
        }

        return plantaRepository.save(planta);
    }

    public void eliminarPlanta(Long id) {
        plantaRepository.deleteById(id);
    }

    public void actualizarPlanta(Long idPlanta, Planta plantaActualizada, Long idJardinero, List<Long> idProspectos) {
        Optional<Planta> plantaOptional = plantaRepository.findById(idPlanta);

        Jardinero jardinero = jardineroRepository.findById(idJardinero)
                .orElseThrow(() -> new RuntimeException("No se encontró el jardinero con el id:"
                        +idJardinero+" al momento de actualizar la planta"));

        if (idProspectos !=null) {
            plantaActualizada.setProspectosAsociados(prospectoRepository.findAllById(idProspectos));
        }

        plantaActualizada.setJardinero(jardinero);

        Planta plantaExistente = construirPlanta(plantaActualizada, plantaOptional);
        plantaRepository.save(plantaExistente);
    }

    private Planta construirPlanta(Planta plantaActualizada, Optional<Planta> plantaOptional) {
        Planta.PlantaBuilder plantaBuilder = Planta.builder();

        plantaOptional.ifPresent(plantaExistente -> { //Si plantaOptional esta presente, se le asigna su contenido a plantaExistente
            plantaBuilder
                    .id(plantaExistente.getId())
                    .colorHojas(plantaActualizada.getColorHojas())
                    .especie(plantaActualizada.getEspecie())
                    .fechaPlantacion(plantaActualizada.getFechaPlantacion())
                    .jardinero(plantaActualizada.getJardinero())
                    .prospectosAsociados(plantaActualizada.getProspectosAsociados());
        });

        return plantaBuilder.build();
    }

}