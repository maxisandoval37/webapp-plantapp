package ar.dev.maxisandoval.webappplantapp.repository;

import ar.dev.maxisandoval.webappplantapp.model.Jardinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JardineroRepository extends JpaRepository <Jardinero, Long> {
}