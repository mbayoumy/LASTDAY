package com.example.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;


/**
 * 
 * This was just done for testing purposes
 * @author Jug-raj
 *
 */
public class CalenderViewTest extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enter_date_layout);
		
		/**final CalendarView calendar = (CalendarView) findViewById(R.id.calendarView1);
		calendar.setOnDateChangeListener(new OnDateChangeListener(){

			@Override
			public void onSelectedDayChange(CalendarView view, int year,
					int month, int dayOfMonth) {
				// TODO Auto-generated method stub
				Log.i("Date is", "" + dayOfMonth + "/" + month + "/" + year);
			}
			
		});
		
		calendar.setMinDate(3101994);*/
	}
	
}