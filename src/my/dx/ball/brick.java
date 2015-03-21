package my.dx.ball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;



public class brick extends drawable  {
public Rect brk;
Paint paint;
public int state;
public boolean first=false;
public brick(Rect brk){
	this.brk=brk;
	state = 0;

paint= new Paint();
paint.setColor(Color.rgb(71,2,159));
	
}
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		
		canvas.drawRect(brk, paint);
		//Log.i("MyApp","count"+count++);
	}


}

