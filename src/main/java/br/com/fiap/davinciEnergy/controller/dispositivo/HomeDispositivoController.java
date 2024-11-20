package br.com.fiap.davinciEnergy.controller.dispositivo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeDispositivoController {

    @GetMapping("/homeDispositivo")
    public String home(){
        return "/dispositivo/dispositivohome";
    }
}
