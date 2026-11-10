package com.estanteweb.estanteweb;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SugestaoController {

    private final SugestaoRepository sugestaoRepository;

    public SugestaoController(SugestaoRepository sugestaoRepository) {
        this.sugestaoRepository = sugestaoRepository;
    }

    @GetMapping("/sugestoes/nova")
    public String novaSugestao(Model model) {
        return "sugestao-nova";
    }

    @PostMapping("/sugestoes")
    public String adicionarSugestao(
            @RequestParam String nomeCliente,
            @RequestParam String livroSugerido,
            Model model) {
        if (nomeCliente.isBlank() || livroSugerido.isBlank()) {
            model.addAttribute("erro", "Preencha seu nome e o livro sugerido.");
            model.addAttribute("nomeCliente", nomeCliente);
            model.addAttribute("livroSugerido", livroSugerido);
            return "sugestao-nova";
        }

        Sugestao sugestao = new Sugestao(nomeCliente, livroSugerido);
        sugestaoRepository.salvar(sugestao);

        return "redirect:/sugestoes?enviada=true";
    }

    @GetMapping("/sugestoes")
    public String listarSugestoes(
            @RequestParam(required = false) Boolean enviada,
            Model model) {
        var sugestoes = sugestaoRepository.listarTodas();
        model.addAttribute("sugestoes", sugestoes);
        model.addAttribute("enviada", Boolean.TRUE.equals(enviada));
        return "sugestoes"; 
    }

    @GetMapping("/sugestoes/{id}")
    public String sugestaoPorId(@PathVariable Long id, Model model) {
        Sugestao sugestao = sugestaoRepository.buscarPorId(id);
        if (sugestao == null) {
            return "redirect:/sugestoes";
        }
        model.addAttribute("sugestao", sugestao);
        return "sugestao";
    }

    @GetMapping("/sugestoes/{id}/editar")
    public String editarSugestao(@PathVariable Long id, Model model) {
        Sugestao sugestao = sugestaoRepository.buscarPorId(id);
        model.addAttribute("sugestao", sugestao);
        return "sugestao-editar";
    }

    @PostMapping("/sugestoes/{id}/salvar")
    public String salvarSugestaoEditada(
            @PathVariable Long id,
            @RequestParam String nomeCliente,
            @RequestParam String livroSugerido,
            Model model) {

        if (nomeCliente.isBlank() || livroSugerido.isBlank()) {
            model.addAttribute("erro", "Preencha seu nome e o livro sugerido.");
            model.addAttribute("sugestao", new Sugestao(id, nomeCliente, livroSugerido));
            return "sugestao-editar";
        }

        Sugestao sugestao = new Sugestao(id, nomeCliente, livroSugerido);
        sugestaoRepository.editar(id, sugestao);

        return "redirect:/sugestoes/" + id;
    }

    @PostMapping("/sugestoes/{id}/deletar")
    public String excluirSugestao(@PathVariable Long id) {
        sugestaoRepository.deletar(id);
        return "redirect:/sugestoes";
    }


}
