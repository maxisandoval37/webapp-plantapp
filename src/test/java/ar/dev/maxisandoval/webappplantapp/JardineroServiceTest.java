package ar.dev.maxisandoval.webappplantapp;

import ar.dev.maxisandoval.webappplantapp.model.Jardinero;
import ar.dev.maxisandoval.webappplantapp.service.JardineroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class JardineroServiceTest {

    @Autowired
    private JardineroService jardineroService;

    private Jardinero jardineroGuardado;

    @BeforeEach
    void setup() {
        Jardinero jardinero = new Jardinero();
        jardinero.setEspecialidad("Suculentas");
        jardinero.setEmail("juandiaz@example.com");

        jardineroGuardado = jardineroService.guardarJardinero(jardinero);
    }

    @Test
    void testGuardarJardinero() {
        assertNotNull(jardineroGuardado.getId());
        assertEquals("Suculentas", jardineroGuardado.getEspecialidad());
        assertEquals("juandiaz@example.com", jardineroGuardado.getEmail());
    }
}