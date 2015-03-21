package my.dx.ball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class ball extends drawable {
	
	public int x,y,count=0;
	Canvas canvas;
	Paint paint;
	int dx=3,dy=3;
	int r;
	public ball(int x, int y,int r) {
		// TODO Auto-generated constructor stub
		this.x =x;
		this.y = y;
		this.r=r;
		paint = new Paint();
		paint.setColor(Color.RED);
	}
	
	@Override
	public void draw(Canvas canvas) {
	// TODO Auto-generated method stub
		this.canvas=canvas;
	   canvas.drawCircle(x, y, r,this.paint);
	  // MoveRandomly();
	  // Log.i("MyApp","count"+count++);
	}

public	void MoveRandomly()

	{
	if(x+15>canvas.getWidth()){
		
		dx=-dx;
	}
if(x<15){
		
		dx=-dx;
	}
	if(y+15>canvas.getHeight()){
		
		dy=-dy;
	}
if(y<15){
		
		dy=-dy;
	}
		x+=dx;
		y+=dy;
	}
}
