/**
 * 
 */
package viruSystem;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import util.Style;

/**
 * @author fabianmoronzirfas
 *
 */
public class Path {
	
	private PApplet p;
	private ArrayList<PVector> points;
	private float radius;

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public Path(PApplet p, float radius) {
		this.p = p;
		this.radius = radius;
		points = new ArrayList<PVector>();

	}
	
	 public void addPoint(float x, float y) {
	 PVector point = new PVector(x,y);
	 points.add(point);
	 }
	 
		public ArrayList<PVector> getPointList() {
			return this.points;
		}

		public synchronized ArrayList<PVector> getPoints() {
			return points;
		}
		
		public void simplePathDisplay() {

			// Draw the radius as thick lines and circles

			// Draw end points
			for (int i = 0; i < points.size(); i++) {
				PVector point = points.get(i).get();
			
				
				p.fill(0);
				p.noStroke();
				p.ellipse(point.x, point.y, 10, 10);
			}

			// Draw Polygon around {@code Path}
			
//			PVector start = null;
//			PVector end = null;
//			PVector line = null;
//			PVector normal = null;
//			PVector a = null;
//			PVector b = null;
//			PVector c = null;
//			PVector d = null;
//			for (int i = 0; i < ptclPoints.size(); i++) {
//				start = ptclPoints.get(i).getLoc();
//				// We're assuming {@code Path} is a circle in this example
//				end = ptclPoints.get((i + 1) % ptclPoints.size()).getLoc();
//				line = PVector.sub(end, start);
//				normal = new PVector(line.y, -line.x);
//				normal.normalize();
//				normal.mult(this.radius);
	//
//				// Polygon has four vertices
//				a = PVector.add(start, normal);
//				b = PVector.add(end, normal);
//				c = PVector.sub(end, normal);
//				d = PVector.sub(start, normal);
	//
//				p.fill(Style.superSoftWhite);
//				p.noStroke();
//				p.beginShape();
//				p.vertex(a.x, a.y);
//				p.vertex(b.x, b.y);
//				p.vertex(c.x, c.y);
//				p.vertex(d.x, d.y);
//				p.endShape();
//			}

			// Draw Regular Line
			p.stroke(Style.superSoftWhite);
//			p.strokeWeight(this.getRadius()*2);
			p.noFill();
			p.beginShape();
			PVector loc = null;
			for (int i = 0; i < points.size(); i++) {
				loc = points.get(i).get();
				p.vertex(loc.x, loc.y);
			}
			p.endShape(PConstants.CLOSE);

		}
}
