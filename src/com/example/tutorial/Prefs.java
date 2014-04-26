package com.example.tutorial;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Prefs extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		// TODO: find new way to do this:
		addPreferencesFromResource(R.xml.prefs);
	}

}
