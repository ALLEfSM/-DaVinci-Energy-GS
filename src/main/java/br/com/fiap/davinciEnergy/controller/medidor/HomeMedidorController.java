package br.com.fiap.davinciEnergy.controller.medidor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeMedidorController {

    @GetMapping("/homeMedidor")
    public String home(){
        return "/medidor/mdhome";
    }
}
