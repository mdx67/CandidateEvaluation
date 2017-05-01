<!doctype html>
<html>
    <head>
        <title>Página não encontrada</title>
        <meta name="layout" content="main">
        <g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
    </head>
    <body>
        <ul class="errors">
            <li>Página não encontrada</li>
        </ul>

        <a class="margin-left-20" href="${g.createLink(controller: 'candidate', action: 'index')}">Voltar ao inicio</a>
    </body>
</html>
