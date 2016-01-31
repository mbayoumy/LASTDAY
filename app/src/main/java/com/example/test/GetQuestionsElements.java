
package com.example.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * The purpose of this class is to parse a JSON file into a list of arrays each containing a question elements. Each question set is formed by the following elements: 
 * (in order) the ordering number of the question, the questionnaire ID, the question's text, five strings corresponding to the five possible choices included in the answer, 
 * the question type.
 * 
 * @author Cristina Burello
 */
public class GetQuestionsElements {
	//String questionnaireData = "{\"questions\":[[\"101\",\"1\",\"Question1Question1Question1Question1Question1?\",\"Choice1\",\"Choice2\",\"Choice3\",\"Choice4\",\"Choice5\",\"Single choice\"],[\"102\",\"1\",\"Question2Question2Question2Question2Question2Question2Question2Question2?\",\"Choice1\",\"Choice2\",\"\",\"\",\"\",\"Numerical\"],[\"103\",\"1\",\"Question3Question3Question3Question3?\",\"Choice1\",\"Choice2\",\"Choice3\",\"\",\"\",\"Multiple choice\"]]}";
	JSONObject dataObject;
	JSONArray questionsObject;
	String questionnaireData, questionID, questionnaireID, typeOfQuestion, questionText, choice1, choice2, choice3, choice4, choice5, questionSet, questnaireID, questType, questText;
	List<String> choicesList, questionSetList;
	private static int numberOfQuestionsInQuestionnaire = 0;
	
