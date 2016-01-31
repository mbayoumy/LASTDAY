package com.example.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
/**
 * The purpose of this class is to collect some personal information from the patient so to be able to link the specific questionnaire to a specific patient.
 * The information collected are the Date of Birth and the Surname.
 * 
 * @author Cristina Burello
 */
public class LoginTwo extends Activity {

	/**
	 * Called so the user doesn't accidentally go back to the doctor's validation activity.
	 */
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
	}

	DatePicker patientDoB;
	EditText patientSurname, patientfirstNameET;
	Button loginButton;
	int patientDoBDayInt, patientDoBMonthInt, patientDoBYearInt;
	String patientDoBDayStr, patientDoBMonthStr, patientDoBYearStr, patientDoBStr, patientID, patientFirstName, patientLastName;
	String previousFirstName, previousLastName, previousDoB;
	private String JSONstringTemp;
	AlertDialog.Builder alert;
	int counter = 5;
	/**
	 * Method that launches the login activity and it records the Date of Birth and the Surname of the patient when pressing the 'Log in' button
	 * The DoB is saved as a string in the format DD-MM-YYYY when day/month >10 and D-M-YYYYif day/month <10.
	 * It then adds the surname to it so that the final string with the patient ID will be "DD-MM-YYYY.Surname"
	 * 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		setupAlertDialog();
		JSONstringTemp = getIntent().getExtras().getString("qString");
		previousFirstName = getIntent().getExtras().getString("pFirst").toLowerCase();
		previousLastName = getIntent().getExtras().getString("pLast").toLowerCase();
		previousDoB = getIntent().getExtras().getString("pDOB").toLowerCase();
		
		Log.i("firstname", previousFirstName);
		Log.i("lastname", previousLastName);
		Log.i("previousdob", previousDoB);
		//Log.i("loaoslsaf", a);

		patientDoB = (DatePicker) findViewById(R.id.datepicker);
		patientSurname = (EditText) findViewById(R.id.login_surname_ET);
		patientfirstNameET = (EditText) findViewById(R.id.login_first_name_ET);
		
		findViewById(R.id.login_button).setOnClickListener( 
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						patientDoBYearInt = patientDoB.getYear(); // retrieves the year,
						patientDoBMonthInt = patientDoB.getMonth() + 1; // the month (It adds 1 because the months are recorded in an array)
						patientDoBDayInt = patientDoB.getDayOfMonth(); // and the day on the datePicker widget.
						
						patientDoBYearStr = Integer.toString(patientDoBYearInt); // parses all the integers to string
						patientDoBMonthStr = Integer.toString(patientDoBMonthInt); // ready to be added together to make 
						patientDoBDayStr = Integer.toString(patientDoBDayInt); // the date of birth
						patientDoBStr = patientDoBYearStr +"-"+ patientDoBMonthStr +"-"+ patientDoBDayStr; // builds the string with the full date
																												
						patientID = patientDoBStr + "." + patientSurname.getText(); // adds the string with the surname to the string with the date
						System.out.println(patientID);
						
						patientFirstName = patientfirstNameET.getText().toString();
						patientLastName = patientSurname.getText().toString();
						
						Log.i("FIRSTNAME", patientFirstName);
						Log.i("LASTNAME", patientLastName);
						Log.i("DOB", patientDoBStr);
						
						if(patientFirstName.trim().toLowerCase().equals(previousFirstName.trim().toLowerCase())
								&& patientLastName.trim().toLowerCase().equals(previousLastName.trim().toLowerCase())
								&& patientDoBStr.trim().toLowerCase().equals(previousDoB.trim().toLowerCase()))
						{
							Intent intent = new Intent(LoginTwo.this, MainActivity.class);
							intent.putExtra("qString", JSONstringTemp);
							intent.putExtra("pId", patientID);
							intent.putExtra("pFirst", patientFirstName);
							intent.putExtra("pLast", patientLastName);
							intent.putExtra("pDOB", patientDoBStr);
							startActivity(intent);
						}
						
						else
						{
							if(counter == 0)
							{
								alert.setMessage("Please get assistance from the Doctor.");
								
								
							}
							else{
								
							
							counter--;
							alert.setMessage("Unfortunately, there has been an error. " + counter + " tries left.");
							}
							
							alert.show();
						}
					}
				});
	}

	private void setupAlertDialog() {
		// TODO Auto-generated method stub
		alert = new AlertDialog.Builder(this);
		
		alert.setTitle("Error");
		alert.setMessage("Unfortunately, there has been an error. " + counter + " tries left.");
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
