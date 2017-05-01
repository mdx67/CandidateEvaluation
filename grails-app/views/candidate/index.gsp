<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Candidate Evaluation</title>
    </head>
    <body>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        
        <div class="content-center">
            Bem-vindo(a) ao Candidate Evaluation! <br /><br />

            Nosso software tem como objetivo avaliar seus conhecimentos de programação e lhe informar se há vagas para o seu perfil. 
        
            <br /><br />

            <a href="${g.createLink(controller: 'candidate', action: 'create')}">Começar!</a>
        </div>
    </body>
</html>