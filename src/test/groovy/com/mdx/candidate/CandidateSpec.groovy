package com.mdx.candidate

import com.mdx.evaluation.Evaluation
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Candidate)
class CandidateSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test save failed"() {
        new Candidate(name: "Matheus", email: "matheus.mdx@gmail.com").save(flush: true)
    	
        expect: "has not valid save"
    		Candidate.count() == 0
    }

    void "test save"() {
    	new Candidate(name: "Matheus", email: "matheus.mdx@gmail.com", evaluation: new Evaluation()).save(flush: true)
    	
    	expect: "has valid save"
    		Candidate.count() == 1
    }

}
