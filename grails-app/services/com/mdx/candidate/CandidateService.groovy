package com.mdx.candidate

import com.mdx.candidate.Candidate
import grails.transaction.Transactional

@Transactional
class CandidateService {

	def evaluationService

    public Candidate save(Map params) {
    	Candidate candidate = new Candidate(name: params.name, email: params.email)
        
        Map programmingLanguageTypes = evaluationService.cleanTypesOfParams(params)
        
        candidate.evaluation = evaluationService.save(programmingLanguageTypes)
        
        candidate.save(flush: true, failOnError: true)

    	return candidate
    }

    public Candidate saveAndEvaluate(Map params) {
    	Candidate candidate = save(params)

    	evaluationService.evaluateAndReport(candidate)

    	return candidate
    }

}
