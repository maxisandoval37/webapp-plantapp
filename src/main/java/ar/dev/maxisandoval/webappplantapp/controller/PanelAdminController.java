package ar.dev.maxisandoval.webappplantapp.controller;

import ar.dev.maxisandoval.webappplantapp.model.Jardinero;
import ar.dev.maxisandoval.webappplantapp.model.Usuario;
import ar.dev.maxisandoval.webappplantapp.service.CustomUserDetailsService;
import ar.dev.maxisandoval.webappplantapp.service.JardineroService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class PanelAdminController {

    private final CustomUserDetailsService customUserDetailsService;
    private final JardineroService jardineroService;

    @GetMapping("/gestorRoles")
    public String gestorRoles(Model model) {
        model.addAttribute("usuarios", customUserDetailsService.listarUsuariosRegistrados());
        return "gestorRoles";
    }

    @GetMapping("/actualizarRolUsuario/{id}")
    public String mostrarFormularioActualizarUsuario(@PathVariable Long id, Model model){
        Usuario usuario = customUserDetailsService.obtenerUsuarioPorId(id);
        model.addAttribute("usuario", usuario);

        return "actualizarRolUsuarioForm";
    }

    @PostMapping("/actualizarRolUsuario/{id}")
    public String actualizarRolUsuario(@PathVariable Long id, @RequestParam(required = false) String rol, @RequestParam(required = false) String especialidad, @RequestParam(required = false) String email) {
        if (rol.equals("ROL_JARDINERO") && especialidad != null && email != null) {

            Jardinero jardineroNuevo = new Jardinero();
            jardineroNuevo.setEspecialidad(especialidad);
            jardineroNuevo.setEmail(email);
            jardineroNuevo.setUsuario(customUserDetailsService.obtenerUsuarioPorId(id));

            Jardinero jardineroGuardado = jardineroService.guardarJardinero(jardineroNuevo);
            customUserDetailsService.actualizarRolUsuarioJardinero(id, jardineroGuardado);
        } else {
            customUserDetailsService.actualizarRolUsuario(id, rol);
        }
        return "redirect:/gestorRoles";
    }

    @GetMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable Long id){
        customUserDetailsService.eliminarUsuario(id);
        return "redirect:/gestorRoles";
    }
}