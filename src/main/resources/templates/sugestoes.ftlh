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

    <#if RequestParameters.atualizada??>
        <p style="color: green;">Sugestão atualizada com sucesso!</p>
    </#if>

    <ul>
        <#list sugestoes as sugestao>
            <li>
                <strong>${sugestao.nomeCliente}</strong>: ${sugestao.livroSugerido}
                <#if sugestao.id??>
                    [<a href="/sugestoes/${sugestao.id?c}/editar">Editar</a>]
                    <form method="POST" action="/sugestoes/${sugestao.id?c}/deletar" style="display:inline">
                        <button type="submit" onclick="return confirm('Excluir esta sugestão?');">Excluir</button>
                    </form>
                </#if>
            </li>
        </#list>
    </ul>

    <a href="/sugestoes/nova">Nova sugestão</a>
</body>
</html>
