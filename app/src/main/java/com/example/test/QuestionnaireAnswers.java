package com.example.test;

import java.util.List;


public class QuestionnaireAnswers
{
	List<String> answerList;
	String questionId;
	
	public QuestionnaireAnswers(List l, String qId)
	{
		this.answerList = l;
		this.questionId = qId;
	}

	public List<String> getAnswerList() {
		return answerList;
	}


	public String getQuestionId() {
		return questionId;
	}
	
	public int getQuestionChoiceLength()
	{	
		int count = 0;
		for(int i = 0; i < answerList.size(); i++)
		{
			if(answerList.get(i).toString().trim().length() != 0)
			{
				count++;
			}
		}
		return count;
	}
}