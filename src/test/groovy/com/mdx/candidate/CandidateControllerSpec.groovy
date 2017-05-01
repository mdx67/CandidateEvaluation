package com.mdx.candidate

import com.mdx.candidate.Candidate
import com.mdx.evaluation.Evaluation
import grails.test.mixin.*
import spock.lang.*
import grails.transaction.Transactional

@TestFor(CandidateController)
@Mock([Evaluation, CandidateService])
class CandidateControllerSpec extends Specification {

    void "test index"() {
        when:
            controller.index()
        then:
            response.status == 200
    }

    void "test create"() {
        when:
            controller.create()
        then:
            response.status == 200
    }

    void "test show"() {
        Candidate candidate = new Candidate(name: "Matheus", email: "matheus.mdx@gmail.com", evaluation: new Evaluation())

        when:
            controller.show(candidate)
        then:
            response.status == 200
    }

    void "test the saveAndEvaluate action"() {
        given:
            CandidateService candidateService = Mock()
            controller.candidateService = candidateService
        when: "the save saveAndEvaluate is executed with a valid instance"
            populateValidParams()

            controller.saveAndEvaluate()
        then:"the message success returned"
            controller.flash.type == "SUCCESS"
    }

    void "test that the show without params"() {
        when:"the show action is executed with a null domain"
            controller.show(null)

        then:"return to index with error message"
            controller.flash.message == "evaluation.notFound"
            response.redirectedUrl == '/candidate/index'
    }

    def populateValidParams() {
        controller.params.name = "Matheus Xavier"
        controller.params.email = "matheus.mdx@gmail.com"
        controller.params."programmingLanguageType-HTML" = 0
        controller.params."programmingLanguageType-CSS" = 0
        controller.params."programmingLanguageType-JAVASCRIPT" = 0
        controller.params."programmingLanguageType-PYTHON" = 0
        controller.params."programmingLanguageType-DJANGO" = 0
        controller.params."programmingLanguageType-IOS" = 0
        controller.params."programmingLanguageType-ANDROID" = 0
    }
}
