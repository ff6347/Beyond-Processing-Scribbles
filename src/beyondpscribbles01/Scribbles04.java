/**
 * @author fabiantheblind
 *
 */
package beyondpscribbles01;

import image.DrawPixels;

import java.util.ArrayList;

import util.Style;
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


	public ViruSystem vs;
	public PVector dir;
	public DrawPixels pixels;


	public Virus vi;
	public Path path;
	
	public void setup(){
		smooth();
		size(500,500,P3D);
		background(255,5);



		pixels = new DrawPixels(this);
		pixels.init();
		int pathRadius = width;
		path = new Path(this, pathRadius);
		virs = new ArrayList<Virus>();
			 for(int j = 0; j < height+100;j = j +height / 5){
				path.addPoint(width/2 , j);
			 }
			 
		vs = new ViruSystem(this, 1, path.getPoints().get(0), virs);
		vi =  virs.get(0);
	}
	public void draw(){
		Style.setPAppletStyle(this);
		Style.create();
		drawInfo();
//		vs.addVirusEmitter(true);
		vi.applyForces(path);
		vi.setStrokeColor01(color(255,255,0));
		vi.setLifeTime(100);
		if(frameCount%5 ==1){
			pixels.calcVirusPos(vi.getLoc());
			}
		vs.run();
		pixels.draw();
		image(pixels.thePixels(),0,0);
		drawInfo();
//		saveFrame("../data/vs-###.tif");
	}
	
	public void keyReleased() {
		if (key == 'e') {
			exit();
		}
	}

	
	public void drawInfo() {

		textFont(Style.GENTIUMBASIC15);
		noStroke();
		fill(255);
		rect(45,35,270,50);
		fill(0);
		text("Framerate: " + frameRate, 50, 50);
		text("there are " + nf(virs.size(),5) + "Viruses in this frame", 50, 70);

		noFill();
	}
	public void mousePressed(){
	
	}
}
