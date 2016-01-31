package com.example.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//http://examples.javacodegeeks.com/android/core/socket-core/android-socket-example/
//change varables names DONT FORGET.

public class Client extends Activity {

    private Socket connection;
    public String questionnaire;
    private static final int SERVERPORT = 4117; 
   
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Button send,close, open, start, restart;
    private EditText ip;
    private TextView status;
    private AlertDialog.Builder alert, npeAlert;
    
    private String questionnaireString;
    
    boolean isLastScreen;
	@Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.the_client_layout);     
        
        determineIsLastScnree();
        setupNullPointerExceptionDialog();
       
        
        send = (Button) findViewById(R.id.SendData);
        
        restart = (Button) findViewById(R.id.restartQuestionnaire);
        restart.setOnClickListener(new View.OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(Client.this, Login.class);
				intent.putExtra("qString", questionnaireString);
				startActivity(intent);
				finish();
			}
		});
        
        start  = (Button) findViewById(R.id.startWholeThing);
        if(isLastScreen == true)
        {
        	questionnaireString = getIntent().getExtras().getString("qString");
        	Log.i("QSTRING", questionnaireString);
        	start.setVisibility(View.GONE);
        	setupAlertDialog();
        	alert.show();
        }
        else
        {
        	send.setVisibility(View.GONE);
        	restart.setVisibility(View.GONE);
        }
        send.setClickable(false);
        
        start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				if(status.getText().toString().toLowerCase().equals("data received"))
				{
					try {
						
						output.writeObject("connection ended");
						output.flush();
						output.close();
						input.close();
						connection.close();
						showStatus("Connection ended");
						Intent intent = new Intent(Client.this, Login.class);
						intent.putExtra("qString", questionnaire);
						
						startActivity(intent);
						finish();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
					
					catch(NullPointerException e)
					{
						npeAlert.show();
					}
				}
				else
				{
					npeAlert.show();
				}
				
				
				
			}
		});
        
        
        
        send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

		        try {	         
		        	QuestionnareActivity a = new QuestionnareActivity();
		        	String lala = a.answersJsonArray.toString();
		    		output.writeObject(lala);
		    		output.flush();
		    		output.writeObject(a.patientJsonArray.toString());
		    		output.flush();
		    		showStatus("Data Sent");
		    		
		        } catch (UnknownHostException e) {
		            e.printStackTrace();
		            System.out.println("host");
		        } catch (IOException e) {
		            e.printStackTrace();
		            System.out.println("io");
		        } catch (Exception e) {

		            e.printStackTrace();
		            System.out.println("any e");
		        }			
			}
		});
        
       
        ip = (EditText) findViewById(R.id.ip) ;   
        ip.setText("159.92.183.112");
        open = (Button) findViewById(R.id.openConnection);
        status = (TextView) findViewById(R.id.status);      
        
        open.setOnClickListener(new OnClickListener() {
 		
 		@Override
 		public void onClick(View arg0) {
 			
 				
 				 new Thread(new ClientThread()).start();
 				 
 			
 			
 		}
 	});
      
    }
	
	private void setupNullPointerExceptionDialog() {
		// TODO Auto-generated method stub
		npeAlert = new AlertDialog.Builder(this);
		npeAlert.setTitle("Error");
		
		npeAlert.setMessage("Unfortunately, there has been an error. \n\nMake sure to click 'Open Connection' before clicking this button" +
				" and that the 'Data received' text is on the screen.");
		
		
		npeAlert.setNeutralButton("Ok",  new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
	}

	private void setupAlertDialog() {
		// TODO Auto-generated method stub
		
			// TODO Auto-generated method stub
			
				// TODO Auto-generated method stub
				alert = new AlertDialog.Builder(this);
				
				alert.setTitle("Thank you");
				alert.setMessage("\n\nThank you for completing the questionnaire. \n\nPlease return the tablet back to the Doctor.\n\n");
				
			
				
		
		
	}

	private void determineIsLastScnree() {
		// TODO Auto-generated method stub
		try
		{
			if(getIntent().getExtras().getBoolean("isFromQuestionnaireActivity"))
			{
				isLastScreen = true;
			}
			else
			{
				isLastScreen = false;
			}
		}
		
		catch(NullPointerException e)
		{
			isLastScreen = false;
		}
		
	}

	public String setJSONfeedback(){
		JSONArray main = new JSONArray();
		
		int noOfquestions = 2;

		for(int x = 0 ; x < noOfquestions ; x++){
			JSONObject question = new JSONObject();
			
			
			question.put("PatientID", "13-07-94.Bayoumy");
			question.put("QuestionID", new Integer(104));
			question.put("Answer", "very good");
			
			main.add(question);
			
		}
		return main.toString();
	}
	public String setJSONpatient(){
		   
	  	JSONArray main = new JSONArray();
		
		int noOfquestions = 2;

		for(int x = 0 ; x < noOfquestions ; x++){
			JSONObject patient = new JSONObject();
			
			
			patient.put("IDPatient", "13-07-94.Bayoumy");
			patient.put("firstname", "Mohamed");
			patient.put("lastname", "Bayoumy");
			patient.put("dateofbirth", "12-02-1500");
			
			
			main.add(patient);		
		}
		return main.toString();
				
	}
	public void showStatus(final String statusT){
		
		Client.this.runOnUiThread(new Runnable() {				
			@Override
			public void run() {
				status.setText(statusT);				
			}
		});	
	
	}


    class ClientThread implements Runnable {	

    	
    	
    	
		@Override
        public void run() {

            try {
            	
                              
            	
                InetAddress serverAddr = InetAddress.getByName(ip.getText().toString());
                connection = new Socket(serverAddr, SERVERPORT);                
                output = new ObjectOutputStream(connection.getOutputStream());
	    		output.flush(); 
	    		input = new ObjectInputStream(connection.getInputStream());
	    		send.setClickable(true);
	                	
	    		whileConnected();
                
            } catch (UnknownHostException e1) {
            	 System.out.println("host");
                e1.printStackTrace();
            } catch (IOException e1) {
            	 System.out.println("io");
                e1.printStackTrace();
            }
        }
		public void whileConnected() throws IOException{
			
			String message ="Connected to admin app" ;
			showStatus(message);
						
			do{
				try{
					message = (String) input.readObject();
					if(message.charAt(3) == 'Q'||message.charAt(3) == 'c'){
						sendData(message);
					}
					
										
				}catch(ClassNotFoundException c){
					System.out.println("could not send message");
				}				
			}while(!message.equals("end connection"));	
			
			showStatus("connection ended");
		}
		
		
		public void sendData(String data){
			questionnaire = data;
			
			System.out.println(questionnaire);
			showStatus("Data received");
			
		}
    }
}
