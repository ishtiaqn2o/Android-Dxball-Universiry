package my.dx.ball;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.R.integer;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.widget.Toast;

public class stage {
	
	public int Level ;
	public int ballSpeed;
	public int radious;
	public int BallX;
	public int BallY;
	public	int NumOfBrick;
	public Rect BarPosition;
public DrawObject dr;
	
public ArrayList<drawable> AllObject;
public ArrayList<brick> allBrick;
	public Context context;
	
	
	public stage(Context context){
		
		this.context=context;
	AllObject = new ArrayList<drawable>();
		
		}
	public void setLevel(int level) throws IOException{
		this.Level=level;
		switch (Level) {
		case 1:
			LoadResource("out.txt");
			break;
		case 2:
			Log.i("MyApp","STAGE 2");
			LoadResource("out.txt");
			break;	

		default:
			break;
		}
	}
	
	
	public void LoadResource(String FileName) throws IOException{
		//final StringBuffer storedString = new StringBuffer();
	    String str1,str2,str3,str4,str5 = null;
		AssetManager am = context.getAssets();
		InputStream is = am.open(FileName);
		try {
		  //  fis = openFileInput("desk");
		    DataInputStream dataIO = new DataInputStream(is);
		   
		    
		    str1 = dataIO.readLine();
		    ballSpeed =Integer.parseInt(str1);
		    str2 = dataIO.readLine();
		    
		    String[] items = str2.split(",");
		   this.BallX=Integer.parseInt(items[0]);
		   this.BallY=Integer.parseInt(items[1]);
		   this.radious=Integer.parseInt(items[2]);
		   
		   
		   ball bl=new ball(BallX, BallY,radious);
		   AllObject.add(bl);
		   
		   str3 = dataIO.readLine();
		   
		   String[] bar = str3.split(",");
		   BarPosition = new Rect();
		 
		   BarPosition.left=Integer.parseInt(bar[0]);
		  BarPosition.top=Integer.parseInt(bar[1]);
		  BarPosition.right=Integer.parseInt(bar[2]);
		  BarPosition.bottom=Integer.parseInt(bar[3]);
		 bar ba=new bar(BarPosition);
		 AllObject.add(ba);
		  str4 = dataIO.readLine();
		  NumOfBrick = Integer.parseInt(str4);
		allBrick = new ArrayList<brick>();
		  for(int i=0;i<NumOfBrick;i++){
			  str5 = dataIO.readLine();
			  String[] bri = str5.split(",");
			  Rect brickk = new Rect();
			 
			   brickk.left=Integer.parseInt(bri[0]);
			  brickk.top=Integer.parseInt(bri[1]);
			  brickk.right=Integer.parseInt(bri[2]);
			  brickk.bottom=Integer.parseInt(bri[3]);
			  
			  brick brk = new brick(brickk);
			  AllObject.add(brk);
			  allBrick.add(brk);
			  
			  //Log.i("MyApp","width:"+brickPos.get(i).left);
		  }
		
		  
		    

		   
		  //  Log.i("MyApp","width:"+radious);
		    dataIO.close();
		    is.close();
		}
		catch  (Exception e) {  
		}
		
		
	}

	public void ondraw(Canvas canvas,stage s) {
		// TODO Auto-generated method stub
		//dr.Ondraw(canvas,s);
	}

}
