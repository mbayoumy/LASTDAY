package com.example.test;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * This class is for the TourGuide
 * Needs tutorial implemented for using the tablet
 * @author Jug-raj
 *
 */
public class TourGuide extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tour_guide);
		
		VideoView tourVideo = (VideoView) findViewById(R.id.tour_guide_videoView);
		tourVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.tutorial_cancer));
		
		tourVideo.start();
		
		MediaController mediaController = new MediaController(this);
		mediaController.setMediaPlayer(tourVideo);
		tourVideo.setMediaController(mediaController);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tour_guide, menu);
		return true;
	}

}
