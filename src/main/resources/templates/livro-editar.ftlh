<#include "cabecalho.ftlh" />

<h1>Editar Livro</h1>

<form method="POST" action="/livros/${livro.id?c}/editar">
    Título: <input type="text" name="titulo" value="${livro.titulo!}" /> <br />
    Autor: <input type="text" name="autor" value="${livro.autor!}" /> <br />
    Ano: <input type="text" name="ano" value="${livro.ano?c!}" /> <br />
    Preço: <input type="text" name="preco" value="${(livro.preco?c)!}" /> <br />
    <input type="submit" value="Salvar Alterações" />
</form>

<#if erro??>
    <p style="color: red;">${erro}</p>
</#if>

<p><a href="/livros/${livro.id?c}">Cancelar</a></p>

