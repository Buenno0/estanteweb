package com.estanteweb.estanteweb;

import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class SugestaoController {

    private final SugestaoRepository sugestaoRepository;

    public SugestaoController(SugestaoRepository sugestaoRepository) {
        this.sugestaoRepository = sugestaoRepository;
    }

    @GetMapping(value = "/sugestoes/nova", produces = "text/html")
    @ResponseBody
    public String novaSugestao() {
        return """
                <!DOCTYPE html>
                <html lang="pt-BR">
                <head>
                    <meta charset="UTF-8">
                    <title>Nova sugestão - EstanteWeb</title>
                </head>
                <body>
                    <h1>Sugira uma leitura</h1>
                    <form method="post" action="/sugestoes">
                        <label for="nomeCliente">Seu nome:</label>
                        <input type="text" id="nomeCliente" name="nomeCliente">

                        <label for="livroSugerido">Livro sugerido:</label>
                        <input type="text" id="livroSugerido" name="livroSugerido">

                        <button type="submit">Enviar sugestão</button>
                    </form>
                </body>
                </html>
                """;
    }

    @PostMapping("/sugestoes")
    public ResponseEntity<String> adicionarSugestao(
            @RequestParam String nomeCliente,
            @RequestParam String livroSugerido) {
        Sugestao sugestao = new Sugestao(nomeCliente, livroSugerido);
        sugestaoRepository.adicionar(sugestao);

        String confirmacao = "Obrigado, " + nomeCliente
                + "! A sugestão do livro " + livroSugerido + " foi recebida.";

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("X-Total-Sugestoes", String.valueOf(sugestaoRepository.total()))
                .body(confirmacao);
    }

    @GetMapping(value = "/sugestoes", produces = "text/html")
    public String listarSugestoes(Model model) {
        var sugestoes = sugestaoRepository.listarTodas();
        model.addAttribute("sugestoes", sugestoes);
        return "sugestoes"; 
        
    }
}
