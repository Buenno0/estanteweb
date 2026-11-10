package com.estanteweb.estanteweb;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Map;


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

    public void deletar(Long id){
        jdbcTemplate.update("DELETE FROM sugestao WHERE id = ?", id);
    }

    public void editar(Long id, Sugestao sugestao){
        jdbcTemplate.update("UPDATE sugestao SET nome_cliente = ?, livro_sugerido = ? WHERE id = ?",
                sugestao.getNomeCliente(),
                sugestao.getLivroSugerido(),
                id);
    }

    public Sugestao buscarPorId(Long id) {
        String sql = "SELECT * FROM sugestao WHERE id = ?";
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql, id);

        if (linhas.isEmpty()) {
            return null;
        }

        Map<String, Object> linha = linhas.get(0);
        return new Sugestao(
            ((Number) linha.get("id")).longValue(),
            (String) linha.get("nome_cliente"),
            (String) linha.get("livro_sugerido")
        );
    }

    public synchronized int total() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM sugestao", Integer.class);
    }

    public List<Sugestao> listarTodas() {
        String sql = "SELECT * FROM sugestao";
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql);
        List<Sugestao> lista = new ArrayList<>();
        for (Map<String, Object> linha : linhas) {
            lista.add(new Sugestao(
                ((Number) linha.get("id")).longValue(),
                (String) linha.get("nome_cliente"),
                (String) linha.get("livro_sugerido")
            ));
        }
        return lista;
    }
}
