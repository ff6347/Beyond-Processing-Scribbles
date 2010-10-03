/**
 * 
 */
package beyondpscribbles01;

import ddf.minim.*;
import processing.core.PApplet;
import processing.core.PVector;
import util.Debug;
import util.Particle;
import util.Style;

/**
 * @author fabianmoronzirfas
 *
 */
public class Scribbles02 extends PApplet{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	Minim minim;
	AudioInput in;

	
		private PVector vec01;


		private PVector vec02;


		private PVector vec03;


		private PVector vec04;


		private PVector vec05;


		private PVector vec06;


		private PVector vec07;


		private PVector vec08;
		
		float centerY ;
		float centerZ;
		float centerX;
		float eyeZ;
		float eyeY;
		float eyeX;
		float upX;
		float upY;
		float upZ;
		int depth;
		int distance;
		float boxSize;
		int turn;
		int incr;


		private Particle ptclLeftUp;
		private Particle ptclRightUp;


		private Particle ptclRightDown;


		private Particle ptclLeftDown;

	public void setup(){
		size(500,500,P3D);
		minim = new Minim(this);
		minim.debugOn();
		// get a line in from Minim, default bit depth is 16
		in = minim.getLineIn(Minim.STEREO, 512);

		Debug.setPAppletDebug(this);
		Style.setPAppletStyle(this);
		
//		front Vector clockwise
		vec01 = new PVector(0,0,0);
		vec02 = new PVector(width,0,0);
		vec03 = new PVector(width,height,0);
		vec04 = new PVector(0,height,0);
		
//		bck Vector clockwise
		vec05 = new PVector(0,0,-500);
		vec06 = new PVector(width,0,-500);
		vec07 = new PVector(width,height,-500);
		vec08 = new PVector(0,height,-500);
		
		ptclLeftUp = new Particle(this, vec05, 10, 2.5f, 2.5f, true, false);
		ptclRightUp = new Particle(this, vec06, 10, 2.5f, 2.5f, true, false);
		ptclRightDown = new Particle(this, vec07, 10, 2.5f, 2.5f, true, false);
		ptclLeftDown = new Particle(this, vec08, 10, 2.5f, 2.5f, true, false);



		 eyeX = width/2;
		 eyeY = height/2;
		 eyeZ =  0;//(height/2.0f) / tan(PI*60.0f / 360.0f);
		 
		 centerX = width/2;
		 centerY = height/2;
		 centerZ =500;

		 upX = 0;
		 upY = 1;
		 upZ = 0;
		 depth = 3000;
		 distance = 50;
		 boxSize = 10f;
		 Debug.setPAppletDebug(this);
		 Style.setPAppletStyle(this);
		 Style.create();
	}

	
	public void draw(){
		turn = incr%360;
		cls();

//		for(int i = 0; i < in.bufferSize() - 1; i++)
//		  {
//		    line(i, 50 + in.left.get(i)*50, i+1, 50 + in.left.get(i+1)*50);
//		    line(i, 150 + in.right.get(i)*50, i+1, 150 + in.right.get(i+1)*50);
//		  }

		
		
		stroke(255);
		strokeWeight(2);
		camera();
//		camera(eyeX , eyeY, eyeZ, centerX/*+ sin(radians(turn))*180*/, centerY/*+cos(radians(turn))*180*/, centerZ, upX, upY, upZ );
//		vec05.x = ptclLeftUp.loc.x + in.mix.level() *100;
//		vec05.y = ptclLeftUp.loc.y + in.mix.level()*100;
//		vec05.z = ptclLeftUp.loc.z + in.mix.level()*100;
		
		ptclLeftUp.loc.x = ptclLeftUp.loc.x + in.mix.level() *1000*(random(-1,1));
		ptclLeftUp.loc.y = ptclLeftUp.loc.y + in.mix.level()*1000*(random(-1,1));
		ptclLeftUp.loc.z = ptclLeftUp.loc.z + in.mix.level()*1000*(random(-1,1));
		
		ptclLeftUp.run();
		ptclLeftUp.seek(ptclLeftUp.getOrigin());
		
		ptclRightUp.loc.x = ptclRightUp.loc.x + in.mix.level() *1000*(random(-1,1));
		ptclRightUp.loc.y = ptclRightUp.loc.y + in.mix.level()*1000*(random(-1,1));
		ptclRightUp.loc.z = ptclRightUp.loc.z + in.mix.level()*1000*(random(-1,1));
		
		ptclRightUp.run();
		ptclRightUp.seek(ptclRightUp.getOrigin());
		
		ptclRightDown.loc.x = ptclRightDown.loc.x + in.mix.level() *1000*(random(-1,1));
		ptclRightDown.loc.y = ptclRightDown.loc.y + in.mix.level()*1000*(random(-1,1));
		ptclRightDown.loc.z = ptclRightDown.loc.z + in.mix.level()*1000*(random(-1,1));
		
		ptclRightDown.run();
		ptclRightDown.seek(ptclRightDown.getOrigin());
		
		ptclLeftDown.loc.x = ptclLeftDown.loc.x + in.mix.level() *1000*(random(-1,1));
		ptclLeftDown.loc.y = ptclLeftDown.loc.y + in.mix.level()*1000*(random(-1,1));
		ptclLeftDown.loc.z = ptclLeftDown.loc.z + in.mix.level()*1000*(random(-1,1));
		
		ptclLeftDown.run();
		ptclLeftDown.seek(ptclLeftDown.getOrigin());
		

//		make the first lines
		stroke(255,0,0);
		line(vec01.x,vec01.y,vec01.z,ptclLeftUp.loc.x,ptclLeftUp.loc.y,ptclLeftUp.loc.z);
		stroke(0,255,0);
		line(vec02.x,vec02.y,vec02.z,ptclRightUp.loc.x,ptclRightUp.loc.y,ptclRightUp.loc.z);
		stroke(0,0,255);
		line(vec03.x,vec03.y,vec03.z,ptclRightDown.loc.x,ptclRightDown.loc.y,ptclRightDown.loc.z);
		stroke(126);
		line(vec04.x,vec04.y,vec04.z,ptclLeftDown.loc.x,ptclLeftDown.loc.y,ptclLeftDown.loc.z);
		

		stroke(255,0,0);

		line(ptclLeftUp.loc.x,ptclLeftUp.loc.y,ptclLeftUp.loc.z,ptclRightUp.loc.x,ptclRightUp.loc.y,ptclRightUp.loc.z);
		stroke(0,255,0);

		line(ptclRightUp.loc.x,ptclRightUp.loc.y,ptclRightUp.loc.z,ptclRightDown.loc.x,ptclRightDown.loc.y,ptclRightDown.loc.z);
		stroke(0,0,255);

		line(ptclRightDown.loc.x,ptclRightDown.loc.y,ptclRightDown.loc.z,ptclLeftDown.loc.x,ptclLeftDown.loc.y,ptclLeftDown.loc.z);
		stroke(126);

		line(ptclLeftDown.loc.x,ptclLeftDown.loc.y,ptclLeftDown.loc.z,ptclLeftUp.loc.x,ptclLeftUp.loc.y,ptclLeftUp.loc.z);


		incr++;
		//saveFrame("./data/backFacesOnAudio-####.tif");

	}
	
	
	
	void cls(){
		background(Style.bgBlur);

		
	}
	
	public void keyReleased() {
		if (key == 'e') {
			exit();
		}
		
		if (key == 's' || key == 'S') {

			// just for unique filenames when saving a frame as .jpg in the
			// folder data
			float time;
			time = millis();
			Debug.saveFrame(time);
			println("wrote \"MyImg" + time + ".jpg\" to the folder bilder");

		}
		
		
		if (key == 'i' || key == 'I') {
			Debug.writeImg = true;
		}
		if (key == 'o' || key == 'O') {
			Debug.writeImg = false;
		}
	}
	
	public void stop()
	{
	  // always close Minim audio classes when you are done with them
	  in.close();
	  minim.stop();
	 
	  super.stop();
	}


}
