<%@ page import="com.mdx.type.ProgrammingLanguageType" %>

<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        
        <asset:stylesheet src="application.css"/>
        
        <title>Candidate Evaluation</title>
    </head>
    <body>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>

        <div>
            <span class="margin-left-100">Complete os dados:</span>
            
            <br />

            <g:form controller="candidate" action="saveAndEvaluate" method="POST">
                
                <div class="fieldcontain required">
                    <label for="name">Name <span class="required-indicator">*</span>: </label>
                    <input type="text" name="name" required id="name"/>
                </div>
                
                <div class="fieldcontain required">
                  <label for="email">Email <span class="required-indicator">*</span>: </label>
                  <input type="text" name="email" required id="email"/>
                </div>
                
                <br />

                <span class="margin-left-100">
                    Avalie seu conhecimento sobre os itens abaixo. Considerando 0 como sem conhecimento algum e 10 dominio total.
                </span>

                <br />

                <g:each in="${ProgrammingLanguageType.values()}" var="programmingLanguageType">
                    <div class="fieldcontain">
                        <label for="${programmingLanguageType}">${programmingLanguageType.getLabel()}: </label>
                        
                        <g:select name="programmingLanguageType-${programmingLanguageType}" 
                                  id="programmingLanguageType-${programmingLanguageType}"  
                                  from="${0..10}" 
                                  optionValue="${it}"/>
                    </div>
                </g:each>

                <br />

                <div class="content-center">
                    <g:submitButton name="save" class="save" value="${message(code: 'form.submitButton.label')}" />
                </div>
            </g:form>
        </div>
    </body>
</html>
