package com.svauto.fastrvc.seekbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

public class MiddleSeekBar extends SeekBar {
	//private Rect rect;
	private RectF rectf;
	private Paint paint;
	private int seekbar_height;
	private boolean isPress;

	public MiddleSeekBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		//rect = new Rect();
		rectf = new RectF();
		paint = new Paint();
		seekbar_height = 6;
		isPress = false;
	}

	public MiddleSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
		this(context, attrs, defStyleAttr,0);
	}

	public MiddleSeekBar(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	public MiddleSeekBar(Context context) {
		this(context,null);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		rectf.set(0 + getPaddingLeft(), (getHeight() / 2) - (seekbar_height / 2), getWidth() - getPaddingLeft(),
				(getHeight() / 2) + (seekbar_height / 2));

		paint.setColor(Color.argb(0xFF, 0x01, 0x01, 0x01));

		canvas.drawRect(rectf, paint);

		if (this.getProgress() > 50) {
			
			rectf.set(getWidth() / 2.0f, (getHeight() / 2.0f) - (seekbar_height / 2.0f),
					getWidth() / 2.0f + ((getWidth() - getPaddingRight() - getPaddingLeft()) / 100.0f) * (getProgress() - 50.0f), getHeight() / 2.0f + (seekbar_height / 2.0f));

			if(isPress){
				paint.setColor(Color.argb(0xFF, 0xFF, 0x8C, 0));
			}else	
				paint.setColor(Color.RED);
			canvas.drawRect(rectf, paint);

		}

		if (this.getProgress() < 50) {

			rectf.set(getWidth() / 2.0f - (((getWidth() - getPaddingLeft() - getPaddingRight()) / 100.0f) * (50.0f - getProgress())),
					(getHeight() / 2.0f) - (seekbar_height / 2.0f), getWidth() / 2.0f, getHeight() / 2.0f + (seekbar_height / 2.0f));

			if(isPress){
				paint.setColor(Color.argb(0xFF, 0xFF, 0x8C, 0));
			}else	
				paint.setColor(Color.RED);
			canvas.drawRect(rectf, paint);
		}

		super.onDraw(canvas);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			isPress = true;
			break;
		case MotionEvent.ACTION_UP:
			isPress = false;
			break;
		}
		return super.onTouchEvent(event);
	}

}
