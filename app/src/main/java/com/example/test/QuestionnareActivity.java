package com.example.test;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.ViewFlipper;

/**
 * This class is our answersJsonArray questionnaire one which has a list of questions
 * @author Jug-raj
 *
 */
public class QuestionnareActivity extends Activity

{
	public static ListView theListView;
	public  ArrayList<QuestionnaireInfo> infoArray = new ArrayList<QuestionnaireInfo>();
	public static ArrayAdapter<QuestionnaireInfo> arrayAdapter;
	private float lastX;
	private ViewFlipper flipView;
	boolean aChoiceHasBeenSelected = false;
	
	private boolean isSingleChoice;
	//String questionnaireData = "{\"questions\":[[\"101\",\"1\",\"Where else have you felt pain?\",\"Lungs\",\"Heart\",\"Neck\",\"Arm\",\"    \",\"Multiple choice\"],[\"102\",\"1\",\"Have you ever been welcomed to beast?\",\"AKINFENWA\",\"DROGBA\",\"YAYA TOURE\",\"EBOUE\",\"\",\"Numerical\"],[\"103\",\"1\",\"Question3Question3Question3Question3?\",\"Choice3.1\",\"Choice3.2\",\"Choice3.3\",\"\",\"\",\"Multiple choice\"]]}";
	
