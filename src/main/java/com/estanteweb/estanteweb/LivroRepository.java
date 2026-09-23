package com.estanteweb.estanteweb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository 
public class LivroRepository {
    
    @Autowired 
    private JdbcTemplate jdbcTemplate;

    public List<Livro> listarTodos() {
        String sql = "SELECT * FROM livro";
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql);
        List<Livro> livros = new ArrayList<>();

        for (Map<String, Object> linha : linhas) {
            livros.add(Livro.deMap(linha));
        }

        return livros;
    }

    public String contarLivros() {
        return jdbcTemplate.queryForObject("select count(*) from livro", String.class);
    }

    public List<Livro> listarAtePreco(double precoMaximo) {
        String sql = "SELECT * FROM livro WHERE preco <= ?";
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql, precoMaximo);
        List<Livro> livros = new ArrayList<>();

        for (Map<String, Object> linha : linhas) {
            livros.add(Livro.deMap(linha));
        }

        return livros;
    }
}
