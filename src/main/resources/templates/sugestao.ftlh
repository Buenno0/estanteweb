<#include "cabecalho.ftlh" />

<h1>Sugestão de ${sugestao.nomeCliente}</h1>
<ul>
    <li>Sugerido por: ${sugestao.nomeCliente}</li>
    <li>Livro sugerido: ${sugestao.livroSugerido}</li>
</ul>

<p><a href="/sugestoes/${sugestao.id?c}/editar">Editar esta sugestão</a></p>

<form method="POST" action="/sugestoes/${sugestao.id?c}/deletar">
    <button type="submit" onclick="return confirm('Tem certeza que deseja excluir esta sugestão?');">Excluir sugestão</button>
</form>

<p><a href="/sugestoes">Voltar para sugestões</a></p>

