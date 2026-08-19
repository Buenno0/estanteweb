package com.estanteweb.estanteweb;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping(value = "/contato", produces = "text/html")
    public String getContato() {
        return """
                <!DOCTYPE html>
                <html lang="pt-BR">
                <head>
                    <meta charset="UTF-8">
                    <title>Contato - EstanteWeb</title>
                </head>
                <body>
                    <h1>Entre em contato</h1>
                    <form method="post" action="/contato">
                        <label for="nome">Nome:</label>
                        <input type="text" id="nome" name="nome">

                        <label for="mensagem">Mensagem:</label>
                        <textarea id="mensagem" name="mensagem"></textarea>

                        <button type="submit">Enviar</button>
                    </form>
                </body>
                </html>
                """;
    }

    @PostMapping("/contato")
    public ResponseEntity<String> postContato(
            @RequestParam String nome,
            @RequestParam String mensagem) {
        if (mensagem.isBlank()) {
            return ResponseEntity.badRequest()
                    .body("A mensagem não pode ficar em branco.");
        }

        return ResponseEntity.ok("Obrigado, " + nome + "! Sua mensagem foi recebida.");
    }
}
