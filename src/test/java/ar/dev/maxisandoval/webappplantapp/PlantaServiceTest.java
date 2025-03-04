package ar.dev.maxisandoval.webappplantapp;

import  ar.dev.maxisandoval.webappplantapp.model.*;
import  ar.dev.maxisandoval.webappplantapp.service.*;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class PlantaServiceTest extends BaseTest {

    private final PlantaService plantaService;
    private final JardineroService jardineroService;
    private final ProspectoService prospectoService;

    private Jardinero jardineroGuardado;
    private Prospecto prospectoGuardada;
    private Planta plantaBasica;

    @BeforeEach
    void setup() {
        Jardinero jardinero = new Jardinero();
        jardinero.setEspecialidad("Suculentas");
        jardinero.setEmail("juan.perez@example.com");

        jardineroGuardado = jardineroService.guardarJardinero(jardinero);

        Prospecto prospecto = new Prospecto();
        prospecto.setNombre("Tierra");
        prospecto.setCantidad(5d);
        prospecto.setUnidad("KG");
        prospectoGuardada = prospectoService.guardarProspecto(prospecto);

        plantaBasica = new Planta();
        plantaBasica.setEspecie("suculenta");
        plantaBasica.setColorHojas("verdes");
        plantaBasica.setFechaPlantacion(LocalDate.now());
    }

    @Test
    void testGuardarPlantaSinProspectos() {
        plantaBasica.setJardinero(jardineroGuardado);
        Planta plantaGuardada = plantaService.guardarPlanta(plantaBasica, jardineroGuardado.getId(), null);

        assertNotNull(plantaGuardada.getId());
        assertEquals("suculenta", plantaGuardada.getEspecie());
        assertEquals("verdes", plantaGuardada.getColorHojas());
        assertEquals(jardineroGuardado.getId(), plantaGuardada.getJardinero().getId());
    }

    @Test
    void testGuardarPlantaConProspectos() {
        plantaBasica.setFechaPlantacion(LocalDate.now());
        plantaBasica.setJardinero(jardineroGuardado);

        List<Long> idsProspectosAsociadosList = new ArrayList<>();
        idsProspectosAsociadosList.add(prospectoGuardada.getId());

        // Guardamos la planta junto con el prospecto
        Planta plantaGuardada = plantaService.guardarPlanta(plantaBasica, jardineroGuardado.getId(), idsProspectosAsociadosList);

        assertNotNull(plantaGuardada.getId());
        assertEquals("suculenta", plantaGuardada.getEspecie());
        assertEquals("verdes", plantaGuardada.getColorHojas());
        assertEquals(jardineroGuardado.getId(), plantaGuardada.getJardinero().getId());
        assertFalse(plantaGuardada.getProspectosAsociados().isEmpty());
    }

    @Test
    void testListarPlantas() {
        List<Planta> plantas = plantaService.listarPlantas();
        assertFalse(plantas.isEmpty());
    }

    @Test
    void testObtenerPlantaPorId() {
        Long idPlanta = 2L;
        Planta planta = plantaService.obtenerPlantaPorId(idPlanta);

        assertNotNull(planta);
        assertEquals(idPlanta, planta.getId());
    }

    @Test
    void testActualizarPlantaSinProspectos() {
        Long idPlanta = 1L;

        Planta plantaActualizada = new Planta();
        plantaActualizada.setEspecie("Suculenta");
        plantaActualizada.setColorHojas("Verdes");
        plantaActualizada.setFechaPlantacion(LocalDate.now());
        plantaActualizada.setJardinero(jardineroGuardado);

        plantaService.actualizarPlanta(idPlanta, plantaActualizada, jardineroGuardado.getId() ,null);

        Planta plantaDespuesDeActualizar = plantaService.obtenerPlantaPorId(idPlanta);

        assertEquals(plantaActualizada.getColorHojas(), plantaDespuesDeActualizar.getColorHojas());
        assertEquals(plantaActualizada.getEspecie(), plantaDespuesDeActualizar.getEspecie());
        assertEquals(plantaActualizada.getJardinero().getId(), plantaDespuesDeActualizar.getJardinero().getId());
    }

    @Test
    void testEliminarPlanta() {
        Long idPlanta = 1L;

        //Buscamos si existe la planta antes de eliminarla
        assertNotNull(plantaService.obtenerPlantaPorId(idPlanta));

        plantaService.eliminarPlanta(idPlanta);

        Exception exception = assertThrows(RuntimeException.class, () -> plantaService.obtenerPlantaPorId(idPlanta));

        String expectedMessage = "No se encontró la planta con el id: " + idPlanta;
        String actualMessage = exception.getMessage();

        //Corroboramos que efectivamente fue eliminada
        assertTrue(actualMessage.contains(expectedMessage));
    }
}