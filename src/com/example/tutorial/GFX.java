package com.example.tutorial;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class GFX extends Activity {
	CustomView ourView;
	WakeLock wL;

	@Override
	protected void onPause() {
		wL.release();
		super.onPause();
	}

	@Override
	protected void onResume() {
		wL.acquire();
		super.onResume();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Wake lock
		PowerManager pM = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wL = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK, "tag");

		super.onCreate(savedInstanceState);
		wL.acquire();
		ourView = new CustomView(this);
		setContentView(ourView);
	}
}
