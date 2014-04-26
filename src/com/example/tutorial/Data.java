package com.example.tutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Data extends Activity implements View.OnClickListener {

	Button start, startFor;
	EditText sendET;
	TextView gotAnswer;

	private void initialize() {
		start = (Button) findViewById(R.id.bSA);
		startFor = (Button) findViewById(R.id.bSAFR);
		sendET = (EditText) findViewById(R.id.etSend);
		gotAnswer = (TextView) findViewById(R.id.tvGot);

		start.setOnClickListener(this);
		startFor.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get);
		initialize();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.bSA:
				String data = sendET.getText().toString();
				Bundle b = new Bundle();
				b.putString("data", data);
				
				Intent i = new Intent(Data.this, OpenClass.class);
				i.putExtras(b);
				startActivity(i);
			break;

			case R.id.bSAFR:
				Intent j = new Intent(Data.this, OpenClass.class);
				
				startActivityForResult(j, 0);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == RESULT_OK){
			Bundle b = data.getExtras();
			String response = b.getString("response");
			
			gotAnswer.setText(response);
		}
	}
}
