<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Candidate Evaluation</title>
    </head>
    <body>
        <div id="show-candidate" class="content scaffold-show" role="main">
            <h1>Avaliação</h1>
            
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            <div class="fieldcontain">
                <label id="name-label" class="property-label">Name: </label>
                ${candidate.name}
            </div>

            <div class="fieldcontain">
                <label id="email-label" class="property-label">Email: </label>
                ${candidate.email}
            </div>
            
            <g:each in="${candidate.evaluation.items}" var="evaluationItem">
                <div class="fieldcontain">
                    <label for="${evaluationItem}">${evaluationItem.programmingLanguageType.getLabel()}: </label>
                    ${evaluationItem.note}
                </div>
            </g:each>
        </div>
    </body>
</html>

