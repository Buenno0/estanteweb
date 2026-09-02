<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Contato - EstanteWeb</title>
</head>
<body>
    <#include "cabecalho.ftlh">

    <h2>Entre em contato</h2>

    <#if erro??>
        <p>${erro}</p>
    </#if>

    <form method="post" action="/contato">
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" value="${nome!}">

        <label for="mensagem">Mensagem:</label>
        <textarea id="mensagem" name="mensagem"></textarea>

        <button type="submit">Enviar</button>
    </form>
</body>
</html>
