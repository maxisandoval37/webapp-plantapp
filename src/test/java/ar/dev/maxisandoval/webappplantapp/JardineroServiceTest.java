package ar.dev.maxisandoval.webappplantapp;

import ar.dev.maxisandoval.webappplantapp.model.Jardinero;
import ar.dev.maxisandoval.webappplantapp.service.JardineroService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class JardineroServiceTest extends BaseTest {

    private final JardineroService jardineroService;

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

    @Test
    void testListarJardineros() {
        List<Jardinero> jardineros = jardineroService.listarJardineros();
        assertFalse(jardineros.isEmpty());
    }

    @Test
    void testObtenerJardineroPorId() {
        Long idJardinero = 1L;
        Jardinero jardinero = jardineroService.obtenerJardineroPorId(idJardinero);

        assertNotNull(jardinero);
        assertEquals(idJardinero, jardinero.getId());
    }
}