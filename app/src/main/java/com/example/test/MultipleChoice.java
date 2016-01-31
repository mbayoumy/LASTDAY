package com.example.test;

import java.util.List;

import org.json.JSONException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

/**
 * This class will probably not be used, I moved away from it and am now using QuestionnareActivity
 * @author Jug-raj
 *
 */
public class MultipleChoice extends Activity
{
	private static TextView questionNumber, questionText, questionGuide;
	public static TextView  userInputInfoTV;
	private static Button choice1, choice2, choice3, choice4, choice5, next;
	private static int countOfQuestion; // This will keep track of questions, and will allow us to point to the correct part of the
	// JSON file. From there, we can choose the correct block containing the question and its data, then display accordingly.
	// Do it by increment and/or re-assigning depending on the situation
	public MultipleChoice()
	{
		doDaTings();
	}
	private void doDaTings() {
		// TODO Auto-generated method stub
		String questionnaireData = "{\"questions\":[[\"101\",\"1\",\"Big man ting where's the pain??\",\"Nose\",\"Ears\",\"Jaw\",\"Mouth\",\"Back\",\"Single choice\"],[\"102\",\"1\",\"Question2Question2Question2Question2Question2Question2Question2Question2?\",\"Choice2.1\",\"Choice2.2\",\"\",\"\",\"\",\"Numerical\"],[\"103\",\"1\",\"Question3Question3Question3Question3?\",\"Choice3.1\",\"Choice3.2\",\"Choice3.3\",\"\",\"\",\"Multiple choice\"]]}";
		GetQuestionsElements questionnaireObject;
		GetQuestionsElements questionsData;
		choice1 = (Button) findViewById(R.id.mc_four_choice1_button);
		choice2 = (Button) findViewById(R.id.mc_four_choice2_button);
		choice3 = (Button) findViewById(R.id.mc_four_choice3_button);
		choice4 = (Button) findViewById(R.id.mc_four_choice4_button);
		TextView theQuestionTV = (TextView) findViewById(R.id.mc_four_question_text_TV);
		
		//userInputInfoTV = (TextView) findViewById(R.id.mc_four_user_input_info_TV);
		
		choice1 = new ButtonRenderer(this, choice1);
		choice2 = new ButtonRenderer(this, choice2);
		choice3 = new ButtonRenderer(this, choice3);
		choice4 = new ButtonRenderer(this, choice4);

			try {
				questionnaireObject = new GetQuestionsElements(questionnaireData);
				String testQuestnaireID = questionnaireObject.getQuestionnaireID(1);
				Log.v("FINE! - this is the ID of the questionnaire", testQuestnaireID); 
				
				String testQuestText1 = questionnaireObject.getQuestionText(1);
				
				String testQuestType1 = questionnaireObject.getQuestionType(1);
				
				List<String> testAnswer1 = questionnaireObject.getAnswerChoices(1); //TEST
				int count = 1;
				for(int i = 0; i < testAnswer1.size(); i++)
				{
					Log.i("LALA", testAnswer1.get(i).toString());
					Log.i("count", "the count is " + count + "when i is " + i);
					if(count == 1)
					{
						choice1.setText(testAnswer1.get(i));
						Log.i("count", "the count is " + count + "when i is " + i);
						
					}
					if(count == 2)
					{
						choice2.setText(testAnswer1.get(i));
						Log.i("count", "the count is " + count + "when i is " + i);
						
					}
					if(count == 3)
					{
						choice3.setText(testAnswer1.get(i));
						Log.i("count", "the count is " + count + "when i is " + i);
					}
					if(count == 4)
					{
						choice4.setText(testAnswer1.get(i));
						Log.i("count", "the count is " + count + "when i is " + i);
					}
					count++;
				}
				
				theQuestionTV.setText(testQuestText1);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.multiple_choice_four_layout);
		// We shall do an "if" statement here. "if" the incoming question has 4 multiple choices to choose from
		// Then setContentView(R.layout.the_four_choice_multiple_choice_layout
		// write a class which all buttons use - OnClickListener which causes the button to change colour upon selection to green,
		// then back to pink on re-selection
		// Suggestion - textview to tell the user what they have selected.
		String questionnaireData = "{\"questions\":[[\"101\",\"1\",\"Big man ting where's the pain??\",\"Nose\",\"Ears\",\"Jaw\",\"Mouth\",\"Back\",\"Single choice\"],[\"102\",\"1\",\"Question2Question2Question2Question2Question2Question2Question2Question2?\",\"Choice2.1\",\"Choice2.2\",\"\",\"\",\"\",\"Numerical\"],[\"103\",\"1\",\"Question3Question3Question3Question3?\",\"Choice3.1\",\"Choice3.2\",\"Choice3.3\",\"\",\"\",\"Multiple choice\"]]}";
		GetQuestionsElements questionnaireObject;
		GetQuestionsElements questionsData;
		choice1 = (Button) findViewById(R.id.mc_four_choice1_button);
		choice2 = (Button) findViewById(R.id.mc_four_choice2_button);
		choice3 = (Button) findViewById(R.id.mc_four_choice3_button);
		choice4 = (Button) findViewById(R.id.mc_four_choice4_button);
		TextView theQuestionTV = (TextView) findViewById(R.id.mc_four_question_text_TV);
		
		//userInputInfoTV = (TextView) findViewById(R.id.mc_four_user_input_info_TV);
		
		choice1 = new ButtonRenderer(this, choice1);
		choice2 = new ButtonRenderer(this, choice2);
		choice3 = new ButtonRenderer(this, choice3);
		choice4 = new ButtonRenderer(this, choice4);

			try {
				questionnaireObject = new GetQuestionsElements(questionnaireData);
				String testQuestnaireID = questionnaireObject.getQuestionnaireID(1);
				Log.v("FINE! - this is the ID of the questionnaire", testQuestnaireID); 
				
				String testQuestText1 = questionnaireObject.getQuestionText(1);
				
				String testQuestType1 = questionnaireObject.getQuestionType(1);
				
				List<String> testAnswer1 = questionnaireObject.getAnswerChoices(1); //TEST
				int count = 1;
				for(int i = 0; i < testAnswer1.size(); i++)
				{
					Log.i("LALA", testAnswer1.get(i).toString());
					Log.i("count", "the count is " + count + "when i is " + i);
					if(count == 1)
					{
						choice1.setText(testAnswer1.get(i));
						Log.i("count", "the count is " + count + "when i is " + i);
						
					}
					if(count == 2)
					{
						choice2.setText(testAnswer1.get(i));
						Log.i("count", "the count is " + count + "when i is " + i);
						
					}
					if(count == 3)
					{
						choice3.setText(testAnswer1.get(i));
						Log.i("count", "the count is " + count + "when i is " + i);
					}
					if(count == 4)
					{
						choice4.setText(testAnswer1.get(i));
						Log.i("count", "the count is " + count + "when i is " + i);
					}
					count++;
				}
				
				theQuestionTV.setText(testQuestText1);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
	
}