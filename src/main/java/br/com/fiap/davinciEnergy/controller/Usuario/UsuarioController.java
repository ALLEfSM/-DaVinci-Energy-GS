package br.com.fiap.davinciEnergy.controller.Usuario;

import br.com.fiap.davinciEnergy.dto.UserForm;
import br.com.fiap.davinciEnergy.repository.RoleRepository;
import br.com.fiap.davinciEnergy.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("registrar")
    public String cadastrar(Model model){
        model.addAttribute("usuario", new UserForm());
        model.addAttribute("roles", roleRepository.findAll());
        return "usuario/form";
    }

    @PostMapping("registrar")
    public String cadastrar(@Valid UserForm userForm, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("role", roleRepository.findAll());
            return "usuario/form";
        }
        usuarioService.salvar(userForm.getUsername(),
                passwordEncoder.encode(userForm.getPassword()), userForm.getRoles());
        return "redirect:/login";
    }

}