package com.estanteweb.estanteweb;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class SugestaoRepository {

    private final List<Sugestao> sugestoes = new ArrayList<>();

    public synchronized void adicionar(Sugestao sugestao) {
        sugestoes.add(sugestao);
    }

    public synchronized int total() {
        return sugestoes.size();
    }

    public synchronized List<Sugestao> listarTodas() {
        return new ArrayList<>(sugestoes);
    }
}
