package com.estanteweb.estanteweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PaginaController {

    @GetMapping("/")
    @ResponseBody
    public String getPagina() {
        return "EstanteWeb: livros usados com história";
    }

    @GetMapping("/sobre")
    @ResponseBody
    public String getSobre() {
        return "Essa empresa é especializada em livros usados, com uma variedade de títulos disponíveis para compra.";
    }

    @GetMapping("/contato")
    public String getContato(Model model) {
        return "contato";
    }

    @PostMapping("/contato")
    public String postContato(
            @RequestParam String nome,
            @RequestParam String mensagem,
            Model model) {
        if (mensagem.isBlank()) {
            model.addAttribute("erro", "A mensagem não pode ficar em branco.");
            model.addAttribute("nome", nome);
            return "contato";
        }

        return "redirect:/contato/sucesso";
    }

    @GetMapping("/contato/sucesso")
    public String contatoSucesso() {
        return "contato-sucesso";
    }
}
