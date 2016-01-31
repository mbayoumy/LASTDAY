package com.example.test;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * This button renderer has been tested and works (maybe some bugs)
 * All I need to do here is implement for buttons in QuestionnareActivity.
 * 
 * Some lines which have been commented out were old of course!
 * 
 * @author Jug-raj
 *
 */
public class ButtonRenderer extends Button
{
	private Context cont;
	private Button theButton;
	
	private Boolean buttonIsSelectedPositively = true;
	private String selectedChoice = "";
	
	public ButtonRenderer(Context context, Button b) {
		super(context);
		cont = context;
		theButton = b;
		// TODO Auto-generated constructor stub
		setButtonListener();
		
	}
	
	/**
	 * This method sets button listeners
	 * There may need to be some changes here, I will work on that.
	 */
	private void setButtonListener() {
		// TODO Auto-generated method stub
		theButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(buttonIsSelectedPositively)
				{
					theButton.setTextColor(Color.GREEN);
					buttonIsSelectedPositively = false; // upon selection, we set this as false, since the next time they'll select
					// it, it'll mean it is selected negatively.
					selectedChoice = theButton.getText().toString() + ", ";
					//userInputInfo = QuestionnareActivity.userInputInfoTV.getText().toString().concat(selectedChoice);
					//QuestionnareActivity.userInputInfoTV.setText(userInputInfo);
				}
				else
				{
					theButton.setTextColor(Color.BLACK);
					buttonIsSelectedPositively = true;
					
					//String newStr = QuestionnareActivity.userInputInfoTV.getText().toString().replace(theButton.getText()+", ", "");
					//TestFlipper.userInputInfoTV.setText(newStr);
					
					
				}
				
			}
			
		});
	}


	private boolean getIsButtonSelectedPositively()
	{
		return buttonIsSelectedPositively;
	}
	
	
	
	
}