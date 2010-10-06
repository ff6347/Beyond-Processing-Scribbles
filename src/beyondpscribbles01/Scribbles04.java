/**
 * @author fabiantheblind
 *
 */
package beyondpscribbles01;

import java.util.ArrayList;
import ddf.minim.*;

import viruSystem.Path;
import processing.core.PApplet;
import processing.core.PVector;
import viruSystem.ViruSystem;
import viruSystem.Virus;


public class Scribbles04 extends PApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArrayList <Virus> virs;
	public ArrayList  <ViruSystem> vsList;
	public ViruSystem vs;
	public PVector dir;
	public Path path;
	
//	Minim minim;
//	AudioInput in;

	
	public void setup(){
		size(800,800,P3D);
		background(255,100);
//		minim = new Minim(this);
//		minim.debugOn();
		// get a line in from Minim, default bit depth is 16
//		in = minim.getLineIn(Minim.STEREO, 512);
		virs = new ArrayList<Virus>();
		vsList = new ArrayList<ViruSystem>();
//		vs = new ViruSystem(this, 10, new PVector(width/2,height/2,0), virs);
//		vsList.add(vs);
		
		 path = new Path(this, 100);
		for (int i = 0; i <= 360; i += 360 / 5) {

			path.addPoint((width / 2) + (sin(PApplet.radians(i)))* 100f, (height / 2) + (cos(PApplet.radians(i)))* 100f);
		}
	}
	public void draw(){
//		println(in.mix.level());

		fill(255,5);
		rect(0,0,width,height);
//		dir = new PVector(random(-1,1),random(-1,1),0);

//		path.simplePathDisplay();
		
		for (int i = 0; i < virs.size(); i++) {
			virs.get(i).applyForces(virs, path);
		}
		
		for(int j = 0;j<vsList.size();j++){
			vsList.get(j).addVirusEmitter(true);
			vsList.get(j).run();	
		}
//		vs.addVirusEmitter(true);
//		vs.run();
//		noise();
		saveFrame("../data/vs-###.tif");
	}
	
	public void keyReleased() {
		if (key == 'e') {
			stop();
			exit();
		}
	}
		
//	public void noise(){
//		if (in.mix.level()> 0.4f){
//			
//			ViruSystem newVS = new ViruSystem(this, 10, new PVector(random(width),random(height),0), virs);
//			vsList.add(newVS); 
//			println("added a vs");
//		}
		
//	}
	public void mousePressed(){
		
		ViruSystem newVS = new ViruSystem(this, 10, new PVector(mouseX,mouseY,0), virs);
		vsList.add(newVS); 
		
	}
//	public void stop()
//	{
//	  // always close Minim audio classes when you are done with them
//	  in.close();
//	  minim.stop();
//	 
//	  super.stop();
//	}
}
