package com.mdx.evaluation

import com.mdx.type.ProgrammingLanguageType

class EvaluationItem {

	Integer note
	ProgrammingLanguageType programmingLanguageType

	static belongsTo = [evaluation: Evaluation]
}
