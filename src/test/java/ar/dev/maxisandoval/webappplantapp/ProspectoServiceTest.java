package ar.dev.maxisandoval.webappplantapp;

import ar.dev.maxisandoval.webappplantapp.model.Prospecto;
import ar.dev.maxisandoval.webappplantapp.service.ProspectoService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class ProspectoServiceTest extends BaseTest {

    private final ProspectoService prospectoService;

    private Prospecto prospectoGuardado;

    @BeforeEach
    void setup () {
        Prospecto prospecto = new Prospecto();
        prospecto.setNombre("Tierra");
        prospecto.setCantidad(5.0d);
        prospecto.setUnidad("KG");

        prospectoGuardado = prospectoService.guardarProspecto(prospecto);
    }

    @Test
    void testGuardarProspecto() {
        assertNotNull(prospectoGuardado.getId());
        assertEquals("Tierra", prospectoGuardado.getNombre());
        assertEquals(5d, prospectoGuardado.getCantidad());
        assertEquals("KG", prospectoGuardado.getUnidad());
    }

    @Test
    void testListarProspectos() {
        List<Prospecto> prospectos = prospectoService.listarProspectos();
        assertFalse(prospectos.isEmpty());
    }

    @Test
    void testObtenerProspectoPorId() {
        Long prospectoId = 2L;
        Prospecto prospecto = prospectoService.obtenerProspectoPorId(prospectoId);

        assertNotNull(prospecto);
        assertEquals(prospectoId, prospecto.getId());
    }

    @Test
    void testEliminarProspecto() {
        Long prospectoId = 1L;

        //Buscamos si existe el prospecto antes de eliminarlo
        assertNotNull(prospectoService.obtenerProspectoPorId(prospectoId));

        prospectoService.eliminarProspecto(prospectoId);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            prospectoService.obtenerProspectoPorId(prospectoId);
        });

        String expectedMessage = "No se encontró el prospecto con el id: " + prospectoId;
        String actualMessage = exception.getMessage();

        //Corroboramos que efectivamente fue eliminado
        assertTrue(actualMessage.contains(expectedMessage));
    }
}