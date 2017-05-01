package com.mdx.message

import com.mdx.candidate.Candidate
import grails.transaction.Transactional

@Transactional
class MessageService {

	def mailService
	def groovyPageRenderer

    public void send(String mailFrom, String mailTo, List<String> bccMails, String mailSubject, String mailBody, Boolean isHtml) {
		try {
			mailService.sendMail {
				from mailFrom
				to mailTo
				
				if (bccMails) {
					bcc bccMails
				}
				
				subject mailSubject
				
				if (isHtml) {
					html mailBody
				} else {
					body mailBody
				}
			}
		} catch (Exception e) {
			e.printStackTrace()

			throw e
		}
	}

	public String buildTemplate(String templatePath, Map binding) {
		return groovyPageRenderer.render(template: templatePath, model: binding)
	}

	public void sendEvaluationReport(Candidate candidate, String jobOpportunity) {
		String emailSubject = "Obrigado por se candidatar"
		String emailBody = buildTemplate("/mailTemplates/evaluationReport", [jobOpportunity: jobOpportunity])	
		
		send("matheus.mdx@gmail.com", candidate.email, null, emailSubject, emailBody, true)
  	}
}
