package com.example.tutorial;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class OpenClass extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

	TextView question, test;
	Button returnData;
	RadioGroup answers;
	String data, response;

	private void initialize() {
		question = (TextView) findViewById(R.id.tvQuestion);
		test = (TextView) findViewById(R.id.tvTest);
		returnData = (Button) findViewById(R.id.bReturn);
		answers = (RadioGroup) findViewById(R.id.rgAnswers);

		answers.setOnCheckedChangeListener(this);
		returnData.setOnClickListener(this);
	}

	private void getData() {
		Bundle gotData = getIntent().getExtras();
		data = gotData.getString("data");

		question.setText(data);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		initialize();
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String et = prefs.getString("name", "Select your mood:");
		String values = prefs.getString("os", "win7");
		
		if(values.contentEquals("an")){
			question.setText(et);
		}
		
		//getData();
	}

	@Override
	public void onClick(View v) {
		Bundle b = new Bundle();
		b.putString("response", response);
		
		Intent i = new Intent();
		i.putExtras(b);
		setResult(RESULT_OK, i);
		finish();
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
			case R.id.rHappy:
				response = "Happy";
			break;

			case R.id.rSad:
				response = "Sad";
			break;

			case R.id.rBoth:
				response = "Both";
			break;
		}
		
		test.setText(response);
	}
}
