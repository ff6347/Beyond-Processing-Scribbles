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
//	public ArrayList  <ViruSystem> virSystemList;
//	public ArrayList<Path> pathsList;

	public ViruSystem vs;
	public PVector dir;
	public DrawPixels pixels;


	public ArrayList<ArrayList<Virus>> virusListsList;

	public Virus vi;
	public Path path;
	
	public void setup(){
		smooth();
		size(500,500,P3D);
		background(255,5);


//		virSystemList = new ArrayList<ViruSystem>();
//		virusListsList = new ArrayList<ArrayList<Virus>>();
//		pathsList = new ArrayList<Path>();
		pixels = new DrawPixels(this);
		pixels.init();
		
		int pathRadius = width;
//		for (int i = 0; i< width; i = i+ width/4){
		path = new Path(this, pathRadius);
		ArrayList<Virus> virs = new ArrayList<Virus>();
			 for(int j = 0; j < height+100;j = j +height / 5){
				path.addPoint(width/2 , j);
			 }
			 
		vs = new ViruSystem(this, 1, path.getPoints().get(0), virs);
//			virusListsList.add(virList);
//			virSystemList.add(newVS);
//			pathsList.add(path);

//		}
		
		vi =  virs.get(0);
//		path = pathsList.get(0);


	}
	public void draw(){
		Style.setPAppletStyle(this);
		Style.create();
		drawInfo();
//		fill(255,5);
//		rect(0,0,width,height);
		

//		for (int p = 0; p < pathsList.size(); p++) {
////			pathsList.get(p).simplePathDisplay();
//			
//			vi = virusListsList.get(p).get(0);
//			vi.applyForces(virusListsList.get(p), pathsList.get(p));
////			for (int v = 0; v < virSystemList.get(p).getVirs().size(); v++) {
////				virSystemList.get(p).getVirs().get(v).applyForces(virSystemList.get(p).getVirs(), pathsList.get(p));
////		}
////			virSystemList.get(p).addVirusEmitter(true);
//			virSystemList.get(p).run();
//			virusListsList.get(p).get(0).setLifeTime(1000f);
//
//		}
	
		vs.run();
//		vs.addVirusEmitter(true);
		vi.applyForces(path);
		vi.setStrokeColor01(color(255,255,0));
		vi.setRadius(10);
//		if(frameCount%5 ==1){
			pixels.calcVirusPos(vi.getLoc());
//			}
		pixels.draw();
		image(pixels.thePixels(),0,0);



//		for (int d = 0;d <virusListsList.size();d++){
//
//}
		drawInfo();
//		saveFrame("../data/vs-###.tif");
	}
	
	public void keyReleased() {
		if (key == 'e') {
			exit();
		}
	}

	
	public void drawInfo() {
//		int virCount = 
//		for(int i = 0; i < virusListsList.size();i++){
//			virCount = virCount + (virusListsList.get(i).size());
//			
//		}
		textFont(Style.GENTIUMBASIC15);
		noStroke();
		fill(255);
		rect(45,35,270,50);
		fill(0);
		text("Framerate: " + frameRate, 50, 50);
//		text("there are " + nf(virs.size(),5) + "Viruses in this frame", 50, 70);

		noFill();
	}
	public void mousePressed(){
	
	}
}
