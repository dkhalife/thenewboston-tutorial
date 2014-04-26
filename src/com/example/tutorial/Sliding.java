package com.example.tutorial;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SlidingDrawer;

public class Sliding extends Activity implements OnClickListener, OnCheckedChangeListener, SlidingDrawer.OnDrawerOpenListener {
	Button handle1, handle2, handle3, handle4;
	CheckBox cb;
	SlidingDrawer sd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sliding);

		handle1 = (Button) findViewById(R.id.handle1);
		handle2 = (Button) findViewById(R.id.handle2);
		handle3 = (Button) findViewById(R.id.handle3);
		handle4 = (Button) findViewById(R.id.handle4);
		cb = (CheckBox) findViewById(R.id.cbSlideable);
		sd = (SlidingDrawer) findViewById(R.id.slidingD); 
		
		handle1.setOnClickListener(this);
		handle2.setOnClickListener(this);
		handle3.setOnClickListener(this);
		handle4.setOnClickListener(this);
		cb.setOnCheckedChangeListener(this);
		sd.setOnDrawerOpenListener(this);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if(isChecked){
			sd.lock();
		}
		else{
			sd.unlock();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.handle1:
				sd.open();
			break;

			case R.id.handle2:
				sd.toggle();
			break;

			case R.id.handle3:
				sd.close();
			break;

			case R.id.handle4:
				
			break;
		}
	}

	@Override
	public void onDrawerOpened() {
		MediaPlayer mp = MediaPlayer.create(this, R.raw.bomb);
		mp.start();
	}
}
