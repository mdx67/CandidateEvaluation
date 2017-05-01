package com.mdx.evaluation

import com.mdx.candidate.Candidate
import com.mdx.evaluation.Evaluation
import com.mdx.evaluation.EvaluationItem
import com.mdx.type.ProgrammingLanguageType
import grails.test.mixin.*
import spock.lang.*

@TestFor(EvaluationService)
@Mock([Candidate, Evaluation, EvaluationItem])
class EvaluationServiceSpec extends Specification {

    Candidate frontEndCandidate = new Candidate(name: "Matheus FrontEnd", email: "matheus.mdx.frontend@gmail.com", evaluation: new Evaluation(items: [new EvaluationItem(programmingLanguageType: ProgrammingLanguageType.HTML, note: 10), new EvaluationItem(programmingLanguageType: ProgrammingLanguageType.CSS, note: 7), new EvaluationItem(programmingLanguageType: ProgrammingLanguageType.JAVASCRIPT, note: 8)]))
    Candidate frontEndCandidateWithLowNote = new Candidate(name: "Matheus FrontEnd 2", email: "matheus.mdx.frontend2@gmail.com", evaluation: new Evaluation(items: [new EvaluationItem(programmingLanguageType: ProgrammingLanguageType.HTML, note: 6)]))
    
    Candidate backEndCandidate = new Candidate(name: "Matheus BackEnd", email: "matheus.mdx.backend@gmail.com", evaluation: new Evaluation(items: [new EvaluationItem(programmingLanguageType: ProgrammingLanguageType.PYTHON, note: 8), new EvaluationItem(programmingLanguageType: ProgrammingLanguageType.DJANGO, note: 9)]))
    Candidate backEndCandidateWithLowNote = new Candidate(name: "Matheus BackEnd 2", email: "matheus.mdx.backend2@gmail.com", evaluation: new Evaluation(items: [new EvaluationItem(programmingLanguageType: ProgrammingLanguageType.PYTHON, note: 2)]))
    
    Candidate mobileCandidate = new Candidate(name: "Matheus Mobile", email: "matheus.mdx.mobile@gmail.com", evaluation: new Evaluation(items: [new EvaluationItem(programmingLanguageType: ProgrammingLanguageType.IOS, note: 9)]))
    Candidate mobileCandidateWithLowNote = new Candidate(name: "Matheus Mobile 2", email: "matheus.mdx.mobile2@gmail.com", evaluation: new Evaluation(items: [new EvaluationItem(programmingLanguageType: ProgrammingLanguageType.IOS, note: 5)]))

    void "candidate has potencial for FrontEnd"() {
        when:
            Boolean frontEndPotencial = service.candidateHasPotencialForFrontEndOpportunity(frontEndCandidate)
        then:
            frontEndPotencial == true
    }

    void "candidate has not potencial for FrontEnd"() {
        when:
            Boolean frontEndPotencial = service.candidateHasPotencialForFrontEndOpportunity(frontEndCandidateWithLowNote)
        then:
            frontEndPotencial == false
    }

    void "candidate has potencial for BackEnd"() {
        when:
            Boolean backEndPotencial = service.candidateHasPotencialForBackEndOpportunity(backEndCandidate)
        then:
            backEndPotencial == true
    }

    void "candidate has not potencial for BackEnd"() {
        when:
            Boolean backEndPotencial = service.candidateHasPotencialForBackEndOpportunity(backEndCandidateWithLowNote)
        then:
            backEndPotencial == false
    }

    void "candidate has potencial for Mobile"() {
        when:
            Boolean hasMobilePotencial = service.candidateHasPotencialForMobileOpportunity(mobileCandidate)
        then:
            hasMobilePotencial == true
    }

    void "candidate has not potencial for Mobile"() {
        when:
            Boolean hasMobilePotencial = service.candidateHasPotencialForMobileOpportunity(mobileCandidateWithLowNote)
        then:
            hasMobilePotencial == false
    }

    void "test save"() {
        Map params = ["HTML": 0,
                      "CSS": 0,
                      "JAVASCRIPT": 0,
                      "PYTHON": 0,
                      "DJANGO": 0,
                      "IOS": 0,
                      "ANDROID": 0]
    	when: 
            Evaluation evaluation = service.save(params)
        then:
            evaluation != null
            evaluation.items != null
    }
}
