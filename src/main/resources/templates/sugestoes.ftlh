<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Sugestões - EstanteWeb</title>
</head>
<body>
    <#include "cabecalho.ftlh">

    <h2>Sugestões</h2>

    <#if enviada>
        <p>Sugestão enviada com sucesso!</p>
    </#if>

    <ul>
        <#list sugestoes as sugestao>
            <li>${sugestao.nomeCliente}: ${sugestao.livroSugerido}</li>
        </#list>
    </ul>

    <a href="/sugestoes/nova">Nova sugestão</a>
</body>
</html>
