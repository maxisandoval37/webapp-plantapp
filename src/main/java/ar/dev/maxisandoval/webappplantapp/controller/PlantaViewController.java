package ar.dev.maxisandoval.webappplantapp.controller;

import ar.dev.maxisandoval.webappplantapp.model.Jardinero;
import ar.dev.maxisandoval.webappplantapp.model.Planta;
import ar.dev.maxisandoval.webappplantapp.repository.UsuarioRepository;
import ar.dev.maxisandoval.webappplantapp.service.CustomUserDetailsService;
import ar.dev.maxisandoval.webappplantapp.service.JardineroService;
import ar.dev.maxisandoval.webappplantapp.service.PlantaService;
import ar.dev.maxisandoval.webappplantapp.service.ProspectoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class PlantaViewController {
    private final UsuarioRepository usuarioRepository;

    private final PlantaService plantaService;
    private final JardineroService jardineroService;
    private final ProspectoService prospectoService;

    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping("/plantas")
    public String listarPlantas(Model model) {
        List<Planta> plantas;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        mostrarRolesUsuarioActual(authentication);
        String username = authentication.getName();

        Jardinero jardinero = usuarioRepository.findByUsername(username).getJardinero();

        if (jardinero != null) {
            plantas = jardinero.getPlantasAsociadas();
        }
        else {
            plantas = plantaService.listarPlantas();
        }

        model.addAttribute("plantas" , plantas);
        model.addAttribute("userService", customUserDetailsService);

        return "listaPlantas";
    }

    private void mostrarRolesUsuarioActual(Authentication authentication) {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String rol = authority.getAuthority();
            log.info("Rol actual: "+rol);
        }
    }

    @GetMapping("/agregarPlanta")
    public String mostrarFormularioAgregarPlanta(Model model) {
        model.addAttribute("jardineros" , jardineroService.listarJardineros());
        model.addAttribute("prospectos", prospectoService.listarProspectos());
        model.addAttribute("usuarioConJardinero", customUserDetailsService.listarUsuariosRegistradosConJardineros());
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
        //model.addAttribute("userService", customUserDetailsService);

        return "actualizarPlantaForm";
    }

    @PostMapping("/actualizarPlanta/{idPlanta}")
    public String actualizarPlanta(@PathVariable Long idPlanta, @ModelAttribute Planta plantaActualizada, @RequestParam Long idJardinero, @RequestParam(required = false) List<Long> idProspectos) {
        plantaService.actualizarPlanta(idPlanta, plantaActualizada, idJardinero, idProspectos);
        return "redirect:/plantas";
    }

    @GetMapping("eliminarPlanta/{id}")
    public String eliminarPlanta(@PathVariable Long id) {
        plantaService.eliminarPlanta(id);
        return "redirect:/plantas";
    }
}
