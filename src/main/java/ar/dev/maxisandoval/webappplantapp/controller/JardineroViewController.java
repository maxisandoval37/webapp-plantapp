package ar.dev.maxisandoval.webappplantapp.controller;

import ar.dev.maxisandoval.webappplantapp.service.CustomUserDetailsService;
import ar.dev.maxisandoval.webappplantapp.service.JardineroService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class JardineroViewController {

    private final JardineroService jardineroService;
    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping("/jardineros")
    public String listarJardineros(Model model) {
        model.addAttribute("jardineros", jardineroService.listarJardineros());
        model.addAttribute("userService", customUserDetailsService);

        return "listaJardineros";
    }

}
