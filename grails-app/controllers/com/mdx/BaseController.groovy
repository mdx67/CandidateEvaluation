package com.mdx

public class BaseController {

	protected buildResponseAndRedirect(Boolean success, String messageCode, String controller, String action) {
		return buildResponseAndRedirect(success, messageCode, controller, action, null)
	}
	
	protected buildResponseAndRedirect(Boolean success, String messageCode, String controller, String action, entity) {
		flash.message = message(code: messageCode)
        flash.type = success ? "SUCCESS" : "ERROR"

		return redirect(controller: controller, action: action, id: entity?.id)
	}
}
