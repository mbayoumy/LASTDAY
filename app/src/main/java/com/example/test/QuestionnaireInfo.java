package com.example.test;

import java.util.List;

import android.util.Log;

/**
 * A getter and setter class which is used in QuestionnareActivity.
 * @author Jug-raj
 *
 */
public class QuestionnaireInfo
{
	String questionText, questionBrief, choiceOne, choiceTwo, choiceThree, choiceFour, choiceFive, questionType;
	String questionId;
	List<String> choiceList;
	int questionChoiceLength;
	
	/**
	 * 
	 * @param qT question text
	 * @param choices list of choices
	 * @param qTy question type
	 */
	public QuestionnaireInfo(String qT,  List<String> choices, String qTy, String qId)
	{
		this.questionText = qT;
		//this.questionBrief = qB;
		this.choiceList = choices;
		this.questionType = qTy;
		this.questionId = qId;
		
		setChoices();
	}

	public String getQuestionId() {
		return questionId;
	}

	/**
	 * This loops through the list containing choices for questions and then assigns them to the choice Strings
	 */
	private void setChoices(){
		// TODO Auto-generated method stub
		
		if(choiceList.get(0) != null) { choiceOne = choiceList.get(0); } else { Log.i("Null", "null!"); }
		if(choiceList.get(1) != null) { choiceTwo = choiceList.get(1); } else { Log.i("Null", "null!"); }
		if(choiceList.get(2) != null) { choiceThree = choiceList.get(2); } else { Log.i("Null", "null!"); }
		if(choiceList.get(3) != null) { choiceFour = choiceList.get(3); } else { Log.i("Null", "null!"); }
		if(choiceList.get(4) != null) { choiceFive = choiceList.get(4); } else { Log.i("Null", "null!"); }
		
	}

	public String getQuestionText() {
		return questionText;
	}


	public String getQuestionBrief() {
		return questionBrief;
	}

	public String getChoiceOne() {
		return choiceOne;
	}

	public String getChoiceTwo() {
		return choiceTwo;
	}

	public String getChoiceThree() {
		return choiceThree;
	}

	public String getChoiceFour() {
		return choiceFour;
	}

	public String getChoiceFive() {
		return choiceFive;
	}

	public String getQuestionType() 
	{
		return questionType;
	}
	
	/**
	 * I have added in a way to make sure the program trims all whitespace so if, let's say,
	 * The doctor accidentally leaves white space, then the android program will not view it as an actual answer
	 * @return
	 */
	public int getQuestionChoiceLength()
	{	
		return choiceList.size();
	}
}