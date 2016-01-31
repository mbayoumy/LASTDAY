package com.example.test;

/**
 * 
 */


import java.util.List;

import org.json.JSONException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


/**
 * The answersJsonArray activity!
 * The tourguide button doesn't have a proper video... Yet.
 * Just put in a 3gp file in the 'raw' folder (located in the 'res' folder) and then point to it 
 * from the TourGuide class. TourGuide class needs a tutorial on using the tablet too
 * @author Cristina Burello
 * 
 */
public class MainActivity extends Activity {

	String questionnaireData = "{questions:[[\"101\",\"1\",\"Question1Question1Question1Question1Question1?\",\"Choice1.1\",\"Choice1.2\",\"Choice1.3\",\"Choice1.4\",\"Choice1.5\",\"Single choice\"],[\"102\",\"1\",\"Question2Question2Question2Question2Question2Question2Question2Question2?\",\"Choice2.1\",\"Choice2.2\",\"\",\"\",\"\",\"Numerical\"],[\"103\",\"1\",\"Question3Question3Question3Question3?\",\"Choice3.1\",\"Choice3.2\",\"Choice3.3\",\"\",\"\",\"Multiple choice\"]]}";
	GetQuestionsElements questionnaireObject;
	GetQuestionsElements questionsData;
	private String a;
	TextView displayPatientNameTV;
	
	String patientID, patientFirstName, patientLastName, patientDoBStr;
	Intent tutorialIntent, tourIntent;
	AlertDialog.Builder startQuizAlert, startTutorialAlert, startTourAlert;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		a = getIntent().getExtras().getString("qString");
		patientID = getIntent().getExtras().getString("pId");
		patientFirstName = getIntent().getExtras().getString("pFirst");
		patientLastName = getIntent().getExtras().getString("pLast");
		patientDoBStr = getIntent().getExtras().getString("pDOB");
		
		setupTextViews();
		setupAlertDialogs();
		//Log.i("LASSFDA", a);
		findViewById(R.id.main_start_quiz_button).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startQuizAlert.show();
			}
			
		});
		try {
			questionnaireObject = new GetQuestionsElements(questionnaireData);
			
			String testQuestnaireID = questionnaireObject.getQuestionnaireID(1);
			Log.v("FINE! - this is the ID of the questionnaire", testQuestnaireID); 
			
			String testQuestText1 = questionnaireObject.getQuestionText(1);
			String testQuestText2 = questionnaireObject.getQuestionText(2);
			String testQuestText3 = questionnaireObject.getQuestionText(3);
			Log.v("Q1", testQuestText1); 
			Log.v("Q2", testQuestText2); 
			Log.v("Q3", testQuestText3); 
			
			String testQuestType1 = questionnaireObject.getQuestionType(1);
			String testQuestType2 = questionnaireObject.getQuestionType(2);
			String testQuestType3 = questionnaireObject.getQuestionType(3);
			Log.v("FINE! - template for question 1", testQuestType1);
			Log.v("FINE! - template for question 2", testQuestType2);
			Log.v("FINE! - template for question 3", testQuestType3);
			
			List<String> testAnswer1 = questionnaireObject.getAnswerChoices(1); //TEST
			List<String> testAnswer2 = questionnaireObject.getAnswerChoices(2); //TEST
			List<String> testAnswer3 = questionnaireObject.getAnswerChoices(3); //TEST
			Log.v("FINE! - these are the first 3 choices of the answer for question 1", testAnswer1.get(0) + ", " + testAnswer1.get(1) + ", " + testAnswer1.get(2)); 
			Log.v("FINE! - these are the first 3 choices of the answer for question 2", testAnswer2.get(0) + ", " + testAnswer2.get(1) + ", " + testAnswer2.get(2)); 
			Log.v("FINE! - these are the first 3 choices of the answer for question 3", testAnswer3.get(0) + ", " + testAnswer3.get(1) + ", " + testAnswer3.get(2)); 	 

			int count = 0;
			for(int i = 0; i < testAnswer1.size(); i++)
			{
				if(testAnswer1.get(i).toString().trim().length() != 0)
				{
					count++;
				}
			}
			Log.i("Count of choices of questions is", " " + count);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Button startTourButton = (Button) findViewById(R.id.main_start_tour_button);
		startTourButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startTourAlert.show();
			}
			
		});
		
		Button startTutorialButton = (Button) findViewById(R.id.main_start_tutorial);
		startTutorialButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startTutorialAlert.show();
			}
		});
		
		Button startQuizButton = (Button) findViewById(R.id.main_start_quiz_button);
		startQuizButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startQuizAlert.show();
				
			}
			
		});
	}

	private void setupAlertDialogs() {
		// TODO Auto-generated method stub
		startQuizAlert = new AlertDialog.Builder(this);
		startQuizAlert.setTitle("Start questionnaire");
		startQuizAlert.setMessage("You have decided to start the questionnaire. Please click 'Get started' to go ahead, or 'No, take me" +
				"back to go back.");
		
		startQuizAlert.setNegativeButton("No, I want to go back",  new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		}) ;
		
		startQuizAlert.setPositiveButton("Get started",  new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Intent intent = new Intent(MainActivity.this, QuestionnareActivity.class);
				intent.putExtra("pId", patientID);
				intent.putExtra("pFirst", patientFirstName);
				intent.putExtra("pLast", patientLastName);
				intent.putExtra("pDOB", patientDoBStr);
				intent.putExtra("qString", a);
				Log.i("MA STRING", a);
				startActivity(intent);
			}
		}) ;
		
		startTourAlert = new AlertDialog.Builder(this);
		startTourAlert.setTitle("Start tour");
		startTourAlert.setMessage("You have decided to start the tour. Please click 'Get started' to go ahead, or 'No, take me" +
				"back to go back.");
		
		startTourAlert.setNegativeButton("No, I want to go back",  new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		}) ;
		
		startTourAlert.setPositiveButton("Get started",  new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Intent intent = new Intent(MainActivity.this, TourGuide.class);
				startActivity(intent);
			}
		}) ;
		
		startTutorialAlert = new AlertDialog.Builder(this);
		startTutorialAlert.setTitle("Start tutorial");
		startTutorialAlert.setMessage("You have decided to start the tutorial. Please click 'Get started' to go ahead, or 'No, take me" +
				"back to go back.");
		
		startTutorialAlert.setNegativeButton("No, I want to go back",  new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		}) ;
		
		startTutorialAlert.setPositiveButton("Get started",  new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Intent intent = new Intent(MainActivity.this, TourGuide.class);
				startActivity(intent);
			}
		}) ;
	}


	private void setupTextViews() {
		// TODO Auto-generated method stub
		displayPatientNameTV = (TextView) findViewById(R.id.main_user_full_name_TV);
		displayPatientNameTV.setText(patientLastName + ", " + patientFirstName);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
