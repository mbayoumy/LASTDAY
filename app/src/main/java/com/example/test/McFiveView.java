package com.example.test;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;



public class McFiveView extends View
{
	Context context;
	View view;
	CheckBox choiceOne, choiceTwo, choiceThree, choiceFour, choiceFive;
	boolean choiceOneIsSelected = false; boolean choiceTwoIsSelected = false; boolean choiceThreeIsSelected = false;
	boolean choiceFourIsSelected = false; boolean choiceFiveIsSelected = false;
	TextView questionText, questionNumber, questionBrief;
	ArrayList<QuestionnaireInfo> infoArray;
	
	public McFiveView(Context c, View v, ArrayList<QuestionnaireInfo> iA) {
		super(c);
		
		this.context = c;
		this.view = v;
		this.infoArray = iA;
		
		
		// TODO Auto-generated constructor stub
	}
	
	public void inflateLayout() {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		view = inflater.inflate(R.layout.multiple_choice_five_layout, null);
	}

	public void setOnClickListeners(final CheckBox b, boolean bool) {
		// TODO Auto-generated method stub
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(b.isChecked())
				{
					b.setTextColor(Color.GREEN);
				}
				else
				{
					b.setTextColor(Color.BLACK);
				}
			}
			
		});
		
	}

	public void getButtons()
	{
		choiceOne = (CheckBox) view.findViewById(R.id.mc_five_choice1_button);
		choiceTwo = (CheckBox) view.findViewById(R.id.mc_five_choice2_button);
		choiceThree = (CheckBox) view.findViewById(R.id.mc_five_choice3_button);
		choiceFour = (CheckBox) view.findViewById(R.id.mc_five_choice4_button);
		choiceFive = (CheckBox) view.findViewById(R.id.mc_five_choice5_button);
		
	}
	
}