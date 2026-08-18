package com.estanteweb.estanteweb;

import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LivroController {

    private LivroRepository livroRepository = new LivroRepository();

   
    
    @GetMapping("/livros")
    public String getLivros() {
        var livros = livroRepository.listarTodos();

        String acervo = livros.stream()
                .map(l -> l.getTitulo() + ", de " + l.getAutor() + " (R$ " + l.getPreco() + ")")
                .collect(Collectors.joining(" | "));

        return "Acervo EstanteWeb: " + acervo;
    }

    @GetMapping("/livros/total")
    public String getTotalLivros() {
        return livroRepository.contarLivros();
    }
}
