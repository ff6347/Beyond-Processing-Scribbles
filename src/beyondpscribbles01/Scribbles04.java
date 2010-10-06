/**
 * @author fabiantheblind
 *
 */
package beyondpscribbles01;

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
	public ArrayList  <ViruSystem> virSystemList;
	public ArrayList<Path> pathsList;

	public ViruSystem vs;
	public PVector dir;


	public ArrayList<ArrayList<Virus>> virusListsList;


	
	public void setup(){
		smooth();
		size(800,800,P3D);
		background(255,5);


		virSystemList = new ArrayList<ViruSystem>();
		virusListsList = new ArrayList<ArrayList<Virus>>();
		pathsList = new ArrayList<Path>();
		
		int pathRadius = 50;
		Path path = null;
		for (int i = 0; i< width; i = i+ 50){
			path = new Path(this, pathRadius);
			ArrayList<Virus> virList = new ArrayList<Virus>();
			 for(int j = 0; j < height+100;j = j +height / 5){
				path.addPoint(i , j);
			 }
			 
			ViruSystem newVS = new ViruSystem(this, 10, path.getPoints().get(0), virList);
			virusListsList.add(virList);
			virSystemList.add(newVS);
			pathsList.add(path);
			 
		}



	}
	public void draw(){
//		background(255,5);
		Style.setPAppletStyle(this);
		Style.create();
		drawInfo();
//		fill(255,5);
//		rect(0,0,width,height);
		for (int p = 0; p < pathsList.size(); p++) {
//			pathsList.get(p).simplePathDisplay();

			for (int v = 0; v < virSystemList.get(p).getVirs().size(); v++) {
				virSystemList.get(p).getVirs().get(v).applyForces(virSystemList.get(p).getVirs(), pathsList.get(p));
		}
			virSystemList.get(p).addVirusEmitter(true);
			virSystemList.get(p).run();
		}
//		saveFrame("../data/vs-###.tif");
	}
	
	public void keyReleased() {
		if (key == 'e') {
			exit();
		}
	}

	
	public void drawInfo() {
		int virCount = 0;
		for(int i = 0; i < virusListsList.size();i++){
			virCount = virCount + (virusListsList.get(i).size() +1);
			
		}
		textFont(Style.GENTIUMBASIC15);
		noStroke();
		fill(255);
		rect(45,35,270,50);
		fill(0);
		text("Framerate: " + frameRate, 50, 50);
		text("there are " + nf(virCount,5) + "Viruses in this frame", 50, 70);

		noFill();
	}
	public void mousePressed(){
	
	}
}
