package com.example.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
/**
 * The purpose of this class is to collect some personal information from the patient so to be able to link the specific questionnaire to a specific patient.
 * The information collected are the Date of Birth and the Surname.
 * 
 * @author Cristina Burello, Jug-Raj Grewal
 */
public class Login extends Activity {

	DatePicker patientDoB;
	EditText patientSurname, patientfirstNameET;
	TextView patientfirstNameTV, patientsurnameTV, dateOfBirthTV;
	Button loginButton;
	int patientDoBDayInt, patientDoBMonthInt, patientDoBYearInt;
	String patientDoBDayStr, patientDoBMonthStr, patientDoBYearStr, patientDoBStr, patientID, patientFirstName, patientLastName;
	AlertDialog.Builder alert;
	private String JSONstringTemp;
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
		
		setTextViews();
		
		
		JSONstringTemp = getIntent().getExtras().getString("qString");
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
						
						setupAlertDialogs();
						alert.show();
					}
				});
	}

	private void setupAlertDialogs() {
		// TODO Auto-generated method stub
		
			// TODO Auto-generated method stub
			alert = new AlertDialog.Builder(this);
			
			alert.setTitle("Confirm");
			alert.setMessage("You have chosen \n\n- " + patientFirstName + "\n- " + patientLastName + "\n- " + patientDoBStr + "\n\n" +
					"Press continue to go ahead");
			
			alert.setNegativeButton("Cancel",
	                new DialogInterface.OnClickListener() {

	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                dialog.dismiss();
	            }
	        });
			
			alert.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.dismiss();
					Intent intent = new Intent(Login.this, LoginTwo.class);
					intent.putExtra("qString", JSONstringTemp);
					intent.putExtra("pId", patientID);
					intent.putExtra("pFirst", patientFirstName);
					intent.putExtra("pLast", patientLastName);
					intent.putExtra("pDOB", patientDoBStr);
					startActivity(intent);
					finish();
				}
			});
		
	}

	private void setTextViews() {
		// TODO Auto-generated method stub
		dateOfBirthTV = (TextView) findViewById(R.id.login_dob_tv);
		dateOfBirthTV.setText("What is the patient's Date of Birth?");
		
		patientfirstNameTV = (TextView) findViewById(R.id.login_first_name_TV);
		patientfirstNameTV.setText("What is the patient's first name?");
		
		patientsurnameTV = (TextView) findViewById(R.id.login_surname_TV);
		patientsurnameTV.setText("What is the patient's last name?");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
