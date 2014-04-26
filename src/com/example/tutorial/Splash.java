package com.example.tutorial;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Splash extends Activity {
	MediaPlayer sound;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		sound = MediaPlayer.create(Splash.this, R.raw.splash);
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		if(prefs.getBoolean("introMusic", true)){
			sound.start();
		}
		
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(2000);
				} catch(InterruptedException e){
					e.printStackTrace();
				} finally {
					Intent splash = new Intent("com.example.tutorial.MENU");
					startActivity(splash);
				}
			}
		};
		
		timer.start();
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		sound.release();
		finish();
	}
}
