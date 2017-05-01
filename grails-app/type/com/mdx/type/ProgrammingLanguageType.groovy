package com.mdx.type

import grails.util.Holders

enum ProgrammingLanguageType {
	
	HTML, CSS, JAVASCRIPT, PYTHON, DJANGO, IOS, ANDROID

	public Boolean isFrontEnd() {
		return frontEndList().contains(this)
	}

	public Boolean isBackEnd() {
		return backEndList().contains(this)
	}

	public Boolean isMobile() {
		return [ProgrammingLanguageType.IOS, ProgrammingLanguageType.ANDROID].contains(this)
	}

	public static Integer frontEndCount() {
		return frontEndList().size()
	}

	public static Integer backEndCount() {
		return backEndList().size()
	}

	public static List<ProgrammingLanguageType> frontEndList() {
		return [ProgrammingLanguageType.HTML, ProgrammingLanguageType.CSS, ProgrammingLanguageType.JAVASCRIPT]
	}

	public static List<ProgrammingLanguageType> backEndList() {
		return [ProgrammingLanguageType.PYTHON, ProgrammingLanguageType.DJANGO]
	}

	public String getLabel() {
		return Holders.applicationContext.getBean("messageSource").getMessage("programmingLanguageType.${this.toString()}.label", null, "", new Locale("pt","BR"))
	}
}
