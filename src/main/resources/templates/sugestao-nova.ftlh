<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Nova sugestão - EstanteWeb</title>
</head>
<body>
    <#include "cabecalho.ftlh">

    <h2>Sugira uma leitura</h2>

    <#if erro??>
        <p>${erro}</p>
    </#if>

    <form method="post" action="/sugestoes">
        <label for="nomeCliente">Seu nome:</label>
        <input type="text" id="nomeCliente" name="nomeCliente" value="${nomeCliente!}">

        <label for="livroSugerido">Livro sugerido:</label>
        <input type="text" id="livroSugerido" name="livroSugerido" value="${livroSugerido!}">

        <button type="submit">Enviar sugestão</button>
    </form>
</body>
</html>
