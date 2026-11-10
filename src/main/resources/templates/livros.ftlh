<#include "cabecalho.ftlh" />

<h1>Livros</h1>
<#if livros?size == 0>
    <p>Não há livros cadastrados</p>
<#else>
    <ul>
        <#list livros as livro>
            <li>
                <a href="/livros/${livro.id?c}">${livro.titulo}</a>,
                de ${livro.autor} (R$ ${livro.preco})
            </li>
        </#list>
    </ul>
</#if>
