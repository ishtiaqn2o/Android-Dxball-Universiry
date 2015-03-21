package my.dx.ball;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import my.dx.ball.DxtestActivity.viewsurface;

import android.R.color;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class DrawObject implements OnTouchListener {
	public ArrayList<drawable> all;
	viewsurface v;
	ball b;
	bar br;
	int numOfbrick;
	int  posTop =0;
	int  posLeft =0;
	int  posRight =0;
	int barPos=0;
	int count=0;
	ArrayList<brick> allBrick;
	 List<Integer> list= new ArrayList<Integer>();
	int width,height;
	boolean firstTime=true;
	int dx,dy;
	float closestX ;
	float closestY;
	boolean collison = false;
	boolean BarCollison = false;
	int  moveX;
	int moveY;
	stage st;
	Canvas can;
	Thread trd;
	
	public DrawObject(viewsurface v,Thread t) {
		// TODO Auto-generated constructor stub
		this.v=v;
		trd = t;
		v.setOnTouchListener(this);
	}


	public void Ondraw(Canvas canvas ,stage s){
		if(firstTime){
			
			
			firstTime=false;
			width=canvas.getWidth();
			height=canvas.getHeight();
		all = s.AllObject;
		b=(ball) all.get(0);
		br=(bar) all.get(1);
		allBrick=s.allBrick;
		dx=dy=s.ballSpeed;
		this.numOfbrick=s.NumOfBrick;
		this.st = s;
		this.can = canvas;
		
		
		
		
		}
		
		//draw Ball
		b.draw(canvas);
		//draw Bar
		br.draw(canvas);
	for(int i=0;i<allBrick.size();i++){
		//draw Brick
		allBrick.get(i).draw(canvas);
		
		
	}
		moverandomly();
	    
			
			
		
		
		
	}
	
	
	 public void moverandomly(){
		 if(collison){
			 if(b.y<posTop+b.r){
					
					dy=-dy;
					 posTop=0;
					 collison=false;
				}
			 if(b.x<posLeft+15){
					
					dx=-dx;
					 posLeft=0;
					 collison=false;
				}
			 if(b.x+b.r>posRight){
					dx=-dx;
					 posRight=0;
					 collison=false;
					//Log.i("MyApp","count");
					//this.dx=-this.dx;
				}
			
		 }
		 else if(BarCollison){
			 if(b.y+b.r>=barPos){
					
				 dy=-dy;
				barPos=0;
				 BarCollison=false;
				}
			
			 
			 
		 }
		 else{
			if(b.x+b.r>width){
				dx=-dx;
				//Log.i("MyApp","count");
				//this.dx=-this.dx;
			}
			if(b.x<b.r){
				
				dx=-dx;
			}
			if(b.y+b.r>height){
				
				//dy=-dy;
				dy = 0;
				dx = 0;
		        //trd.destroy();
			}
		if(b.y<b.r){
				
				dy=-dy;
			}
		 }
		 b.x+=dx;
		 b.y+=dy;
		 //Brick and ball Collision
		 for(int i=0;i<allBrick.size();i++)
		 {
		   collision(b,allBrick.get(i),i);
		 }
		 //Ball and Bar Collision
		 ColWithBar(b,br); 
	 }
	 
	public void collision(ball bl,brick bk,int brkNo){
		int closeX = bl.x;
		int closeY = bl.y;
	
		
		if(bl.x<bk.brk.left)
		{
			closeX =bk.brk.left ;
		}
		
		else if(bl.x>bk.brk.right)
		{
			closeX =bk.brk.right;
			
		}
		
		if(bl.y>bk.brk.bottom)
		{
			closeY =bk.brk.bottom;
		}
		else if(bl.y<bk.brk.top)
		{
			closeY =bk.brk.top;
		}
         
		int distx = (int) Math.pow((bl.x - closeX), 2);
		int disty = (int) Math.pow((bl.y - closeY), 2);
		int distsqr = distx + disty;

			
		if(Math.sqrt(distsqr)<bl.r)
		{
			
			collison=true;
			posTop=allBrick.get(brkNo).brk.bottom;
			posLeft=allBrick.get(brkNo).brk.left;
			posRight=allBrick.get(brkNo).brk.right;
		    allBrick.get(brkNo).state +=1;
		    allBrick.get(brkNo).paint.setColor(Color.BLACK);
		    Log.i("MyApp","state"+allBrick.get(brkNo).state);
		  /*if(allBrick.get(brkNo).first)
					{
					if(allBrick.get(brkNo).paint.getColor()==Color.BLACK)
					{
					  allBrick.remove(brkNo);
					}
				}*/
			
			if(allBrick.get(brkNo).state % 2 ==0)
			{
				allBrick.remove(brkNo);
				
				
			}
			if(allBrick.size() ==0)
			{
				//st.setLevel(2);
				//Ondraw(can, st);
				//DrawObject d = new DrawObject(v);
				stage stg = new stage(v.context);
				firstTime = true;
				try {
					stg.setLevel(2);
					Ondraw(can, stg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		   //allBrick.get(brkNo).first = true;
		
		   
			
			// Log.i("MyApp","collision"+brkNo);
			
				
					
				
		 
		  
		}
		
}
	
	
	public void  ColWithBar(ball bl,bar bar){
		int closeX = bl.x;
		int closeY = bl.y;
	
		
		if(bl.x<bar.br.left)
		{
			closeX =bar.br.left ;
		}
		
		else if(bl.x>bar.br.right)
		{
			closeX =bar.br.right;
		}
		
		if(bl.y>bar.br.bottom)
		{
			closeY =bar.br.bottom;
		}
		else if(bl.y<bar.br.top)
		{
			closeY =bar.br.top;
		}
         
		int distx = (int) Math.pow((bl.x - closeX), 2);
		int disty = (int) Math.pow((bl.y - closeY), 2);
		int distsqr = distx + disty;
		if(Math.sqrt(distsqr)<bl.r)
		{
			BarCollison=true;
			barPos=bar.br.top;
			
			
		 // Log.i("MyApp","collision");
		  
		}
}


	public boolean onTouch(View v, MotionEvent event) {
	 moveX= (int) event.getX();
		 moveY= (int) event.getY();
			// TODO Auto-generated method stub
			switch(event.getAction()){
		
			case MotionEvent.ACTION_UP :
				
			//movebar();
				
				
				break;
			case MotionEvent.ACTION_MOVE :
			if(moveY>780){
		movebar();
				
			}	//Log.i("MyApp","bar touch"+moveX);

				// Log.i("MyApp","move"+moveX);
				break;
			case MotionEvent.ACTION_DOWN :
				break;
			
			}
			return true;
	}

	public void movebar(){
if(moveX>0&&moveX<400){
		br.br.left=moveX;
		br.br.top=780;
		br.br.right=moveX+80;
		br.br.bottom=800;
}
		
	}
}
