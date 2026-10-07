package com.estanteweb.estanteweb;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


@Repository
public class SugestaoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final List<Sugestao> sugestoes = new ArrayList<>();

    public void salvar(Sugestao sugestao) {
        
        String sql = "INSERT INTO sugestao (nome_cliente, livro_sugerido) VALUES (?, ?)";
        jdbcTemplate.update(sql, 
                sugestao.getNomeCliente(),
                sugestao.getLivroSugerido());

    }

    public synchronized int total() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM sugestao", Integer.class);
    }

    public synchronized List<Sugestao> listarTodas() {
        return new ArrayList<>(sugestoes);
    }
}
