package br.com.fiap.davinciEnergy.controller.eficiencia;

import br.com.fiap.davinciEnergy.model.Dispositivo;
import br.com.fiap.davinciEnergy.model.Eficiencia;
import br.com.fiap.davinciEnergy.model.Tipos;
import br.com.fiap.davinciEnergy.repository.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("eficiencia")
public class EficienciaController {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    // Método GET para exibir o formulário de cálculo de eficiência
    @GetMapping("media")
    public String calcularMedia(Model model){
        model.addAttribute("dispositivo", dispositivoRepository.findAll());
        return "eficiencia/media";
    }

    // Método POST para processar o cálculo de eficiência
    @PostMapping("media")
    public String calcularMedia(Eficiencia eficiencia, Model model) {
        // Verificar se o dispositivo foi selecionado
        if (eficiencia.getDispositivo() == null || eficiencia.getDispositivo().getId() == null) {
            model.addAttribute("erro", "Dispositivo não foi selecionado.");
            return "eficiencia/media";
        }

        // Buscar o dispositivo no banco de dados
        Dispositivo dispositivo = dispositivoRepository.findById(eficiencia.getDispositivo().getId()).orElse(null);
        if (dispositivo == null) {
            model.addAttribute("erro", "Dispositivo não encontrado.");
            return "eficiencia/media";
        }

        // Configurar o dispositivo no objeto eficiência
        eficiencia.setDispositivo(dispositivo);

        // Calcular a média final (consumo mensal em kWh)
        Double mediaFinal = eficiencia.calcularMediaFinal();

        // Calcular a classificação de eficiência energética com base no tipo de dispositivo e no consumo mensal
        String classificacao = calcularClassificacao(dispositivo.getTipos(), mediaFinal);

        // Adicionar os resultados e o dispositivo ao modelo para exibição na página
        model.addAttribute("mediaFinal", mediaFinal);
        model.addAttribute("classificacao", classificacao);
        model.addAttribute("dispositivo", dispositivo);

        return "eficiencia/media";  // Retorna para a mesma página para mostrar o resultado
    }

    // Método para calcular a classificação de eficiência energética com base no tipo e no consumo
    private String calcularClassificacao(Tipos tipo, double consumoMensal) {
        switch (tipo) {
            case arCondicionado:
                if (consumoMensal <= 100) return "A";
                if (consumoMensal <= 200) return "B";
                if (consumoMensal <= 300) return "C";
                return "D";
            case fogao:
                if (consumoMensal <= 20) return "A";
                if (consumoMensal <= 40) return "B";
                if (consumoMensal <= 60) return "C";
                return "D";
            case microondas:
                if (consumoMensal <= 30) return "A";
                if (consumoMensal <= 60) return "B";
                if (consumoMensal <= 90) return "C";
                return "D";
            case fornoEletrico:
                if (consumoMensal <= 50) return "A";
                if (consumoMensal <= 100) return "B";
                if (consumoMensal <= 150) return "C";
                return "D";
            case Lampada:
                if (consumoMensal <= 5) return "A";
                if (consumoMensal <= 10) return "B";
                if (consumoMensal <= 15) return "C";
                return "D";
            case lavadorRoupa:
                if (consumoMensal <= 80) return "A";
                if (consumoMensal <= 150) return "B";
                if (consumoMensal <= 200) return "C";
                return "D";
            case refrigerador:
                if (consumoMensal <= 50) return "A";
                if (consumoMensal <= 100) return "B";
                if (consumoMensal <= 150) return "C";
                return "D";
            case televisor:
                if (consumoMensal <= 30) return "A";
                if (consumoMensal <= 60) return "B";
                if (consumoMensal <= 90) return "C";
                return "D";
            case ventilador:
                if (consumoMensal <= 15) return "A";
                if (consumoMensal <= 30) return "B";
                if (consumoMensal <= 50) return "C";
                return "D";
            default:
                if (consumoMensal <= 50) return "A";
                if (consumoMensal <= 100) return "B";
                if (consumoMensal <= 200) return "C";
                return "D";
        }
    }
}
