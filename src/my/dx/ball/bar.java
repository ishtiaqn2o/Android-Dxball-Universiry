package my.dx.ball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class bar extends drawable  {
public Rect br;
Paint paint;
int count=0;
public bar(Rect br){
	this.br=br;

paint= new Paint();
paint.setColor(Color.BLACK);
	
}
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		
		canvas.drawRect(br, paint);
		//Log.i("MyApp","count"+count++);
	}


}
