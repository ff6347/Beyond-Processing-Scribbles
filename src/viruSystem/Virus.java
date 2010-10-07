/**
 * @author fabiantheblind
 *
 */
package viruSystem;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;


public class Virus {

	
	private PApplet p;

	private PVector loc;
	private PVector vel;
	private PVector acc;
	
	private float gravity = 10.1f;
	private float maxforce = 0.05f;
	private float maxspeed = 5.0f;
	private float radius = 1f; // radius
	private float lifeTime; // the lifetime of an virus
//	The higher the mass of an Virus the
//	lesser the Virus get pushed by cell
	private float mass = 0f; //
	private PVector origin;
	private int strokeWeight;
	private int strokeColor01;
	private int strokeColor02;

	public Virus(PApplet p, PVector loc) {
		this.p = p;
		this.acc = new PVector(0, 0);
		this.vel = new PVector(0, 0);
		this.loc = loc.get();
		this.origin = new PVector(loc.x, loc.y);
		this.lifeTime = p.random(23f,200f);
		this.strokeWeight = 2;
		this.strokeColor01 =  p.color(255,0,0);
		this.strokeColor02 =  p.color(255,255,0);

	}
	
	public PVector getLoc() {
		return loc;
	}

	public void setLoc(PVector loc) {
		this.loc = loc;
	}

	public PVector getVel() {
		return vel;
	}

	public void setVel(PVector vel) {
		this.vel = vel;
	}

	public PVector getAcc() {
		return acc;
	}

	public void setAcc(PVector acc) {
		this.acc = acc;
	}

	public float getMaxforce() {
		return maxforce;
	}

	public void setMaxforce(float maxforce) {
		this.maxforce = maxforce;
	}

	public float getMaxspeed() {
		return maxspeed;
	}

