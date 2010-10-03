package beyondpscribbles01;

import processing.core.PApplet;
import util.Debug;
import util.Style;


public class Scribbles01 extends PApplet {

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

	public void setup() {
		size(500,500,P3D);

		 eyeX = width/2;
		 eyeY = height/2;
		 eyeZ =  (height/2.0f) / tan(PI*60.0f / 360.0f);
		 
		 centerX = width/2;
		 centerY = height/2;
		 centerZ =0;

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

	public void draw() {
		
		turn = incr%360;
		cls();

		camera(eyeX + sin(radians(turn))*180, eyeY+cos(radians(turn))*180, eyeZ, centerX, centerY, centerZ, upX, upY, upZ );
		frustum(-50, 50, -50, 50, 50, -50);
		for(int x = 0;x < width +distance; x = x + distance){
			
			for (int y = 0; y < height+ distance; y = y +distance) {
				
				for (int z = ((depth/2) *(-1)); z < depth; z = z +distance) {
					pushMatrix();
					translate(x, y, z);
					fill(Style.boxCol);
					strokeWeight(1);
					stroke(Style.bgBlur);
					box(boxSize);
					popMatrix();
				}
				
			}
	
		}
	incr++;
//	saveFrame("box-####.tif");
	}
	
	void cls(){
		background(Style.bgBlur);

		
	}
	
	public void keyPressed() {
		if (key == 'e') {
			exit();
		}
		
		if (key == 'i' || key == 'I') {
			Debug.writeImg = true;
		}
		if (key == 'o' || key == 'O') {
			Debug.writeImg = false;
		}
	}
}


