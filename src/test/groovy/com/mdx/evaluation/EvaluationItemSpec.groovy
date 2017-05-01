package com.mdx.evaluation

import com.mdx.type.ProgrammingLanguageType
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(EvaluationItem)
class EvaluationItemSpec extends Specification {

	def evaluationItem

    def setup() {
    	evaluationItem = new EvaluationItem(note: 9, programmingLanguageType: ProgrammingLanguageType.ANDROID)
    }

    def cleanup() {
    }

    void "Test item"() {
        expect: "Validation return true"
        	evaluationItem != null
    }

    void "Test item with errors"() {
        when: "EvaluationItem field has null value"
            evaluationItem?.note = null
        then: "Validation returns false"
            evaluationItem?.validate() == false
            evaluationItem?.errors?.hasFieldErrors('note') == true
    }
}
