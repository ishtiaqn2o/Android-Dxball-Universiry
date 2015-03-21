package my.dx.ball;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

public class DxtestActivity extends Activity{
	viewsurface v;
	public  float x,y;
	public int count = 0;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        v = new viewsurface(this);
     //   v.setOnTouchListener(this);
      
        setContentView(v);
    }
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	
    	super.onPause();
    	v.pause();
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	
    	super.onResume();
    	v.resume();
    }
    class viewsurface extends SurfaceView implements Runnable
    {
    	boolean IsRunning = false;
    	SurfaceHolder holder = null;
    	Thread thread;
    	stage s;

    	Context context;
		public viewsurface(Context context) {
			super(context);
			holder = getHolder();
			this.context=context;
			
			// TODO Auto-generated constructor stub
		}
		public void resume()
		{
			IsRunning = true;
		    thread= new Thread(this);
			thread.start();
		}
    	public void pause()
    	{
    		
    		IsRunning = false;
    		while(true)
    		{
    			try {
					thread.join();
					return;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
		public void run() {
			// TODO Auto-generated method stub
		 s = new stage(this.context);
		 //Sending surface view to constructor
		 DrawObject dr = new DrawObject(v,thread);
		 try {
				s.setLevel(1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
			 Log.i("MyApp","count"+count++);
		
			while(IsRunning)
			{
				
				if(!holder.getSurface().isValid())
				{
					continue;
				}
				
				Canvas canvas = holder.lockCanvas();
				Paint paint = new Paint();
				
				canvas.drawARGB(255, 255, 255, 255);
				//s.ondraw(canvas,s);
				dr.Ondraw(canvas, s);
				// Log.i("MyApp","count"+count++);
				holder.unlockCanvasAndPost(canvas);
			}
		}
		
    }

}