	public void setMaxspeed(float maxspeed) {
		this.maxspeed = maxspeed;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public float getLifeTime() {
		return lifeTime;
	}

	public void setLifeTime(float lifeTime) {
		this.lifeTime = lifeTime;
	}

	public float getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	public int getStrokeWeight() {
		return strokeWeight;
	}

	public void setStrokeWeight(int strokeWeight) {
		this.strokeWeight = strokeWeight;
	}

	public int getStrokeColor01() {
		return strokeColor01;
	}

	public void setStrokeColor01(int strokeColor01) {
		this.strokeColor01 = strokeColor01;
	}

	public int getStrokeColor02() {
		return strokeColor02;
	}

	public void setStrokeColor02(int strokeColor02) {
		this.strokeColor02 = strokeColor02;
	}

	public void run() {
		update();
		getALife();
		display();
//		limit();

	}
	public void update() {
		// Update velocity
		this.vel.add(acc);
		// Limit speed
		this.vel.limit(maxspeed);
		this.loc.add(vel);
		// Reset accelertion to 0 each cycle
		this.acc.mult(0);
		// this.lifeTime -= 0.5;

	}
	
	private void getALife() {
		this.lifeTime -= 0.5;
	}
	public void display() {
		if(this.lifeTime > 100){
			p.stroke(this.strokeColor02);

		}else{
			p.stroke(this.strokeColor01);

		}
//			p.stroke(255,0,0,200);
			p.strokeWeight(this.strokeWeight);
			p.point(loc.x,loc.y);
			p.noStroke();

	}
	void limit() {
		if (loc.y > p.height - radius || loc.y < radius) {

			vel.y = -vel.y;
			loc.y = p.constrain(loc.y, -p.height * p.height, p.height- radius);

		}
		if ((loc.x < radius) || (loc.x > p.width - radius)) {
			vel.x = -vel.x;
			loc.x = p.constrain(loc.x, radius, p.width - radius);

		}

	}
	
	boolean dead() {
		if (this.lifeTime <= 0.0) {
			return true;
		} else {
			return false;
		}
	}

	public void applyForces(ArrayList<Virus> virs, Path path) {

		// Follow path force
		PVector f = follow(path);
		// Separate from other boids force
		PVector s = separate(virs);
		// Arbitrary weighting
		f.mult(3);
		s.mult(1);
		// Accumulate in acceleration
		this.acc.add(f);
		this.acc.add(s);
	}
	public void applyForces( Path path) {

		// Follow path force
		PVector f = follow(path);
		// Arbitrary weighting
		f.mult(3);
		// Accumulate in acceleration
		this.acc.add(f);
	}
	
	public void applyRepellForce(PVector force) {

//		 float mass = 0.1f; // We aren't bothering with mass here
		force.div(this.mass);
		this.acc.add(force);

	}
	
	public PVector follow(Path pt) {

		// Predict location 25 (arbitrary choice) frames ahead
		PVector predict = vel.get();
		predict.normalize();
		predict.mult(25);
		PVector predictLoc = PVector.add(loc, predict);

		// // Draw the predicted location
		// if (debug) {
		// p.fill(0);
		// p.stroke(0);
		// p.line(loc.x,loc.y,predictLoc.x, predictLoc.y);
		// p.ellipse(predictLoc.x, predictLoc.y,4,4);
		// }

		// Now we must find the normal to the path from the predicted location
		// We look at the normal for each line segment and pick out the closest
		// one
		PVector target = null;
		PVector dir = null;
		float record = 1000000; // Start with a very high record distance that
								// can easily be beaten

		// Loop through all points of the path
	
		for (int i = 0; i < pt.getPoints().size(); i++) {

			
			// Look at a line segment
			PVector a = pt.getPoints().get(i);
			PVector b = pt.getPoints().get((i + 1)
					% pt.getPoints().size()); // Path wraps around

			// Get the normal point to that line
			PVector normal = getNormalPoint(predictLoc, a, b);

			// Check if normal is on line segment
			float da = PVector.dist(normal, a);
			float db = PVector.dist(normal, b);
			PVector line = PVector.sub(b, a);
			// If it's not within the line segment, consider the normal to just
			// be the end of the line segment (point b)
			if (da + db > line.mag() + 1) {
				normal = b.get();
				// If we're at the end we really want the next line segment for
				// looking ahead
				a = pt.getPoints().get((i + 1) % pt.getPoints().size());
				b = pt.getPoints().get((i + 2) % pt.getPoints().size()); // Path
																						// wraps
																						// around
				line = PVector.sub(b, a);
			}

			// How far away are we from the path?
			float d = PVector.dist(predictLoc, normal);
			// Did we beat the record and find the closest line segment?
			if (d < record) {
				record = d;
				// If so the target we want to steer towards is the normal
				target = normal;

				// Look at the direction of the line segment so we can seek a
				// little bit ahead of the normal
				dir = line;
				dir.normalize();
				// This is an oversimplification
				// Should be based on distance to path & velocity
				dir.mult(25);
			}
		}

		// // Draw the debugging stuff
		// if (debug) {
		// // Draw normal location
		// p.fill(0);
		// p.noStroke();
		// p.line(predictLoc.x,predictLoc.y,target.x,target.y);
		// p.ellipse(target.x,target.y,4,4);
		// p.stroke(0);
		// // Draw actual target (red if steering towards it)
		// p.line(predictLoc.x,predictLoc.y,target.x,target.y);
		// if (record > pt.radius) p.fill(255,0,0);
		// p.noStroke();
		// p.ellipse(target.x+dir.x, target.y+dir.y, 8, 8);
		// }

		// Only if the distance is greater than the path's radius do we bother
		// to steer
		if (record > pt.getRadius() || vel.mag() < 0.1) {
			target.add(dir);
			return steer(target, false);
		} else {
			return new PVector(0, 0);
		}
	}
	
	public PVector separate(ArrayList<Virus> virs) {

		float desiredseparation = radius * 2;
		PVector steer = new PVector(0, 0);
		int count = 0;

		Virus vi = null;
		Virus other = null;
		// For every boid in the system, check if it's too close
		for (int i = 0; i < virs.size(); i++) {
			vi = virs.get(i);
			
				other = virs.get(i);
				float d = PVector.dist(loc, other.loc);
				// If the distance is greater than 0 and less than an arbitrary
				// amount (0 when you are yourself)
				if ((d > 0) && (d < desiredseparation)) {
					// Calculate vector pointing away from neighbor
					PVector diff = PVector.sub(loc, other.loc);
					diff.normalize();
					diff.div(d); // Weight by distance
					steer.add(diff);
					count++;
				}// Keep track of how many
			
		}
		// Average -- divide by how many
		if (count > 0) {
			steer.div(count);
		}

		// As long as the vector is greater than 0
		if (steer.mag() > 0) {
			// Implement Reynolds: Steering = Desired - Velocity
			steer.normalize();
			steer.mult(maxspeed);
			steer.sub(vel);
			steer.limit(maxforce);
		}

		return steer;
	}
	
	public PVector getNormalPoint(PVector p, PVector a, PVector b) {
		// Vector from a to p
		PVector ap = PVector.sub(p, a);
		// Vector from a to b
		PVector ab = PVector.sub(b, a);
		ab.normalize(); // Normalize the line
		// Project vector "diff" onto line by using the dot product
		ab.mult(ap.dot(ab));
		PVector normalPoint = PVector.add(a, ab);
		return normalPoint;
	}
	public PVector steer(PVector target, boolean slowdown) {
		PVector steer; // The steering vector
		PVector desired = PVector.sub(target, loc); // A vector pointing from
													// the location to the
													// target
		float d = desired.mag(); // Distance from the target is the magnitude of
									// the vector
		// If the distance is greater than 0, calc steering (otherwise return
		// zero vector)
		if (d > 0) {
			// Normalize desired
			desired.normalize();
			// Two options for desired vector magnitude (1 -- based on distance,
			// 2 -- maxspeed)
			if ((slowdown) && (d < 100.0f))
				desired.mult(maxspeed * (d / 100.0f)); // This damping is
														// somewhat arbitrary
			else
				desired.mult(maxspeed);
			// Steering = Desired minus Velocity
			steer = PVector.sub(desired, vel);
			steer.limit(maxforce); // Limit to maximum steering force
		} else {
			steer = new PVector(0, 0);
		}
		return steer;
	}

}
