package com.mdx.evaluation

import com.mdx.candidate.Candidate
import com.mdx.evaluation.Evaluation
import com.mdx.evaluation.EvaluationItem
import com.mdx.type.ProgrammingLanguageType
import grails.transaction.Transactional

@Transactional
class EvaluationService {

	def grailsApplication
	def messageService

    public Evaluation save(Map programmingLanguageTypes) {
        Evaluation evaluation = new Evaluation()
		evaluation.save(flush: true, failOnError: true)

        programmingLanguageTypes.each { mapItem ->
	        EvaluationItem item = new EvaluationItem()
            item.programmingLanguageType = ProgrammingLanguageType.valueOf(mapItem.key)
            item.note = mapItem.value.toInteger()
            item.evaluation = evaluation

            item.save(flush: true, failOnError: true)

            evaluation.addToItems(item)
        }

        return evaluation
	}

    public void evaluateAndReport(Candidate candidate) {
        Boolean hasFrontEndPotencial = candidateHasPotencialForFrontEndOpportunity(candidate)
        if (hasFrontEndPotencial) messageService.sendEvaluationReport(candidate, "Front-End")

    	Boolean hasBackEndPotencial = candidateHasPotencialForBackEndOpportunity(candidate)
        if (hasBackEndPotencial) messageService.sendEvaluationReport(candidate, "Back-End")

    	Boolean hasMobilePotencial = candidateHasPotencialForMobileOpportunity(candidate)
        if (hasMobilePotencial) messageService.sendEvaluationReport(candidate, "Mobile")
    	
    	if (!hasFrontEndPotencial && !hasBackEndPotencial && !hasMobilePotencial) {
    		messageService.sendEvaluationReport(candidate, null)
    	}
    }

    private Boolean candidateHasPotencialForFrontEndOpportunity(Candidate candidate) {
        return candidate.evaluation.items.count { it.programmingLanguageType.isFrontEnd() && it.note >= grailsApplication.config.evaluationItem.note.average } == ProgrammingLanguageType.frontEndCount()
    }

    private Boolean candidateHasPotencialForBackEndOpportunity(Candidate candidate) {
        return candidate.evaluation.items.count { it.programmingLanguageType.isBackEnd() && it.note >= grailsApplication.config.evaluationItem.note.average } == ProgrammingLanguageType.backEndCount()
    }

    private Boolean candidateHasPotencialForMobileOpportunity(Candidate candidate) {
    	return (candidate.evaluation.items.find { it.programmingLanguageType.isMobile() && it.note >= grailsApplication.config.evaluationItem.note.average }).asBoolean()
    }

    public Map cleanTypesOfParams(Map params) {
        Map programmingLanguageTypesParams = params.findAll { it.key.startsWith("programmingLanguageType-") }
        
        Map programmingLanguageTypes = [:]

        programmingLanguageTypesParams.each { programmingLanguageTypes."${it.key.minus('programmingLanguageType-')}" = it.value }

        return programmingLanguageTypes
    }
}
