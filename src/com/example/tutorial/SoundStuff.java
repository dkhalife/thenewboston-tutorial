package com.example.tutorial;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.renderscript.RenderScript.Priority;
import android.view.View;
import android.view.View.OnLongClickListener;

public class SoundStuff extends Activity implements View.OnClickListener, OnLongClickListener {
	SoundPool sp;
	MediaPlayer mp;
	
	int explosion = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		View v = new View(this);
		v.setOnClickListener(this);
		v.setOnLongClickListener(this);
		setContentView(v);
		
		mp = MediaPlayer.create(this, R.raw.splash);

		sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		explosion = sp.load(this, R.raw.bomb, 0);
	}

	@Override
	public void onClick(View arg0) {
		if (explosion != 0) {
			sp.play(explosion, 1, 1, 0, 0, 1);
		}
	}

	@Override
	public boolean onLongClick(View arg0) {
		mp.start();
		return false;
	}
}
