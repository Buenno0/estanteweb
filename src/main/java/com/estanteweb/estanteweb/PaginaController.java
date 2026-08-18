package com.estanteweb.estanteweb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaginaController {

    @GetMapping("/pagina")
    public String getPagina() {
        return "EstanteWeb: livros usados com história";
    }

    @GetMapping("/sobre")
    public String getSobre() {
        return "Essa empresa é especializada em livros usados, com uma variedade de títulos disponíveis para compra.";
    }
}
 