	/**
	 * Constructor method
	 * 
	 * @param _questionnaireData	String which holds the data from the JSON file
	 * @throws JSONException 		Throws an exception if the JSON file is not valid
	 */
	public GetQuestionsElements(String _questionnaireData) throws JSONException { 
		
		questionnaireData = _questionnaireData;
		dataObject = new JSONObject(questionnaireData);
	}
	/**
	 * This method parses a JSON file to a list of strings. Each string contains a question which is formed by various elements (question number, questionnaire ID, question text, 
	 * answer choices and question type. 
	 * 
	 * @return questionSetList	List of strings each containing the question elements. Each element is separated from the next by a dash "-".
	 * @throws JSONException 	Throws an exception if the JSON file is not valid
	 */
	public List<String> jsonParser() throws JSONException {
		// parses a JSON file containing the whole questionnaire
		questionsObject = dataObject.getJSONArray("questions"); // array to host the blocks of questions
		questionSetList = new ArrayList<String>(); // array containing all the questions (basically it will be the whole questionnaire on the last loop)
		

		for (int i = 0; i < questionsObject.length(); i++) { // loops over the blocks of questions and retrieves template's ID, questions and answers

			JSONArray questionBlock = questionsObject.getJSONArray(i);
			
			questionID = questionBlock.getString(0); // retrieves the question's ID as a string
			questionnaireID = questionBlock.getString(1); // retrieves the template's ID as a string
			questionText = questionBlock.getString(2); // retrieves the string with the question
			choice1 = questionBlock.getString(3); // retrieves the answer's first choice as a string
			choice2 = questionBlock.getString(4); // retrieves the answer's second choice as a string
			choice3 = questionBlock.getString(5); // retrieves the answer's third choice as a string
			choice4 = questionBlock.getString(6); // retrieves the answer's fourth choice as a string
			choice5 = questionBlock.getString(7); // retrieves the answer's fifth choice as a string
			typeOfQuestion = questionBlock.getString(8); // retrieves the type of question upon which will depend its choices
			
			questionSet = questionID +"-"+ questionnaireID +"-"+ questionText +"-"+ choice1 +"-"+ choice2 +"-"+ choice3 +"-"+ choice4 +"-"+ choice5 +"-"+ typeOfQuestion; // makes a string out of the question elements
			questionSetList.add(questionSet); // each loop adds a question 'block' to the list that will contain the questionnaire
			}
		
		numberOfQuestionsInQuestionnaire = questionsObject.length();
		Log.i("la", " " + numberOfQuestionsInQuestionnaire);
		return questionSetList;
	}
	/**
	 * This method takes the number of the required question and manipulates the data parsed from the JSON file so the various question elements are retrieved more easily by 
	 * the specific methods
	 * 
	 * @param questionNumber	An integer which stands for the ordering number of the question
	 * @return questionElements	A list of strings which are the question elements
	 * @throws JSONException	Throws an exception if the JSON file is not valid
	 */
	public List<String> dataManipulator(int questionNumber) throws JSONException {
 
		GetQuestionsElements tempObject = new GetQuestionsElements(questionnaireData); //JSON data object 
		List<String> tempQuestData = tempObject.jsonParser(); // arrayList with the parsed data from the JSON object
		String question = tempQuestData.get(questionNumber - 1); // retrieves the string holding the question elements 
		ArrayList<String> questionElements = new ArrayList<String>(Arrays.asList(question.split("-"))); // creates the array that will hold the question elements
		
		return questionElements; // list of strings which are the question elements
	}
	/**
	 * This method takes the number of the required question and returns the questionnaire ID as a string
	 * 
	 * @param questionNumber	An integer which stands for the ordering number of the question
	 * @return questnaireID		A string that stands for the questionnaire ID
	 * @throws JSONException	Throws an exception if the JSON file is not valid
	 */
	public String getQuestionnaireID(int questionNumber) throws JSONException { 
		
		List<String> tempQID = new ArrayList<String>();
		tempQID = dataManipulator(questionNumber);
		questnaireID = tempQID.get(1);
		
		return questnaireID;
		}	
	/**
	 * This method takes the number of the required question and returns question type as a string
	 * 
	 * @param questionNumber	An integer which stands for the ordering number of the question
	 * @return questType		A strings that stands for the question type
	 * @throws JSONException	Throws an exception if the JSON file is not valid
	 */
	public String getQuestionType(int questionNumber) throws JSONException { 
		
		List<String> tempQType = new ArrayList<String>();
		tempQType = dataManipulator(questionNumber);
		questType = tempQType.get(8);

		return questType;
		}	
	/**
	 * This method takes the number of the required question and returns the text of the question as a string
	 * 
	 * @param questionNumber	An integer which stands for the ordering number of the question
	 * @return questText		A strings that stands for the question text
	 * @throws JSONException	Throws an exception if the JSON file is not valid
	 */
	public String getQuestionText(int questionNumber) throws JSONException { 		

		List<String> tempQText = new ArrayList<String>();
		tempQText = dataManipulator(questionNumber);
		questText = tempQText.get(2);
		
		return questText;
		}	
	/**
	 * This method takes the number of the required question and returns the possible choices of the answer as a list of strings. The array has five indexes, 
	 * some of which might be empty strings and the number of choices may be less than five.
	 * 
	 * @param questionNumber	An integer which stands for the ordering number of the question
	 * @return choicesList		A list of strings each being a possible choice for the answer. The maximum number of choices is five
	 * @throws JSONException	Throws an exception if the JSON file is not valid
	 */
	public List<String> getAnswerChoices(int questionNumber) throws JSONException {

		List<String> tempChoices = new ArrayList<String>();
		tempChoices = dataManipulator(questionNumber);	
		choicesList = new ArrayList<String>();

		for(int i = 3; i < tempChoices.size() - 1; i++) {
				String tempElem = tempChoices.get(i);
				choicesList.add(tempElem);
			}
		return choicesList;
	}
	
	public int getNumberOfQuestionsInQuestionnaire()
	{
		return numberOfQuestionsInQuestionnaire;
	}
	
	public String getQuestionId(int questionNumber) throws JSONException
	{
		List<String> tempQText = new ArrayList<String>();
		tempQText = dataManipulator(questionNumber);
		this.questionID = tempQText.get(0);
		
		return this.questionID;
	}
	
}