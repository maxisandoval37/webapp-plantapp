package ar.dev.maxisandoval.webappplantapp.controller;

import ar.dev.maxisandoval.webappplantapp.model.Prospecto;
import ar.dev.maxisandoval.webappplantapp.service.ProspectoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class ProspectosViewController {

    private final ProspectoService prospectoService;

    @GetMapping("/prospectos")
    public String listarProspectos(Model model) {
        model.addAttribute("prospectos", prospectoService.listarProspectos());

        return "listaProspectos";
    }

    @GetMapping("/agregarProspecto")
    public String mostrarFormularioNuevoProspecto(Model model) {
        model.addAttribute("prospecto", new Prospecto());

        return "agregarProspectoForm";
    }

    @PostMapping("/guardarProspecto")
    public String guardarProspecto(@ModelAttribute Prospecto prospecto) {
        prospectoService.guardarProspecto(prospecto);
        return "redirect:/prospectos";
    }

}