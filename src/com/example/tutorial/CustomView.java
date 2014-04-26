package com.example.tutorial;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.view.View;

public class CustomView extends View {
	
	Bitmap ball;
	Rect middleRect;
	Paint b;
	Typeface font;
	
	float animY;
	
	public CustomView(Context context) {
		super(context);

		animY = 0;
		
		ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
		middleRect = new Rect();
		
		b =  new Paint();
		b.setColor(Color.BLUE);
		
		font = Typeface.createFromAsset(context.getAssets(), "G-Unit.TTF");
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		canvas.drawColor(Color.WHITE);
		
		Paint textPaint = new Paint();
		textPaint.setARGB(50, 128, 0, 128);
		textPaint.setTextAlign(Align.CENTER);
		textPaint.setTextSize(50);
		textPaint.setTypeface(font);
		
		canvas.drawText("Heading", canvas.getWidth()/2, 200, textPaint);
		
		canvas.drawBitmap(ball, (canvas.getWidth() / 2) - (ball.getWidth() / 2), animY, null);
		
		if(animY < canvas.getHeight()){
			animY += 10;
		}
		else{
			animY = 0;
		}
		
		middleRect.set(50, 400, canvas.getWidth() - 50, 500);
		canvas.drawRect(middleRect, b);
		
		// loop
		invalidate();
	}
}
