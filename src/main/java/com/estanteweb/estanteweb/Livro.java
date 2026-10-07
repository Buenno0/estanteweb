package com.estanteweb.estanteweb;

import java.util.Map;

public class Livro {

    private long id;
    private String titulo;
    private String autor;
    private Double preco;
    private Integer ano;


    public Livro() {
    }

    public static Livro deMap(Map<String, Object> linha) {
        Livro livro = new Livro();
        livro.setId(((Number) linha.get("id")).longValue());
        livro.setTitulo((String) linha.get("titulo"));
        livro.setAutor((String) linha.get("autor"));
        livro.setAno(((Number) linha.get("ano")).intValue());
        livro.setPreco(((Number) linha.get("preco")).doubleValue());
        return livro;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", preco=" + preco +
                ", ano=" + ano +
                '}';
    }
}
