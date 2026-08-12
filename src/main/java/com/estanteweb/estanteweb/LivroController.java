package com.estanteweb.estanteweb;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LivroController {

    private List<Livro> getListaLivros() {
        List<Livro> livros = new ArrayList<>();
        livros.add(new Livro(1L, "Dom Casmurro", "Machado de Assis", 24.90, 1899));
        livros.add(new Livro(2L, "A Hora da Estrela", "Clarice Lispector", 22.00, 1977));
        livros.add(new Livro(3L, "O Senhor dos Anéis", "J.R.R. Tolkien", 19.99, 1954));
        livros.add(new Livro(4L, "O Hobbit", "J.R.R. Tolkien", 14.99, 1937));
        livros.add(new Livro(5L, "O Silmarillion", "J.R.R. Tolkien", 24.99, 1977));
        return livros;
    }
    
    @GetMapping("/livros")
    public String getLivros() {
        List<Livro> livros = getListaLivros();

        String acervo = livros.stream()
                .map(l -> l.getTitulo() + ", de " + l.getAutor() + " (R$ " + l.getPreco() + ")")
                .collect(Collectors.joining(" | "));

        return "Acervo EstanteWeb: " + acervo;
    }

    @GetMapping("/livros/total")
    public String getTotalLivros() {
        return "O acervo tem " + getListaLivros().size();
    }
}
