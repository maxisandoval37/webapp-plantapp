package ar.dev.maxisandoval.webappplantapp.repository;

import ar.dev.maxisandoval.webappplantapp.model.Prospecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProspectoRepository extends JpaRepository<Prospecto, Long> {
}