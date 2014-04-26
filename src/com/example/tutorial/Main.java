package com.example.tutorial;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main extends Activity {

	int counter;
	Button add, sub;
	TextView display;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        counter = 0;
        add = (Button) findViewById(R.id.add);
        sub = (Button) findViewById(R.id.sub);
        display = (TextView) findViewById(R.id.display);

        add.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				++counter;
				display.setText("Your total is "+ counter);
			}
		});
        
        sub.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				--counter;
				display.setText("Your total is "+ counter);
			}
		});
    }
}
