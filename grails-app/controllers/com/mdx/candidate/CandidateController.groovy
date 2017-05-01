package com.mdx.candidate

import com.mdx.BaseController

class CandidateController extends BaseController {

    def candidateService
    
    def index() {
    }

    def create() {
    }

    def show(Candidate candidate) {
        if (!candidate) {
            return buildResponseAndRedirect(false, "evaluation.notFound", "candidate", "index")
        }

        respond candidate
    }

    def saveAndEvaluate() {
        try {
            Candidate candidate = candidateService.saveAndEvaluate(params)

            return buildResponseAndRedirect(true, "evaluation.successOnSubmit", "candidate", "show", candidate)
        } catch(Exception e) {
            e.printStackTrace()

            return buildResponseAndRedirect(false, "evaluation.errorOnSubmit", "candidate", "create")
        }
    }
}
