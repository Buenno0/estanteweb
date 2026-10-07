<#include "cabecalho.ftlh" />

<form method="POST" action="/livros">
    titulo: <input type="text" name="titulo" value="${(livro.titulo)!(livros.titulo)!}" /> <br />
    autor: <input type="text" name="autor" value="${(livro.autor)!(livros.autor)!}" /> <br />
    ano: <input type="text" name="ano" value="${(livro.ano)!(livros.ano)!}" /> <br />
    preco: <input type="text" name="preco" value="${(livro.preco)!(livros.preco)!}" /> <br />
    <input type="submit" value="Salvar" />
</form>

<#if erro??>
    <p style="color: red;">${erro}</p>
</#if>