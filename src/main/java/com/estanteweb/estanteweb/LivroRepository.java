package com.estanteweb.estanteweb;

import java.util.ArrayList;
import java.util.List;

public class LivroRepository {
    
    public List<Livro> listarTodos() {
        List<Livro> livros = new ArrayList<>();
        livros.add(new Livro(1L, "<h1>Dom Casmurro</h1>", "Machado de Assis", 24.90, 1899));
        livros.add(new Livro(2L, "A Hora da Estrela", "Clarice Lispector", 22.00, 1977));
        livros.add(new Livro(3L, "O Senhor dos Anéis", "J.R.R. Tolkien", 19.99, 1954));
        livros.add(new Livro(4L, "O Hobbit", "J.R.R. Tolkien", 14.99, 1937));
        livros.add(new Livro(5L, "O Silmarillion", "J.R.R. Tolkien", 24.99, 1977));
        return livros;
    }

    public String contarLivros() {
        return "O acervo tem " + listarTodos().size() + " livros.";
    }
}
