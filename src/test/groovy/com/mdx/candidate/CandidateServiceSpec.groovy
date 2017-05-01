package com.mdx.candidate

import grails.transaction.*
import grails.test.mixin.integration.*
import spock.lang.*
import com.mdx.candidate.Candidate
import org.springframework.mail.MailSendException

@Integration
@Rollback
class CandidateServiceSpec extends Specification {

    CandidateService candidateService

    Map params = [name: "Matheus Xavier",
                  email: "matheus.mdx@gmail.com",
                  "programmingLanguageType-HTML": 10,
                  "programmingLanguageType-CSS": 10,
                  "programmingLanguageType-JAVASCRIPT": 10,
                  "programmingLanguageType-PYTHON": 10,
                  "programmingLanguageType-DJANGO": 10,
                  "programmingLanguageType-IOS": 10,
                  "programmingLanguageType-ANDROID": 10]

    void "test save"() {
        when: "save candidate"
            Candidate candidate = candidateService.save(params)

        then: "exists candidate"
            candidate != null
    }

    void "test save and evaluate"() {
        when: "save candidate and evaluate"
            Candidate candidate = candidateService.saveAndEvaluate(params) 

        then: "exists candidate"
            candidate != null
    }

    void "test save and evaluate with invalid email"() {
        Map invalidParams = [name: "Matheus Xavier", email: "matheus", "programmingLanguageType-HTML": 10]

        when: 
            candidateService.saveAndEvaluate(invalidParams) 
        then: "exception expected"
            MailSendException e = thrown()
    }
}
