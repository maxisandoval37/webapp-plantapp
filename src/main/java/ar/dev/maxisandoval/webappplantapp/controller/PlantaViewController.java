package ar.dev.maxisandoval.webappplantapp.controller;

import ar.dev.maxisandoval.webappplantapp.model.Planta;
import ar.dev.maxisandoval.webappplantapp.service.JardineroService;
import ar.dev.maxisandoval.webappplantapp.service.PlantaService;
import ar.dev.maxisandoval.webappplantapp.service.ProspectoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@AllArgsConstructor
public class PlantaViewController {

    private final PlantaService plantaService;
    private final JardineroService jardineroService;
    private final ProspectoService prospectoService;

    @GetMapping("/plantas")
    public String listarPlantas(Model model) {
        model.addAttribute("plantas" , plantaService.listarPlantas());
        return "listaPlantas";
    }

    @GetMapping("/agregarPlanta")
    public String mostrarFormularioAgregarPlanta(Model model) {
        model.addAttribute("jardineros" , jardineroService.listarJardineros());
        model.addAttribute("prospectos", prospectoService.listarProspectos());
        model.addAttribute("planta", new Planta());

        return "agregarPlantaForm";
    }

    @PostMapping("/guardarPlanta")
    public String guardarPlanta(@ModelAttribute Planta planta, @RequestParam Long idJardinero, @RequestParam(required = false) List<Long> idProspectos) {
        plantaService.guardarPlanta(planta, idJardinero, idProspectos);
        return "redirect:/plantas";
    }

    @GetMapping("/actualizarPlanta/{id}")
    public String mostrarFormularioActualizarPlanta(@PathVariable Long id, Model model) {
        model.addAttribute("planta", plantaService.obtenerPlantaPorId(id));
        model.addAttribute("prospectos", prospectoService.listarProspectos());
        model.addAttribute("jardineros" , jardineroService.listarJardineros());

        return "actualizarPlantaForm";
    }

    @PostMapping("/actualizarPlanta/{idPlanta}")
    public String actualizarPlanta(@PathVariable Long idPlanta, @ModelAttribute Planta plantaActualizada, @RequestParam Long idJardinero, @RequestParam List<Long> idProspectos) {
        plantaService.actualizarPlanta(idPlanta, plantaActualizada, idJardinero, idProspectos);
        return "redirect:/plantas";
    }

    @GetMapping("eliminarPlanta/{id}")
    public String eliminarPlanta(@PathVariable Long id) {
        plantaService.eliminarPlanta(id);
        return "redirect:/plantas";
    }
}
