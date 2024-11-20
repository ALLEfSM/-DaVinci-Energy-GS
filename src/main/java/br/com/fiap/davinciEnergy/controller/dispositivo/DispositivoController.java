package br.com.fiap.davinciEnergy.controller.dispositivo;

import br.com.fiap.davinciEnergy.model.Dispositivo;
import br.com.fiap.davinciEnergy.model.Tempo;
import br.com.fiap.davinciEnergy.model.Tipos;
import br.com.fiap.davinciEnergy.repository.DispositivoRepository;
import br.com.fiap.davinciEnergy.repository.MedidorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("dispositivo")
public class DispositivoController {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private MedidorRepository medidorRepository;


    @GetMapping("cadastrar")
    public String cadastrar(Dispositivo dispositivo, Model model) {
        model.addAttribute("medidor", medidorRepository.findAll());
        model.addAttribute("tipos", Tipos.values());
        model.addAttribute("tempo", Tempo.values());
        return "dispositivo/cadastrar";
    }

    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Dispositivo dispositivo,
                           BindingResult result,
                           RedirectAttributes redirectAttributes,
                            Model model) {
        if(result.hasErrors()) {

            model.addAttribute("medidor", medidorRepository.findAll());
            model.addAttribute("tipos", Tipos.values());
            model.addAttribute("tempo", Tempo.values());
            return "dispositivo/cadastrar";
        }
        dispositivoRepository.save(dispositivo);
        redirectAttributes.addFlashAttribute
                ("mensagem", "Registrado com sucesso!");
        return "redirect:/dispositivo/cadastrar";
    }

    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("dispositivo", dispositivoRepository.findAll());
        return "dispositivo/listar";
    }

    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {

        model.addAttribute("medidor", medidorRepository.findAll());
        model.addAttribute("tipos", Tipos.values());
        model.addAttribute("tempo", Tempo.values());
        model.addAttribute("dispositivo", dispositivoRepository.findById(id));
        return "dispositivo/editar";
    }

    @PostMapping("editar")
    public String editar(@Valid Dispositivo dispositivo,
                         BindingResult result,
                         RedirectAttributes redirectAttributes,
                         Model model) {
        if(result.hasErrors()) {
            model.addAttribute("medidor", medidorRepository.findAll());
            model.addAttribute("tipos", Tipos.values());
            model.addAttribute("tempo", Tempo.values());
            return "dispositivo/editar";
        }
        dispositivoRepository.save(dispositivo);
        redirectAttributes.addFlashAttribute("mensagem", "Sucesso ao atualizar o cadastro");
        return "redirect:/dispositivo/listar";
    }
    @PostMapping("excluir")
    @Transactional
    public String excluir(Long id, RedirectAttributes redirectAttributes) {
        dispositivoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute
                ("mensagem", "Cadastro removido da base de dados");
        return "redirect:/dispositivo/listar";
    }
}
