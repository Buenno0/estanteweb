<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Editar Sugestão - EstanteWeb</title>
</head>
<body>
    <#include "cabecalho.ftlh">

    <h2>Editar Sugestão</h2>

    <#if erro??>
        <p style="color: red;">${erro}</p>
    </#if>

    <form method="post" action="/sugestoes/${(sugestao.id?c)!}/salvar">
        <label for="nomeCliente">Seu nome:</label>
        <input type="text" id="nomeCliente" name="nomeCliente" value="${(sugestao.nomeCliente)!}">
        <br>
        <label for="livroSugerido">Livro sugerido:</label>
        <input type="text" id="livroSugerido" name="livroSugerido" value="${(sugestao.livroSugerido)!}">
        <br>
        <button type="submit">Salvar alterações</button>
    </form>

    <p><a href="/sugestoes">Cancelar</a></p>
</body>
</html>

