package com.example.tutorial;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {
	String classes[] = { "Main", "TextPlay", "Email", "Camera", "Data", "GFX", "GFXSurface", "SoundStuff","Sliding" };

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater m = getMenuInflater();
		m.inflate(R.menu.cool_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
			case R.id.mnuAboutUs:
				Intent i = new Intent("com.example.tutorial.ABOUT");
				startActivity(i);
			break;

			case R.id.mnuPrefs:
				Intent p = new Intent("com.example.tutorial.PREFS");
				startActivity(p);
			break;

			case R.id.mnuExit:
				finish();
			break;
		}

		return false;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		try {
			String clicked = classes[position];
			Class c = Class.forName("com.example.tutorial." + clicked);

			Intent i = new Intent(Menu.this, c);
			startActivity(i);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
