package br.com.fiap.davinciEnergy.controller.erros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class CustomErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    @Autowired
    public CustomErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public String handleError(WebRequest webRequest, Model model) {
        // Obtém os atributos do erro
        var errorAttributes = this.errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE));
        int status = (int) errorAttributes.getOrDefault("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        model.addAttribute("status", status);
        model.addAttribute("error", errorAttributes.getOrDefault("error", "Erro"));
        model.addAttribute("message", errorAttributes.getOrDefault("message", "Erro inesperado."));

        if (status == HttpStatus.FORBIDDEN.value()) {
            return "errors/403"; // Certifique-se de ter a página errors/403.html no diretório templates
        } else if (status == HttpStatus.NOT_FOUND.value()) {
            return "errors/404"; // Certifique-se de ter a página errors/404.html no diretório templates
        } else {
            return "errors/500"; // Certifique-se de ter a página errors/500.html no diretório templates
        }
    }
}
