package com.estanteweb.estanteweb;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LivroController {

    @Autowired 
    private LivroRepository livroRepository ;

    @GetMapping("/livros")
    @ResponseBody
    public String getLivros() {
        var livros = livroRepository.listarTodos();

        String acervo = livros.stream()
                .map(l -> l.getTitulo() + ", de " + l.getAutor() + " (R$ " + l.getPreco() + ")")
                .collect(Collectors.joining(" | "));

        return "Acervo EstanteWeb: " + acervo;
    }

    @GetMapping("/livros/html")
    public String livrosHtml(Model model) {
        model.addAttribute("livros", livroRepository.listarTodos());
        return "livros";
    }

    @GetMapping("/livros/total")
    @ResponseBody
    public String getTotalLivros() {
        return livroRepository.contarLivros();
    }

    @GetMapping("/livros/busca")
    public ResponseEntity<String> precoMaximo(
            @RequestParam(defaultValue = "999.00") double precoMaximo) {
        List<Livro> livrosEncontrados = livroRepository.listarAtePreco(precoMaximo);

        if (livrosEncontrados.isEmpty()) {
            String mensagem = "Nenhum livro encontrado com preço até R$ " + precoMaximo + ".";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }

        String lista = livrosEncontrados.stream()
                .map(livro -> livro.getTitulo() + ", de " + livro.getAutor()
                        + " (R$ " + livro.getPreco() + ")")
                .collect(Collectors.joining(" | "));
        String resposta = "Livros com preço até R$ " + precoMaximo + ": " + lista;

        return ResponseEntity.ok()
                .header("X-Total-Encontrados", String.valueOf(livrosEncontrados.size()))
                .body(resposta);
    }

    @GetMapping("/livros/quem-acessa")
    @ResponseBody
    public String quemAcessa(@RequestHeader("User-Agent") String userAgent) {
        return "Acesso realizado por: " + userAgent;
    }
}
