package com.estanteweb.estanteweb;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

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
    public String livrosHtml(@RequestParam(required = false) String ordem, Model model) {
        if (ordem == null) {
            model.addAttribute("livros", livroRepository.listarTodos());
        } else {
            model.addAttribute("livros", livroRepository.listarOrdenadoPor(ordem));
        }
        return "livros";
    }

    @GetMapping("/livros/total")
    @ResponseBody
    public String getTotalLivros() {
        return livroRepository.contarLivros();
    }

    @GetMapping("/livros/novo")
    public String novoLivro() {
        return "livro-novo";
    }

    @PostMapping("/livros")
    public String criarLivro(@ModelAttribute Livro livro, Model model) {
        
        if (livro.getAutor().isBlank() || livro.getTitulo().isBlank()) {
            model.addAttribute("erro", "Autor e título são obrigatórios!");
            return "livro-novo";

        }

        livroRepository.salvar(livro);
        return "redirect:/livros/html";
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

    @GetMapping("/livros/{id}")
    public String livroPorId(@PathVariable Long id, Model model) {
        Livro livro = livroRepository.buscarPorId(id);

        if (livro == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        model.addAttribute("livro", livro);
        model.addAttribute("doMesmoAutor", livroRepository.listarDoMesmoAutor(livro.getAutor(), id));
        return "livro";
    }

    @GetMapping("/livros/quem-acessa")
    @ResponseBody
    public String quemAcessa(@RequestHeader("User-Agent") String userAgent) {
        return "Acesso realizado por: " + userAgent;
    }
}
