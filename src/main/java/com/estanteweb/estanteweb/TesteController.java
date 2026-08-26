package com.estanteweb.estanteweb;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TesteController {

    @GetMapping("/ola")
    public String ola(Model model) {
        String nomeLoja = "EstanteWeb";
        String anoFundacao = "1998";
        model.addAttribute("nomeLoja", nomeLoja);
        model.addAttribute("anoFundacao", anoFundacao);
        return "ola";
    }
    

}
