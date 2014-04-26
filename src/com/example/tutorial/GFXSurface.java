package com.example.tutorial;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener {
	public class CustomSurfaceView extends SurfaceView implements Runnable {
		SurfaceHolder h;
		Thread t = null;
		boolean isRunning = false;
		Bitmap ball, plus;

		public CustomSurfaceView(Context context) {
			super(context);
			h = getHolder();
			ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
			plus = BitmapFactory.decodeResource(getResources(), R.drawable.plus_normal);
		}

		public void pause() {
			isRunning = false;
			while (true) {
				try {
					t.join();
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
			t = null;
		}

		public void resume() {
			isRunning = true;
			t = new Thread(this);
			t.start();
		}

		@Override
		public void run() {
			while (isRunning) {
				if (!h.getSurface().isValid()) {
					continue;
				}

				Canvas canvas = h.lockCanvas();
				canvas.drawRGB(2, 2, 150);

				if (x != 0 && y != 0) {
					canvas.drawBitmap(plus, x - plus.getWidth() / 2, y - plus.getHeight() / 2, null);
				}
				if (startX != 0 && startY != 0) {
					canvas.drawBitmap(plus, startX - plus.getWidth() / 2, startY - plus.getHeight() / 2, null);
				}
				if (endX != 0 && endY != 0) {
					canvas.drawBitmap(plus, endX - plus.getWidth() / 2, endY - plus.getHeight() / 2, null);
					canvas.drawBitmap(ball, endX - ball.getWidth() / 2 - animX, endY - ball.getHeight() / 2 - animY, null);
				}

				animX += dX;
				animY += dY;

				h.unlockCanvasAndPost(canvas);
			}
		}
	}

	CustomSurfaceView ourSurfaceView;

	float x, y, startX, startY, endX, endY, dX, dY, animX, animY;

	@Override
	protected void onResume() {
		super.onResume();
		ourSurfaceView.resume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		ourSurfaceView.pause();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ourSurfaceView = new CustomSurfaceView(this);
		ourSurfaceView.setOnTouchListener(this);

		x = y = startX = startY = endX = endY = dX = dY = animX = animY = 0;
		setContentView(ourSurfaceView);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		// 1000 * 1s / fps
		try {
			Thread.sleep(50);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		x = event.getX();
		y = event.getY();
		
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				dX = dY = animX = animY = 0;

				endX = endY = 0;
				
				startX = event.getX();
				startY = event.getY();
			break;

			case MotionEvent.ACTION_UP:
				endX = event.getX();
				endY = event.getY();

				dX = (endX - startX) / 30;
				dY = (endY - startY) / 30;
			break;
		}

		// return true to have the continuous touch event (ball follow drag)
		return true;
	}
}
