package ar.dev.maxisandoval.webappplantapp.repository;

import ar.dev.maxisandoval.webappplantapp.model.Jardinero;
import ar.dev.maxisandoval.webappplantapp.model.Planta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlantaRepository extends JpaRepository <Planta, Long> {

    Optional<Planta> findEspecie(String especie);

    @Query("SELECT p FROM Planta p ORDER BY LOWER(p.especie) ASC")
    List<Planta> findAllByOrderByEspecieIgnoreCaseAsc();

    void deleteByJardinero(Jardinero jardinero);

    void deleteByColorHojas(String colorHojas);
}