	//String two = "{ \"questions\": [[\"100\",\"1\",\" How good was the GP at: putting you at ease\",\"very good \",\"good\",\" satisfactory\",\"poor\",\"very poor \",\"Multiple choice\"]]}";
	String two =  "{\"questions\": [[\"100\",\"1\",\" How good was the GP at: putting you at ease\",\"very good \",\"good\",\" satisfactory\",\"poor\",\"very poor \",\"Single Choice\"],[\"101\",\"1\",\"How good was the GP at: being polite\",\"\",\"\",\"\",\"\",\"\",\"Yes/No\"],[\"102\",\"1\",\"ANGR????\",\"very good\",\"good\",\"Satisfactory \",\"very poor\",\"poor\",\"Multiple Choice\"],[\"103\",\"1\",\"date of appointment\",\"\",\"\",\"\",\"\",\"\",\"Date\"],[\"104\",\"1\",\"Age:\",\"18\",\"100\",\"\",\"\",\"\",\"Numerical\"],[\"105\",\"1\",\"U WOT M8\",\"very good\",\"good\",\"satisfactory\",\"very poor\",\"poor\",\"Multiple Choice\"]]}";
	public static String questionnaireString;
	GetQuestionsElements questionnaireObject;
	GetQuestionsElements questionsData;
	public ArrayList<String> typesOfQuestions = new ArrayList<String>();
	public static JSONArray answersJsonArray = new JSONArray(); // make this global.
	public static JSONArray patientJsonArray = new JSONArray();
	private ArrayList<QuestionnaireAnswers> answersArray = new ArrayList<QuestionnaireAnswers>();
	
	
	String patientID, patientFirstName, patientLastName, patientDoBStr;
	
	
	// to add and remove from arraylist, do int size = flipView.getChildCount() - 1; (minus one since we manually add the submit view
	// then use this to reference the position in the array list.
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);
		switch(item.getItemId())
		{
		case R.id.qm_inc_font_mi:
			
		}
		
		
		return false;
	}









	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.questionnaire_menu_items, menu);
		return true;
	}









	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_template_layout);
		
		patientID = getIntent().getExtras().getString("pId");
		patientFirstName = getIntent().getExtras().getString("pFirst");
		patientLastName = getIntent().getExtras().getString("pLast");
		patientDoBStr = getIntent().getExtras().getString("pDOB");
		
		questionnaireString = getIntent().getExtras().getString("qString");
		Log.i("FINAL Q STRING", "" + questionnaireString.charAt(1));
		addToArrayList();
		
		flipView = (ViewFlipper) findViewById(R.id.viewFlipper1);
		flipView.setInAnimation(this, android.R.anim.fade_in);
		flipView.setOutAnimation(this, android.R.anim.fade_out);

		
		for(int i = 0; i < infoArray.size(); i++)
		{
			View v;
			Log.i("TYPE", infoArray.get(i).getQuestionType().toLowerCase());
			Log.i("CHOICE LENGTH", "" + infoArray.get(i).getQuestionChoiceLength());
			if(infoArray.get(i).getQuestionType().toLowerCase().equals("multiple choice"))
			{
				if(infoArray.get(i).getQuestionChoiceLength() == 5)
				{
					v = getLayoutInflater().inflate(R.layout.multiple_choice_five_layout,  null);
					v.setTag("mc5");
					setMultipleChoiceButtons(v, i, infoArray);
					
					
					Log.i("tag", v.getTag().toString());
					//setOnClickListeners(v, i);
					//flipView.addView(v);
					
				}
				
				if(infoArray.get(i).getQuestionChoiceLength() == 4)
				{
					v = getLayoutInflater().inflate(R.layout.multiple_choice_four_layout,  null);
					//setButtons(v, i, infoArray);
					v.setTag("mc4");
					setMultipleChoiceButtons(v, i, infoArray);
					
					Log.i("tag", v.getTag().toString());
					
					
				
					//flipView.addView(v);
				}
				
				if(infoArray.get(i).getQuestionChoiceLength() == 3)
				{
					
					v = getLayoutInflater().inflate(R.layout.multiple_choice_three_layout,  null);
					//setButtons(v, i, infoArray);
					v.setTag("mc3");
					setMultipleChoiceButtons(v, i, infoArray);
					
					Log.i("tag", v.getTag().toString());
					
					
				
					//flipView.addView(v);
				}
				
				if(infoArray.get(i).getQuestionChoiceLength() == 2)
				{
					v = getLayoutInflater().inflate(R.layout.multiple_choice_two_layout,  null);
					//setButtons(v, i, infoArray);
					v.setTag("mc2");
					setMultipleChoiceButtons(v, i, infoArray);
					
					Log.i("tag", v.getTag().toString());
					
					
				
					//flipView.addView(v);
				}
				// add in if statements for other multiple choice sizes... single choice ones may be awks. Let's just make it long and do a seperate one for single choice.
			}
			
			if(infoArray.get(i).getQuestionType().toLowerCase().equals("single choice"))
			{
				if(infoArray.get(i).getQuestionChoiceLength() == 5)
				{
					v = getLayoutInflater().inflate(R.layout.multiple_choice_five_layout,  null);
					v.setTag("sc5");
					setSingleChoiceButtons(v, i, infoArray);
					
					
					Log.i("tag", v.getTag().toString());
					//setOnClickListeners(v, i);
					//flipView.addView(v);
					
				}
				
				if(infoArray.get(i).getQuestionChoiceLength() == 4)
				{
					v = getLayoutInflater().inflate(R.layout.multiple_choice_four_layout,  null);
					//setButtons(v, i, infoArray);
					v.setTag("sc4");
					setSingleChoiceButtons(v, i, infoArray);
					
					Log.i("tag", v.getTag().toString());
					
					
				
					//flipView.addView(v);
				}
				
				if(infoArray.get(i).getQuestionChoiceLength() == 3)
				{
					v = getLayoutInflater().inflate(R.layout.multiple_choice_three_layout,  null);
					//setButtons(v, i, infoArray);
					v.setTag("sc3");
					setSingleChoiceButtons(v, i, infoArray);
					
					Log.i("tag", v.getTag().toString());
					
					
				
					//flipView.addView(v);
				}
				
				if(infoArray.get(i).getQuestionChoiceLength() == 2)
				{
					v = getLayoutInflater().inflate(R.layout.multiple_choice_two_layout,  null);
					//setButtons(v, i, infoArray);
					v.setTag("sc2");
					setSingleChoiceButtons(v, i, infoArray);
					
					Log.i("tag", v.getTag().toString());
					
					
				
					//flipView.addView(v);
				}
				// add in if statements for other multiple choice sizes... single choice ones may be awks. Let's just make it long and do a seperate one for single choice.
			}
			
			
			
			if(infoArray.get(i).getQuestionType().toLowerCase().equals("text"))
			{
				v = getLayoutInflater().inflate(R.layout.enter_text_layout, null);
				v.setTag("txt");
				setTextLayout(v, i, infoArray);
			}
			if(infoArray.get(i).getQuestionType().toLowerCase().equals("numerical"))
			{
				v = getLayoutInflater().inflate(R.layout.min_max_layout, null);
				v.setTag("num");
				setMinMaxLayout(v, i, infoArray);
			}
			// date
			if(infoArray.get(i).getQuestionType().toLowerCase().equals("date"))
			{
				v = getLayoutInflater().inflate(R.layout.enter_date_layout, null);
				v.setTag("date");
				setDateLayout(v, i, infoArray);
			}
			//yes/no
			if(infoArray.get(i).getQuestionType().toLowerCase().equals("yes/no"))
			{
				v = getLayoutInflater().inflate(R.layout.yes_or_no_layout, null);
				v.setTag("yn");
				setSingleChoiceButtons(v, i, infoArray);
			}
		}
		
		
		
		View submit = getLayoutInflater().inflate(R.layout.submit_questionnaire_layout, null);
		submit.setTag("submit");
		setSubmitButtons(submit);
		//flipView.addView(ugh); 
		//flipView.addView(roflmao);
		
		// to get all info, loop over array size.
		// for(int i =0; i < infoArray.size(); i++
		// if(flipView.getChildAt(0).getTag().equals("multiple choice five")
		// flipView.getChildAt(0).findViewById(R.id.mc_five_choice1_button); find out selected buttons.
		// 
		// use setDisplayedChild(choose index) for global access :)
		flipView.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				
				//use view v - it gets the current view in focus!
				//Button a = (Button) flipView.getChildAt(0).findViewById(R.id.mc_five_choice1_button);
				//a.setText("YGH");
				
				
				return false;
			}
		});
			
		
		
		getAnswers();
		
	}

		
		
	
	









	private void setDateLayout(View v, int i,
			ArrayList<QuestionnaireInfo> infoArray2) {
		// TODO Auto-generated method stub
		final int a = i;
		
		TextView questionTV = (TextView) v.findViewById(R.id.enter_date_question_text_TV);
		questionTV.setText(infoArray.get(a).getQuestionText().toString());
		
		int questionNo = a + 1;
		
		TextView questionNumber = (TextView) v.findViewById(R.id.enter_date_question_number_TV);
		questionNumber.setText("Question number " + questionNo + " of " + infoArray.size());
		
		final DatePicker pickDate = (DatePicker) v.findViewById(R.id.enter_date_date_picker);
		
		Button nextButton = (Button) v.findViewById(R.id.enter_date_next_button);
		
		nextButton.setOnClickListener(new View.OnClickListener() {
			
			
			List<String> answer = new ArrayList<String>();
			String questionID = infoArray.get(a).getQuestionId().toString();
			
			Integer year, month, day;
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				day = pickDate.getDayOfMonth(); 
				month = pickDate.getMonth() + 1; 
				year = pickDate.getYear();
				Log.d(" question id", questionID);
				answer.add(day.toString() + "-" + month.toString() + "-" + year.toString());
				answersArray.add(new QuestionnaireAnswers(answer, questionID));
				
				flipView.showNext();
			}
		});
		
		flipView.addView(v);
	}









	private void setMinMaxLayout(View v, int i, ArrayList<QuestionnaireInfo> infoArray2) {
		// TODO Auto-generated method stub
		final int a = i;
		
		TextView questionTV = (TextView) v.findViewById(R.id.min_max_question_text_TV);
		questionTV.setText(infoArray.get(a).getQuestionText().toString());
		
		int questionNo = a + 1;
		
		TextView questionNumber = (TextView) v.findViewById(R.id.min_max_question_number_TV);
		questionNumber.setText("Question number " + questionNo + " of " + infoArray.size());
		
		final NumberPicker numberPicker = (NumberPicker) v.findViewById(R.id.mm_number_picker);
		numberPicker.setMinValue(Integer.parseInt(infoArray.get(a).getChoiceOne()));
		numberPicker.setMaxValue(Integer.parseInt(infoArray.get(a).getChoiceTwo()));
		
		Button inc = (Button) v.findViewById(R.id.mm_inc_number);
		Button dec = (Button) v.findViewById(R.id.mm_dec_number);
		
		inc.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				numberPicker.setValue(numberPicker.getValue() + 1);
				
				
			}
		});
		
		dec.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				numberPicker.setValue(numberPicker.getValue() - 1);
			}
		});
		
		Button nextButton = (Button) v.findViewById(R.id.min_max_next_button);
		nextButton.setOnClickListener(new View.OnClickListener() {
			
			List<String> answer = new ArrayList<String>();
			String questionID = infoArray.get(a).getQuestionId().toString();
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				answer.add("" + numberPicker.getValue());
				answersArray.add(new QuestionnaireAnswers(answer, questionID));
				
				flipView.showNext();
			}
		});
		
		flipView.addView(v);
	}









	private void setTextLayout(View v, int i,
			ArrayList<QuestionnaireInfo> infoArray2) {
		// TODO Auto-generated method stub
		final int a = i;
		
		TextView questionTV = (TextView) v.findViewById(R.id.enter_text_question_text_TV);
		questionTV.setText(infoArray.get(a).getQuestionText().toString());
		
		int questionNo = a + 1;
		
		TextView questionNumber = (TextView) v.findViewById(R.id.enter_text_question_number_TV);
		questionNumber.setText("Question number " + questionNo + " of " + infoArray.size());
		
		final EditText inputET = (EditText) v.findViewById(R.id.enter_text_input_et);
		
		Button nextButton = (Button) v.findViewById(R.id.enter_text_next_button);
		nextButton.setOnClickListener(new View.OnClickListener() {
			
			List<String> answer = new ArrayList<String>();
			String questionID = infoArray.get(a).getQuestionId().toString();
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				answer.add(inputET.getText().toString());
				answersArray.add(new QuestionnaireAnswers(answer, questionID));
				
				flipView.showNext();
			}
		});
		
		flipView.addView(v);
	}









	private void setSingleChoiceButtons(View v, int i,
			ArrayList<QuestionnaireInfo> infoArray2) {
		// TODO Auto-generated method stub
		
		if(v.getTag().equals("sc5"))
		{
			
			
			
			final int a = i;
			Log.i("Sha!", "omdz");
			TextView questionTV = (TextView) v.findViewById(R.id.mc_five_question_text_TV);
			questionTV.setText(infoArray2.get(i).getQuestionText().toString());		
			
			int questionNo = a + 1;
			
			TextView questionNumber = (TextView) v.findViewById(R.id.mc_five_question_number_TV);
			questionNumber.setText("Question number " + questionNo + " of " + infoArray.size());
			
			TextView questionBrief = (TextView) v.findViewById(R.id.mc_five_q_brief_TV);
			questionBrief.setText("Please select one choice from below");
			
			final CheckBox choiceOne = (CheckBox) v.findViewById(R.id.mc_five_choice1_button);
			choiceOne.setText(infoArray2.get(i).getChoiceOne().toString());
			//choiceOne.setOnCheckedChangeListener(listener);
			
			final CheckBox choiceTwo = (CheckBox) v.findViewById(R.id.mc_five_choice2_button);
			choiceTwo.setText(infoArray2.get(i).getChoiceTwo().toString());
			//choiceTwo.setOnCheckedChangeListener(listener);
			
			final CheckBox choiceThree = (CheckBox) v.findViewById(R.id.mc_five_choice3_button);
			choiceThree.setText(infoArray2.get(i).getChoiceThree().toString());
			if(choiceThree.getText().toString().trim().equals(""))
			{
				choiceThree.setVisibility(View.GONE);
			}
			//choiceThree.setOnCheckedChangeListener(listener);
				
			final CheckBox choiceFour = (CheckBox) v.findViewById(R.id.mc_five_choice4_button);
			choiceFour.setText(infoArray2.get(i).getChoiceFour().toString());
			if(choiceFour.getText().toString().trim().equals(""))
			{
				choiceFour.setVisibility(View.GONE);
			}
			//choiceFour.setOnCheckedChangeListener(listener);
			
			final CheckBox choiceFive = (CheckBox) v.findViewById(R.id.mc_five_choice5_button);
			choiceFive.setText(infoArray2.get(i).getChoiceFive().toString());
			if(choiceFive.getText().toString().trim().equals(""))
			{
				choiceFive.setVisibility(View.GONE);
			}
			//choiceFive.setOnCheckedChangeListener(listener);
			
			
			
			final ArrayList<CheckBox> checkBoxList = new ArrayList<CheckBox>();
			checkBoxList.add(choiceOne); checkBoxList.add(choiceTwo);
			checkBoxList.add(choiceThree); checkBoxList.add(choiceFour);
			checkBoxList.add(choiceFive);
			
			aChoiceHasBeenSelected = false;
			OnCheckedChangeListener listener = new OnCheckedChangeListener(){

				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					// TODO Auto-generated method stub
					if(!buttonView.isChecked()) // if the button has been checked, 
					{
						for(int inti = 0; inti < checkBoxList.size(); inti++)
						{
							checkBoxList.get(inti).setEnabled(true);
						}
					}
					else
					{ // if they have checked it, disable all the other buttons.
						for(int inti = 0; inti < checkBoxList.size(); inti++)
						{
							checkBoxList.get(inti).setEnabled(false);
						}
					}
					
				}
				
			};
			
			for(int inta = 0; inta < checkBoxList.size(); inta++)
			{
				checkBoxList.get(inta).setOnCheckedChangeListener(listener);
			}
			//flipView.addView(v);
			
			Button changeChoice = (Button) v.findViewById(R.id.mc_five_sc_change_choice);
			changeChoice.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					for(int l = 0; l < checkBoxList.size(); l++)
					{
						checkBoxList.get(l).setChecked(false);
						checkBoxList.get(l).setEnabled(true);
					}
				}
			});
			
			
			Button nextButton = (Button) v.findViewById(R.id.mc_five_next_button);
			nextButton.setOnClickListener(new View.OnClickListener(){

				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					List<String> listOfAnswers = new ArrayList<String>();
					String idOfQuestion = infoArray.get(a).getQuestionId();
					if(choiceOne.isChecked())
					{
						Log.i("mc5choiceOne", choiceOne.getText().toString());
						listOfAnswers.add(choiceOne.getText().toString());
						
					}
					if(choiceTwo.isChecked())
					{
						listOfAnswers.add(choiceTwo.getText().toString());
						
					}
					if(choiceThree.isChecked())
					{
						listOfAnswers.add(choiceThree.getText().toString());
						
					}
					if(choiceFour.isChecked())
					{
						listOfAnswers.add(choiceFour.getText().toString());
						
					}
					if(choiceFive.isChecked())
					{
						listOfAnswers.add(choiceFive.getText().toString());
						
					}
					
					answersArray.add(new QuestionnaireAnswers(listOfAnswers, idOfQuestion));
					//Log.i("La", listOfAnswers.get(0).toString());
					flipView.showNext();
				}
				
			});
			
			flipView.addView(v);
			}
		
		if(v.getTag().equals("sc4"))
			{
				final int a = i;
				TextView questionTV = (TextView) v.findViewById(R.id.mc_four_question_text_TV);
				questionTV.setText(infoArray2.get(i).getQuestionText().toString());		
				
				final CheckBox choiceOne = (CheckBox) v.findViewById(R.id.mc_four_choice1_button);
				choiceOne.setText(infoArray2.get(i).getChoiceOne().toString());
				//choiceOne.setOnCheckedChangeListener(listener);
				
				
				final CheckBox choiceTwo = (CheckBox) v.findViewById(R.id.mc_four_choice2_button);
				choiceTwo.setText(infoArray2.get(i).getChoiceTwo().toString());
				//choiceTwo.setOnCheckedChangeListener(listener);
				
				final CheckBox choiceThree = (CheckBox) v.findViewById(R.id.mc_four_choice3_button);
				choiceThree.setText(infoArray2.get(i).getChoiceThree().toString());
				//choiceThree.setOnCheckedChangeListener(listener);
					
				final CheckBox choiceFour = (CheckBox) v.findViewById(R.id.mc_four_choice4_button);
				choiceFour.setText(infoArray2.get(i).getChoiceFour().toString());
				//choiceFour.setOnCheckedChangeListener(listener);
				
				final ArrayList<CheckBox> checkBoxList = new ArrayList<CheckBox>();
				checkBoxList.add(choiceOne); checkBoxList.add(choiceTwo);
				checkBoxList.add(choiceThree); checkBoxList.add(choiceFour);
				
				
				OnCheckedChangeListener listener = new OnCheckedChangeListener(){

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if(!buttonView.isChecked()) // if the button has been checked, 
						{
							for(int inti = 0; inti < checkBoxList.size(); inti++)
							{
								checkBoxList.get(inti).setEnabled(true);
							}
						}
						else
						{ // if they have checked it, disable all the other buttons.
							for(int inti = 0; inti < checkBoxList.size(); inti++)
							{
								checkBoxList.get(inti).setEnabled(false);
							}
						}
						
					}
					
				};
				
				for(int inta = 0; inta < checkBoxList.size(); inta++)
				{
					checkBoxList.get(inta).setOnCheckedChangeListener(listener);
				}
				//flipView.addView(v);
				
				Button changeChoice = (Button) v.findViewById(R.id.mc_four_sc_change_choice);
				changeChoice.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						for(int l = 0; l < checkBoxList.size(); l++)
						{
							checkBoxList.get(l).setChecked(false);
							checkBoxList.get(l).setEnabled(true);
						}
					}
				});
				
				
				
				Button nextButton = (Button) v.findViewById(R.id.mc_four_next_button);
				
				//flipView.addView(v);
				
				nextButton.setOnClickListener(new View.OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						List<String> listOfAnswers = new ArrayList<String>();
						String idOfQuestion = infoArray.get(a).getQuestionId();
						if(choiceOne.isChecked())
						{
							listOfAnswers.add(choiceOne.getText().toString());
							
						}
						if(choiceTwo.isChecked())
						{
							listOfAnswers.add(choiceTwo.getText().toString());
							
						}
						if(choiceThree.isChecked())
						{
							listOfAnswers.add(choiceThree.getText().toString());
							
						}
						if(choiceFour.isChecked())
						{
							listOfAnswers.add(choiceFour.getText().toString());
						
						}
						answersArray.add(new QuestionnaireAnswers(listOfAnswers, idOfQuestion));
						
						//Log.i("La", listOfAnswers.get(0).toString());
						flipView.showNext();
					}
					
				});
				
				flipView.addView(v);
				}
		
		if(v.getTag().equals("sc3"))
			{
				final int a = i;
				TextView questionTV = (TextView) v.findViewById(R.id.mc_three_question_text_TV);
				questionTV.setText(infoArray2.get(i).getQuestionText().toString());		
				
				final CheckBox choiceOne = (CheckBox) v.findViewById(R.id.mc_three_choice1_button);
				choiceOne.setText(infoArray2.get(i).getChoiceOne().toString());
				//choiceOne.setOnCheckedChangeListener(listener);
				
				final CheckBox choiceTwo = (CheckBox) v.findViewById(R.id.mc_three_choice2_button);
				choiceTwo.setText(infoArray2.get(i).getChoiceTwo().toString());
				//choiceTwo.setOnCheckedChangeListener(listener);
				
				final CheckBox choiceThree = (CheckBox) v.findViewById(R.id.mc_three_choice3_button);
				choiceThree.setText(infoArray2.get(i).getChoiceThree().toString());
				//choiceThree.setOnCheckedChangeListener(listener);

				final ArrayList<CheckBox> checkBoxList = new ArrayList<CheckBox>();
				checkBoxList.add(choiceOne); checkBoxList.add(choiceTwo);
				checkBoxList.add(choiceThree);
				
				
				OnCheckedChangeListener listener = new OnCheckedChangeListener(){

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if(!buttonView.isChecked()) // if the button has been checked, 
						{
							for(int inti = 0; inti < checkBoxList.size(); inti++)
							{
								checkBoxList.get(inti).setEnabled(true);
							}
						}
						else
						{ // if they have checked it, disable all the other buttons.
							for(int inti = 0; inti < checkBoxList.size(); inti++)
							{
								checkBoxList.get(inti).setEnabled(false);
							}
						}
						
					}
					
				};
				
				for(int inta = 0; inta < checkBoxList.size(); inta++)
				{
					checkBoxList.get(inta).setOnCheckedChangeListener(listener);
				}
				//flipView.addView(v);
				
				Button changeChoice = (Button) v.findViewById(R.id.mc_three_sc_change_choice);
				changeChoice.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						for(int l = 0; l < checkBoxList.size(); l++)
						{
							checkBoxList.get(l).setChecked(false);
							checkBoxList.get(l).setEnabled(true);
						}
					}
				});
				
				
				Button nextButton = (Button) v.findViewById(R.id.mc_three_next_button);
				
				//flipView.addView(v);				
				
				nextButton.setOnClickListener(new View.OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						List<String> listOfAnswers = new ArrayList<String>();
						String idOfQuestion = infoArray.get(a).getQuestionId();
						if(choiceOne.isChecked())
						{
							listOfAnswers.add(choiceOne.getText().toString());
							
						}
						if(choiceTwo.isChecked())
						{
							listOfAnswers.add(choiceTwo.getText().toString());
						
						}
						if(choiceThree.isChecked())
						{
							listOfAnswers.add(choiceThree.getText().toString());
							
						}
						
						
						answersArray.add(new QuestionnaireAnswers(listOfAnswers, idOfQuestion));
						//Log.i("La", listOfAnswers.get(0).toString());
						flipView.showNext();
					}
					
				});
				
				flipView.addView(v);
				}
			
			if(v.getTag().equals("sc2"))
			{
				final int a = i;
				TextView questionTV = (TextView) v.findViewById(R.id.mc_two_question_text_TV);
				questionTV.setText(infoArray2.get(i).getQuestionText().toString());		
				
				final CheckBox choiceOne = (CheckBox) v.findViewById(R.id.mc_two_choice1_button);
				choiceOne.setText(infoArray2.get(i).getChoiceOne().toString());
				//choiceOne.setOnCheckedChangeListener(listener);
				
				final CheckBox choiceTwo = (CheckBox) v.findViewById(R.id.mc_two_choice2_button);
				choiceTwo.setText(infoArray2.get(i).getChoiceTwo().toString());
				//choiceTwo.setOnCheckedChangeListener(listener);
				
				final ArrayList<CheckBox> checkBoxList = new ArrayList<CheckBox>();
				checkBoxList.add(choiceOne); checkBoxList.add(choiceTwo);
				
				
				
				OnCheckedChangeListener listener = new OnCheckedChangeListener(){

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if(!buttonView.isChecked()) // if the button has been checked, 
						{
							for(int inti = 0; inti < checkBoxList.size(); inti++)
							{
								checkBoxList.get(inti).setEnabled(true);
							}
						}
						else
						{ // if they have checked it, disable all the other buttons.
							for(int inti = 0; inti < checkBoxList.size(); inti++)
							{
								checkBoxList.get(inti).setEnabled(false);
							}
						}
						
					}
					
				};
				
				for(int inta = 0; inta < checkBoxList.size(); inta++)
				{
					checkBoxList.get(inta).setOnCheckedChangeListener(listener);
				}
				//flipView.addView(v);
				
				Button changeChoice = (Button) v.findViewById(R.id.mc_two_sc_change_choice);
				changeChoice.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						for(int l = 0; l < checkBoxList.size(); l++)
						{
							checkBoxList.get(l).setChecked(false);
							checkBoxList.get(l).setEnabled(true);
						}
					}
				});
				
				Button nextButton = (Button) v.findViewById(R.id.mc_two_next_button);
				
				
				nextButton.setOnClickListener(new View.OnClickListener(){

				List<String> listOfAnswers = new ArrayList<String>();
				String idOfQuestion = infoArray.get(a).getQuestionId();
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(choiceOne.isChecked())
						{
							Log.i("CHECKED", "it's checked...");
							listOfAnswers.add(choiceOne.getText().toString());
							Log.i("choiceOnemc2", choiceOne.getText().toString());
							
						}
						if(choiceTwo.isChecked())
						{
							listOfAnswers.add(choiceTwo.getText().toString());
							
						}
					
						answersArray.add(new QuestionnaireAnswers(listOfAnswers, idOfQuestion));
						//Log.i("La", listOfAnswers.get(0).toString());
						flipView.showNext();
					}
					
				});
				
				flipView.addView(v);
				}
		
		
		
		if(v.getTag().equals("yn"))
		{
			final int a = i;
			TextView questionTV = (TextView) v.findViewById(R.id.yn_question_text_TV);
			questionTV.setText(infoArray2.get(i).getQuestionText().toString());		
			
			int questionNo = a + 1;
			
			TextView questionNumber = (TextView) v.findViewById(R.id.yn_question_number_TV);
			questionNumber.setText("Question number " + questionNo + " of " + infoArray.size());
			
			final CheckBox choiceOne = (CheckBox) v.findViewById(R.id.yn_yes_button);
			choiceOne.setText("Yes");
			//choiceOne.setOnCheckedChangeListener(listener);
			
			final CheckBox choiceTwo = (CheckBox) v.findViewById(R.id.yn_no_button);
			choiceTwo.setText("No");
			//choiceTwo.setOnCheckedChangeListener(listener);
			
			final ArrayList<CheckBox> checkBoxList = new ArrayList<CheckBox>();
			checkBoxList.add(choiceOne); checkBoxList.add(choiceTwo);

			OnCheckedChangeListener listener = new OnCheckedChangeListener(){

				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					// TODO Auto-generated method stub
					if(!buttonView.isChecked()) // if the button has been checked, 
					{
						for(int inti = 0; inti < checkBoxList.size(); inti++)
						{
							checkBoxList.get(inti).setEnabled(true);
						}
					}
					else
					{ // if they have checked it, disable all the other buttons.
						for(int inti = 0; inti < checkBoxList.size(); inti++)
						{
							checkBoxList.get(inti).setEnabled(false);
						}
					}
					
				}
				
			};
			
			for(int inta = 0; inta < checkBoxList.size(); inta++)
			{
				checkBoxList.get(inta).setOnCheckedChangeListener(listener);
			}
			//flipView.addView(v);
			
			Button changeChoice = (Button) v.findViewById(R.id.yn_change_choice_button);
			changeChoice.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					for(int l = 0; l < checkBoxList.size(); l++)
					{
						checkBoxList.get(l).setChecked(false);
						checkBoxList.get(l).setEnabled(true);
					}
				}
			});
			
			
			
			Button nextButton = (Button) v.findViewById(R.id.yes_no_next_button);
			
			
			nextButton.setOnClickListener(new View.OnClickListener(){

			List<String> listOfAnswers = new ArrayList<String>();
			String idOfQuestion = infoArray.get(a).getQuestionId();
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(choiceOne.isChecked())
					{
						Log.i("CHECKED", "it's checked...");
						listOfAnswers.add(choiceOne.getText().toString());
						Log.i("choiceOnemc2", choiceOne.getText().toString());
						
					}
					if(choiceTwo.isChecked())
					{
						listOfAnswers.add(choiceTwo.getText().toString());
						
					}
				
					answersArray.add(new QuestionnaireAnswers(listOfAnswers, idOfQuestion));
					//Log.i("La", listOfAnswers.get(0).toString());
					flipView.showNext();
				}
				
			});
			
			flipView.addView(v);
		}
			
		
	}









	private void setSubmitButtons(View submit) {
		// TODO Auto-generated method stub
		Button b = (Button) submit.findViewById(R.id.submit_choice1_button);
		b.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setJSONfeedback();
				setJSONpatient();
				Intent intent = new Intent(QuestionnareActivity.this, Client.class);
				intent.putExtra("qString", questionnaireString);
				intent.putExtra("isFromQuestionnaireActivity", true);
				intent.putExtra("pId", patientID);
				intent.putExtra("pFirst", patientFirstName);
				intent.putExtra("pLast", patientLastName);
				intent.putExtra("pDOB", patientDoBStr);
				startActivity(intent);
			}
			
		});
		
		flipView.addView(submit);
	}









	private void getAnswers() {
		// TODO Auto-generated method stub
		
	}









	private void setMultipleChoiceButtons(View v, int i, ArrayList<QuestionnaireInfo> infoArray2) {
		// TODO Auto-generated method stub
		if(v.getTag().equals("mc5"))
		{
			final int a = i;
			Log.i("Sha!", "omdz");
			v.findViewById(R.id.mc_five_sc_change_choice).setVisibility(View.INVISIBLE);
			TextView questionTV = (TextView) v.findViewById(R.id.mc_five_question_text_TV);
			questionTV.setText(infoArray2.get(i).getQuestionText().toString());		
			
			int questionNo = a + 1;
			
			TextView questionNumber = (TextView) v.findViewById(R.id.mc_five_question_number_TV);
			questionNumber.setText("Question number " + questionNo + " of " + infoArray.size());
			
			TextView questionBrief = (TextView) v.findViewById(R.id.mc_five_q_brief_TV);
			questionBrief.setText("Please select one or more from below");
			
			final CheckBox choiceOne = (CheckBox) v.findViewById(R.id.mc_five_choice1_button);
			choiceOne.setText(infoArray2.get(i).getChoiceOne().toString());
			
			final CheckBox choiceTwo = (CheckBox) v.findViewById(R.id.mc_five_choice2_button);
			choiceTwo.setText(infoArray2.get(i).getChoiceTwo().toString());
			
			
			final CheckBox choiceThree = (CheckBox) v.findViewById(R.id.mc_five_choice3_button);
			choiceThree.setText(infoArray2.get(i).getChoiceThree().toString());
			if(choiceThree.getText().toString().trim().equals(""))
			{
				choiceThree.setVisibility(View.GONE);
			}
				
				
			final CheckBox choiceFour = (CheckBox) v.findViewById(R.id.mc_five_choice4_button);
			choiceFour.setText(infoArray2.get(i).getChoiceFour().toString());
			if(choiceFour.getText().toString().trim().equals(""))
			{
				choiceFour.setVisibility(View.GONE);
				
			}
					
			final CheckBox choiceFive = (CheckBox) v.findViewById(R.id.mc_five_choice5_button);
			choiceFive.setText(infoArray2.get(i).getChoiceFive().toString());
			if(choiceFive.getText().toString().trim().equals(""))
			{
				choiceFive.setVisibility(View.GONE);
			}
			
			Button nextButton = (Button) v.findViewById(R.id.mc_five_next_button);
			
			
			//flipView.addView(v);
			
			nextButton.setOnClickListener(new View.OnClickListener(){

				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					List<String> listOfAnswers = new ArrayList<String>();
					String idOfQuestion = infoArray.get(a).getQuestionId();
					if(choiceOne.isChecked())
					{
						Log.i("mc5choiceOne", choiceOne.getText().toString());
						listOfAnswers.add(choiceOne.getText().toString() + ",");
						
					}
					if(choiceTwo.isChecked())
					{
						listOfAnswers.add(choiceTwo.getText().toString() + ",");
						
					}
					if(choiceThree.isChecked())
					{
						listOfAnswers.add(choiceThree.getText().toString() + ",");
						
					}
					if(choiceFour.isChecked())
					{
						listOfAnswers.add(choiceFour.getText().toString() + ",");
						
					}
					if(choiceFive.isChecked())
					{
						listOfAnswers.add(choiceFive.getText().toString() + ",");
						
					}
					
					answersArray.add(new QuestionnaireAnswers(listOfAnswers, idOfQuestion));
					//Log.i("La", listOfAnswers.get(0).toString());
					flipView.showNext();
				}
				
			});
			
			flipView.addView(v);
			}
		
		else if(v.getTag().equals("mc4"))
			{
				final int a = i;
				v.findViewById(R.id.mc_four_sc_change_choice).setVisibility(View.INVISIBLE);
				TextView questionTV = (TextView) v.findViewById(R.id.mc_four_question_text_TV);
				questionTV.setText(infoArray2.get(i).getQuestionText().toString());		
				
				final CheckBox choiceOne = (CheckBox) v.findViewById(R.id.mc_four_choice1_button);
				choiceOne.setText(infoArray2.get(i).getChoiceOne().toString());
				
				final CheckBox choiceTwo = (CheckBox) v.findViewById(R.id.mc_four_choice2_button);
				choiceTwo.setText(infoArray2.get(i).getChoiceTwo().toString());
				
				
				final CheckBox choiceThree = (CheckBox) v.findViewById(R.id.mc_four_choice3_button);
				choiceThree.setText(infoArray2.get(i).getChoiceThree().toString());
					
					
				final CheckBox choiceFour = (CheckBox) v.findViewById(R.id.mc_four_choice4_button);
				choiceFour.setText(infoArray2.get(i).getChoiceFour().toString());
				
				
				Button nextButton = (Button) v.findViewById(R.id.mc_four_next_button);
				
				//flipView.addView(v);
				
				nextButton.setOnClickListener(new View.OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						List<String> listOfAnswers = new ArrayList<String>();
						String idOfQuestion = infoArray.get(a).getQuestionId();
						if(choiceOne.isChecked())
						{
							listOfAnswers.add(choiceOne.getText().toString() + ",");
							
						}
						if(choiceTwo.isChecked())
						{
							listOfAnswers.add(choiceTwo.getText().toString() + ",");
							
						}
						if(choiceThree.isChecked())
						{
							listOfAnswers.add(choiceThree.getText().toString() + ",");
							
						}
						if(choiceFour.isChecked())
						{
							listOfAnswers.add(choiceFour.getText().toString() + ",");
						
						}
						answersArray.add(new QuestionnaireAnswers(listOfAnswers, idOfQuestion));
						
						//Log.i("La", listOfAnswers.get(0).toString());
						flipView.showNext();
					}
					
				});
				
				flipView.addView(v);
				}
		
		else if(v.getTag().equals("mc3"))
			{
				v.findViewById(R.id.mc_three_sc_change_choice).setVisibility(View.INVISIBLE);
				final int a = i;
				TextView questionTV = (TextView) v.findViewById(R.id.mc_three_question_text_TV);
				questionTV.setText(infoArray2.get(i).getQuestionText().toString());		
				
				final CheckBox choiceOne = (CheckBox) v.findViewById(R.id.mc_three_choice1_button);
				choiceOne.setText(infoArray2.get(i).getChoiceOne().toString());
				
				final CheckBox choiceTwo = (CheckBox) v.findViewById(R.id.mc_three_choice2_button);
				choiceTwo.setText(infoArray2.get(i).getChoiceTwo().toString());
				
				
				final CheckBox choiceThree = (CheckBox) v.findViewById(R.id.mc_three_choice3_button);
				choiceThree.setText(infoArray2.get(i).getChoiceThree().toString());
				

				Button nextButton = (Button) v.findViewById(R.id.mc_three_next_button);
				
				//flipView.addView(v);				
				
				nextButton.setOnClickListener(new View.OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						List<String> listOfAnswers = new ArrayList<String>();
						String idOfQuestion = infoArray.get(a).getQuestionId();
						if(choiceOne.isChecked())
						{
							listOfAnswers.add(choiceOne.getText().toString() + ",");
							
						}
						if(choiceTwo.isChecked())
						{
							listOfAnswers.add(choiceTwo.getText().toString() + ",");
						
						}
						if(choiceThree.isChecked())
						{
							listOfAnswers.add(choiceThree.getText().toString() + ",");
							
						}
						
						
						answersArray.add(new QuestionnaireAnswers(listOfAnswers, idOfQuestion));
						//Log.i("La", listOfAnswers.get(0).toString());
						flipView.showNext();
					}
					
				});
				
				flipView.addView(v);
				}
			
		else if(v.getTag().equals("mc2"))
			{
				final int a = i;
				v.findViewById(R.id.mc_two_sc_change_choice).setVisibility(View.INVISIBLE);
				TextView questionTV = (TextView) v.findViewById(R.id.mc_two_question_text_TV);
				questionTV.setText(infoArray2.get(i).getQuestionText().toString());		
				
				final CheckBox choiceOne = (CheckBox) v.findViewById(R.id.mc_two_choice1_button);
				choiceOne.setText(infoArray2.get(i).getChoiceOne().toString());
				
				final CheckBox choiceTwo = (CheckBox) v.findViewById(R.id.mc_two_choice2_button);
				choiceTwo.setText(infoArray2.get(i).getChoiceTwo().toString());
				
				
				
				
				Button nextButton = (Button) v.findViewById(R.id.mc_two_next_button);
				
				
				nextButton.setOnClickListener(new View.OnClickListener(){

				List<String> listOfAnswers = new ArrayList<String>();
				String idOfQuestion = infoArray.get(a).getQuestionId();
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(choiceOne.isChecked())
						{
							Log.i("CHECKED", "it's checked...");
							listOfAnswers.add(choiceOne.getText().toString());
							Log.i("choiceOnemc2", choiceOne.getText().toString() + ",");
							
						}
						if(choiceTwo.isChecked())
						{
							listOfAnswers.add(choiceTwo.getText().toString() + ",");
							
						}
					
						answersArray.add(new QuestionnaireAnswers(listOfAnswers, idOfQuestion));
						//Log.i("La", listOfAnswers.get(0).toString());
						flipView.showNext();
					}
					
				});
				
				flipView.addView(v);
				}
			
		}
		
		
	/**
	 * Call on these when user has clicked 'next' and/or submit.
	 * @return
	 */
	public String setJSONfeedback(){
		
		Log.i("Size of answers", "" + answersArray.size());
		List<String> answerList;
		int QuestionnaireID;
		
		for(int x = 0 ; x < answersArray.size(); x++){
			JSONObject question = new JSONObject();
			answerList = answersArray.get(x).getAnswerList();
			
			String rofl = "";
			for(int i = 0; i < answerList.size(); i++)
			{
				rofl += answerList.get(i).toString();
				Log.i("HUH", " " + i);
			}
			

			
			QuestionnaireID = Integer.parseInt(answersArray.get(x).getQuestionId()) /100;
			Log.d("quuestionnaireid", ""+ answersArray.get(x).getQuestionId());
			question.put("QuestionnaireID", QuestionnaireID);
			question.put("Answer", rofl);
			question.put("QuestionID", answersArray.get(x).getQuestionId());
			question.put("PatientID", patientID);
			
			answersJsonArray.add(question);
			
		}
		
		Log.i("UGH", answersJsonArray.toString());
		return answersJsonArray.toString();
	}
	public String setJSONpatient(){
	

		
			JSONObject patient = new JSONObject();
			
			// Add in first name edittext for login part
			// date format = dd-mm-yyyy
			// patient id = date.concat("." + lastName);
			patient.put("IDPatient", patientID); //DobStr+"."+lastName
			patient.put("firstname", patientFirstName); // firstName
			patient.put("lastname", patientLastName); // lastName
			patient.put("dateofbirth", patientDoBStr); //DobStr
			
			Log.i("patientID", patient.get("IDPatient").toString());
			Log.i("first name", patient.get("firstname").toString());
			Log.i("second name", patient.get("lastname").toString());
			Log.i("date of birth", patient.get("dateofbirth").toString());
			
			patientJsonArray.add(patient);		
		
		return patientJsonArray.toString();
				
	}

			
	/**
	 * Populates the arraylist containing information about the questionnaire.
	 * This arraylist takes in objects of the QuestionnaireInfo class.
	 * I have derived information about the questionnaire through parsing the JSON data sent in from the Doctor's desktop java app		
	 */
	private void addToArrayList() {
		// TODO Auto-generated method stub
		
		
		JSONParser parser=new JSONParser();
		  try{
		         Object obj = parser.parse(questionnaireString);
		         JSONArray array = (JSONArray)obj;
		// This loop adds to our arraylist
		         List<String> choiceArray = new ArrayList<String>() ;
		for(int i = 0; i < array.size(); i++)
		{
			choiceArray.clear();
			JSONObject everyQuestion = (JSONObject)array.get(i);
			
			String questionText =  everyQuestion.get("QuestionnaireText").toString();
			
			
				choiceArray.add(everyQuestion.get("ch1").toString());
			
			
			
				choiceArray.add(everyQuestion.get("ch2").toString());
			
			
				choiceArray.add(everyQuestion.get("ch3").toString());
				
				
				choiceArray.add(everyQuestion.get("ch4").toString());
			
				
				choiceArray.add(everyQuestion.get("ch5").toString());
			
				
			
			String questionType = everyQuestion.get("QuestionType").toString();
		
			String questionId = everyQuestion.get("QuestionID").toString();
			infoArray.add(new QuestionnaireInfo(questionText, choiceArray, questionType, questionId));
			
		}
		  }catch(ParseException pe){
		         System.out.println("position: " + pe.getPosition());
		         System.out.println(pe);
		      }
	}

	
}
