<h1>Livros</h1>
<#if livros?size == 0>
    <p>Não há livros cadastrados</p>
<#else>
    <ul>
        <#list livros as livro>
            <li>${livro.titulo} </li>
            <li>${livro.autor} </li>
            <li>${livro.preco} </li>
        </#list>
    </ul>
</#if>