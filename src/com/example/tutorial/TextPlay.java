package com.example.tutorial;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.format.Time;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TextPlay extends Activity implements View.OnClickListener {
	Button chkCmd;
	ToggleButton passTog;
	EditText input;
	TextView display;

	private void init() {
		chkCmd = (Button) findViewById(R.id.bResults);
		passTog = (ToggleButton) findViewById(R.id.tbPassword);
		input = (EditText) findViewById(R.id.etCommands);
		display = (TextView) findViewById(R.id.tvResults);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);

		init();

		passTog.setOnClickListener(this);
		chkCmd.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.bResults:
				String check = input.getText().toString();

				display.setText(check);

				if (check.contentEquals("left")) {
					display.setGravity(Gravity.LEFT);
				}
				else if (check.contentEquals("center")) {
					display.setGravity(Gravity.CENTER);
				}
				else if (check.contentEquals("right")) {
					display.setGravity(Gravity.RIGHT);
				}
				else if (check.contentEquals("blue")) {
					display.setTextColor(Color.BLUE);
				}
				else if (check.contentEquals("WTF")) {
					Random rnd = new Random();
					display.setText("WTF!!!!!");
					display.setTextSize(rnd.nextInt(75));
					display.setTextColor(Color.rgb(rnd.nextInt(255),
							rnd.nextInt(255), rnd.nextInt(255)));
				}
				else {
					display.setText("invalid");
					display.setGravity(Gravity.CENTER);
				}
			break;

			case R.id.tbPassword:
				input.setInputType(passTog.isChecked() ? InputType.TYPE_CLASS_TEXT
						| InputType.TYPE_TEXT_VARIATION_PASSWORD
						: InputType.TYPE_CLASS_TEXT);
			break;
		}
	}
}