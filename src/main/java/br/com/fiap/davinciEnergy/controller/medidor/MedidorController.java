package br.com.fiap.davinciEnergy.controller.medidor;


import br.com.fiap.davinciEnergy.model.Medidor;
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
@RequestMapping("medidor")
public class MedidorController {

    @Autowired
    private MedidorRepository medidorRepository;

    @Autowired
    private DispositivoRepository dispositivoRepository;


    @GetMapping("registrar")
    public String registrar(Medidor medidor, Model model) {
        return "medidor/registrar";
    }

    @PostMapping("registrar")
    @Transactional
    public String registrar(@Valid Medidor medidor,
                            BindingResult result,
                            RedirectAttributes redirectAttributes,
                            Model model) {
        if(result.hasErrors()) {

            return "medidor/registrar";
        }
        medidorRepository.save(medidor);
        redirectAttributes.addFlashAttribute
                ("mensagem", "Registrado com sucesso!");
        return "redirect:/medidor/registrar";
    }

    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("medidor", medidorRepository.findAll());
        return "medidor/listar";
    }

    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("medidor", medidorRepository.findById(id));
        return "medidor/editar";
    }

    @PostMapping("editar")
    public String editar(@Valid Medidor medidor,
                         BindingResult result,
                         RedirectAttributes redirectAttributes,
                         Model model) {
        if(result.hasErrors()) {
            return "medidor/editar";
        }
        medidorRepository.save(medidor);
        redirectAttributes.addFlashAttribute("mensagem", "Sucesso ao atualizar o cadastro");
        return "redirect:/medidor/listar";
    }
    @PostMapping("excluir")
    @Transactional
    public String excluir(Long id, RedirectAttributes redirectAttributes) {
        dispositivoRepository.deleteById(id);
        medidorRepository.deleteById(id);
        redirectAttributes.addFlashAttribute
                ("mensagem", "Cadastro removido da base de dados");
        return "redirect:/medidor/listar";
    }
}
