/**
 * 
 */
package image;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/**
 * @author fabianmoronzirfas
 *
 */
public class DrawPixels {
	
	
	public DrawPixels(PApplet p){
		this.p = p;

		
	}
	
	 
	
	private  PApplet p;
	

	public PImage thePixels;
	public PImage theOldPixels;
	private float noiseVal;
	public void init(){
		
		PImage Pixels = p.createImage(500,500,p.RGB);
		PImage OldPixels = p.createImage(500,500,p.RGB);
		this.noiseVal = 0.145f;	
		this.thePixels = Pixels;
		this.theOldPixels = OldPixels;
		
		
	}

	public void calcVirusPos(PVector loc){
		
		int viX = PApplet.floor(loc.x);
		int viY = PApplet.floor(loc.y);

		this.thePixels.set(viX,viY,p.color(255,255,255));
	}
	
	public void draw(){
//		p.background(0);
//		p.image(this.thePixels,0,0);
		this.theOldPixels =this.thePixels;

		for (int x=0; x<this.thePixels.width; x++){
	        for (int y=0; y<this.thePixels.height; y++){
	      if (p.red(this.thePixels.get(x,y))>50){
	        // Nachbarschaft erkunden 
	        int nachbarn = 0;
	        if (p.red(this.thePixels.get(x-1,y+1))>50) nachbarn++;
	        if (p.red(this.thePixels.get(x,y+1))>50) nachbarn++;
	        if (p.red(this.thePixels.get(x+1,y+1))>50) nachbarn++;
	        if (p.red(this.thePixels.get(x-1,y))>50) nachbarn++;
	        if (p.red(this.thePixels.get(x+1,y))>50) nachbarn++;
	        if (p.red(this.thePixels.get(x-1,y-1))>50) nachbarn++;
	        if (p.red(this.thePixels.get(x,y-1))>50) nachbarn++;
	        if (p.red(this.thePixels.get(x+1,y-1))>50) nachbarn++;
	        if (nachbarn>4) {
//	          println(nachbarn);
	          this.thePixels.set(x,y,p.color(0,0,0));
	        } else {
	        	
	        
        switch (PApplet.floor(p.random(1,10))) {
        case 1: if (p.red(this.thePixels.get(x-1,y+1))<50) this.thePixels.set(x-1,y+1,p.color(255,0,0,200)); break;
        case 2: if (p.red(this.thePixels.get(x,y+1))<50) this.thePixels.set(x,y+1,p.color(255,0,0,200)); break;
        case 3: if (p.red(this.thePixels.get(x+1,y+1))<50) this.thePixels.set(x+1,y+1,p.color(255,0,0,200)); break;
        case 4: if (p.red(this.thePixels.get(x-1,y))<50) this.thePixels.set(x-1,y,p.color(255,0,0,200)); break;
        case 5: if (p.red(this.thePixels.get(x,y))<50) this.thePixels.set(x,y,p.color(255,0,0,200)); break;
        case 6: if (p.red(this.thePixels.get(x+1,y))<50) this.thePixels.set(x+1,y,p.color(255,0,0,200)); break;
        case 7: if (p.red(this.thePixels.get(x-1,y-1))<50) this.thePixels.set(x-1,y-1,p.color(255,0,0,200)); break;
        case 8: if (p.red(this.thePixels.get(x,y-1))<50) this.thePixels.set(x,y-1,p.color(255,0,0,200)); break;
        case 9: if (p.red(this.thePixels.get(x+1,y-1))<50) this.thePixels.set(x+1,y-1,p.color(255,0,0,200));      break;       

						}
					}

				}
			}
		}
	}
	
	public PImage thePixels(){
		return this.thePixels;
		
		
	}
}
