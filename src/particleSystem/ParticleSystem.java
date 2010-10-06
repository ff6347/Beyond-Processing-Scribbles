package particleSystem;


import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * the whole <code>ParticleSystem</code><br>
 * here the collision and stuff get worked out<br>
 * based on: <a href="http://www.shiffman.net/teaching/nature/"
 * target="blanc">Daniel Shiffman's Nature of Code</a>
 * 
 * @author fabiantheblind
 * @see Particle Class Particle
 * @see Path Class Path
 * @see Repeller Class Repeller
 * 
 */
public class ParticleSystem {

	/**
	 * An origin point for where particles are birthed when using the emitter
	 * 
	 * @see #addParticleEmitter(boolean)
	 * @see #setEmitterOrigin(PVector)
	 */
	private PVector origin;

	/**
	 * the PApplet
	 */
	PApplet p;

	/**
	 * An <code>ArrayList</code> for all the <code>Particle</code>'s
	 */
	ArrayList<Particle> particles;

	/**
	 * this is the masterconstructor
	 * 
	 * @param p
	 *            the PApplet
	 * @param num
	 *            int create at startup a number of particles
	 * @param v
	 *            the origin of the emitter
	 * @param particles
	 *            an Arraylist of Particles
	 */
	public ParticleSystem(PApplet p, int num, PVector v,
			ArrayList<Particle> particles) {
		this.particles = particles; // Initialize the arraylist
		this.p = p;
		this.origin = v.get(); // Store the origin point
		for (int i = 0; i < num; i++) {
			particles.add(new Particle(p, origin, true, false)); // Add "num"
																	// amount of
																	// particles
																	// to the
																	// arraylist
		}
	}

	public ParticleSystem(PApplet p, PVector v, ArrayList<Particle> particles) {
		this.particles = particles; // Initialize the arraylist
		this.p = p;
		this.origin = v.get(); // Store the origin point
	}

	/**
	 * adds a particle to the ArrayList particles
	 * 
	 * @param ptcl
	 * @see #particles
	 */
	void addParticle(Particle ptcl) {
		particles.add(ptcl);
	}

	/**
	 * this is an basic Particle Emitter it is not worked out if u set the
	 * pointOrigin to true it will emit Particles from the center the window
	 * 
	 * @param pointOrigin
	 *            boolean
	 * @see Particle#Particle(PApplet, PVector, boolean, boolean)
	 */
	public void addParticleEmitter(boolean pointOrigin) {

		Particle ptcl;

		if (pointOrigin) {
//			ptcl = new Particle(p, origin, true, false);
			ptcl = new Particle( p, origin, new PVector(p.random(-1,1),p.random(-1,1)), 2f, 5f,	0.7f, true, false);
		} else {

			PVector myOrigin = new PVector(p.random(p.width),
					p.random(p.height));
			ptcl = new Particle( p, myOrigin, new PVector(p.random(p.width),
					p.random(p.height)), 2f, 5f,	0.7f, true, false);
//			ptcl = new Particle(p, myOrigin, true, false);
			ptcl.setMaxspeed(5f);
			ptcl.setRadius(p.random(2));

		}
		ptcl.setLifeTime(p.random(230, 420));
		ptcl.setMaxspeed(0.7f);
		ptcl.setRadius(p.random(2));
		particles.add(ptcl);
	}

	/**
	 * A function to apply a force to all Particles
	 * 
	 * 
	 * @param f
	 *            PVector
	 * @see Particle#applyRepellForce(PVector)
	 * @deprecated
	 */

	void applyForce(PVector f) {
		Particle ptcl = null;
		for (int i = 0; i < particles.size(); i++) {
			ptcl = particles.get(i);
			ptcl.applyRepellForce(f);
		}
	}

	/**
	 * This is the method where the collision takes place. <code>Particle</code>
	 * s interact with all Obstacles using
	 * <code>TNObstacleObject.isHit(float x, floaty)</code>
	 * 
	 * @param ObstclsList
	 *            List
	 * @param day
	 * @see #calcPtclReaction(Repeller, float, Particle, boolean, PVector)
	 * @see <a href"./doc/interaction/TNObstacleObject.html">
	 *      <code>TNObstacleObject</code></a>
	 */
//	public void applyObstcles(List<TNObstacleObject> ObstclsList, boolean day) {
//		// For every Particle
//		int x;
//		int y;
//		Particle ptcl = null;
//		TNObstacleObject obsObj = null;
//		Force force = null;
//		for (int i = 0; i < particles.size(); i++) {
//			ptcl = particles.get(i);
//			x = PApplet.floor(ptcl.getLoc().x);
//			y = PApplet.floor(ptcl.getLoc().y);
//
//			for (int j = 0; j < ObstclsList.size(); j++) {
//				if (ObstclsList.get(j).isActive) {
//					obsObj = ObstclsList.get(j);
//
//					if ((obsObj.isHit(x, y) == true)) {
//
//						force = new Force(p, obsObj.property.getIndex(), obsObj
//								.getProperty().getName(), obsObj.getProperty()
//								.getAffectionProps(), new PVector(x, y), 10);
//						calcPtclReactionOnForce(force, ptcl, day);
//					}
//				}
//			}
//		}
//	}

	/**
	 * @param ObstclsList
	 * @param day
	 * @deprecated
	 */
//	@Deprecated
//	public void applyObstRepeller(List<TNObstacleObject> ObstclsList,
//			boolean day) {
//		// For every Particle
//		for (int i = 0; i < particles.size(); i++) {
//			Particle ptcl = particles.get(i);
//
//			for (int j = 0; j < ObstclsList.size(); j++) {
//				if (ObstclsList.get(j).isActive) {
//					TNObstacleObject obsObj = ObstclsList.get(j);
//					// For every Repeller
//					for (int k = 0; k < obsObj.ObstclsRepellerList.size(); k++) {
//						Repeller r = ObstclsList.get(j).ObstclsRepellerList
//								.get(k);
//						// // Calculate and apply a force from Repeller to
//						// Particle
//						float d = ptcl.getLoc().dist(r.getLoc());
//						PVector repel = new PVector(0, 0);
//						calcPtclReaction(r, d, ptcl, day, repel);
//					}
//				}
//			}
//		}
//
//	}

	/**
	 * A function for particles to interact with all Repellers this function is
	 * not used in the main programm but still is usefull to have
	 * 
	 * @param repellers
	 *            ArrayList
	 *
	 */

	public void applyRepellers(ArrayList<Repeller> repellers) {
		// For every Particle
		Particle ptcl = null;
		Repeller r = null;
		PVector repel = null;
		for (int i = 0; i < particles.size(); i++) {
			ptcl = particles.get(i);
			// For every Repeller
			for (int j = 0; j < repellers.size(); j++) {
				r = repellers.get(j);
				// Calculate and apply a force from Repeller to Particle
//				if(
//						(
//						(ptcl.getLoc().x >= r.getLoc().x-r.getRadius())
//						&&
//						(ptcl.getLoc().x <= r.getLoc().x+r.getRadius())
//						)
//					&& 
//						(
//						(ptcl.getLoc().y >= r.getLoc().y -r.getRadius())
//						&&
//						(ptcl.getLoc().y <= r.getLoc().y +r.getRadius())
//						)
//				){
//					
//					r.setColor2(255, 0, 0, 100);
//					Particle newPtcl = new Particle(p, r.getLoc(), ptcl.getVel(),2,0.5f,0.2f, true, false);
//
////					particles.add(newPtcl);
//					
////					PSUtil.newPtkl( ptclNull, r.getLoc().x, r.getLoc().y, particles, ptcl.getRadius());
//					
//				}else{
////					r.setColor2(0, 0, 0, 100);
//				}
				
				repel = r.pushParticle(ptcl);
				ptcl.applyRepellForce(repel);
			}
		}
	}

	/**
	 * A function for particles to interact with all <code>Repeller</code>'s
	 * that are near to the repeller
	 * 
	 * @param repellers
	 *            ArrayList
	 * @see Particle Class Particle
	 * @see #calcPtclReaction(Repeller, float, Particle, boolean, PVector)
	 * @see #applyObstcles(List, boolean)
	 * @deprecated
	 */
	@Deprecated
	public void applyRepellers(ArrayList<Repeller> repellers, boolean day) {
		// For every Particle
		Particle ptcl = null;
		Repeller r = null;
		for (int i = 0; i < particles.size(); i++) {
			ptcl = particles.get(i);

			float distToCenterPS = ptcl.getLoc().dist(origin);
			float n = PApplet.norm(distToCenterPS, 0, p.width / 2f);
			ptcl.setMass(n);
			// For every Repeller

			for (int j = 0; j < repellers.size(); j++) {

				r = repellers.get(j);
				// Calculate the distance from a Repeller to the particle
				float d = ptcl.getLoc().dist(r.getLoc());

				PVector repel = new PVector(0, 0);
				calcPtclReaction(r, d, ptcl, day, repel);

			}
		}
	}

	/**
	 * here all the {@link Property} stuff gets worked out
	 * 
	 * @param r
	 *            the {@link Repeller}
	 * @param d
	 *            the distance to the <code>epeller</code>
	 * @param ptcl
	 *            the {@link Particle}
	 * @param day
	 *            the day or nite time if <code>true</code> it is day
	 * @param repel
	 *            a <code>PVector</code> for repelling
	 * @see #reactOnPropValues(Repeller, int, int, PVector, Particle)
	 * @deprecated
	 */
	@Deprecated
	private void calcPtclReaction(Repeller r, float d, Particle ptcl,
			boolean day, PVector repel) {

		if (d <= r.getRadius()) {

			// this is in private space
			if ((ptcl.getPathNum() == 0) || (ptcl.getPathNum() == 1)
					|| (ptcl.getPathNum() == 2)) {

				if (day) {
					// at daytime
					reactOnPropValues(r, 0, 0, repel, ptcl);
				} else {
					// at nite
					reactOnPropValues(r, 1, 0, repel, ptcl);

				}
				// this is in public space
			} else if ((ptcl.getPathNum() == 3) || (ptcl.getPathNum() == 4)
					|| (ptcl.getPathNum() == 5)) {

				// at Daytime

				if (day) {
					reactOnPropValues(r, 0, 1, repel, ptcl);

				} else {
					// at nite
					reactOnPropValues(r, 1, 1, repel, ptcl);

				}
				// this is in work space
			} else if ((ptcl.getPathNum() == 6) || (ptcl.getPathNum() == 7)
					|| (ptcl.getPathNum() == 8)) {
				// at Daytime
				if (day) {
					reactOnPropValues(r, 0, 2, repel, ptcl);

				} else {
					// at nite
					reactOnPropValues(r, 1, 2, repel, ptcl);

				}

			} else {

				// if the Particle has no path to follow just a basic action
				// takes place
				// this is for pushing the particles that build the path around

				repel = r.pushParticle(ptcl);
				ptcl.applyRepellForce(repel);
				ptcl.setMaxspeed(ptcl.getMaxspeed() + 0.05f);
				ptcl.setMass(ptcl.getMass() - 0.01f);
				ptcl.setMaxforce(ptcl.getMaxforce() + 0.01f);

			}

		} else {
			// if the distance his higher than the Repeller's radius
			// do nothing
		}
	}

	/**
	 * Here the Reaction on the Force gets calculated<br>
	 * 
	 * @param f
	 *            a Force
	 * @param ptcl
	 *            a Particle
	 * @param day
	 *            the day and night boolean
	 */
	private void calcPtclReactionOnForce(Force f, Particle ptcl, boolean day) {
		float d = ptcl.getLoc().dist(f.getLoc());
		if (d <= f.getRadius()) {

			// this is in private space
			if ((ptcl.getPathNum() == 0) || (ptcl.getPathNum() == 1)
					|| (ptcl.getPathNum() == 2)) {

				if (day) {
					// at daytime
					reactOnForceValues(f, 0, 0, ptcl, day);
				} else {
					// at nite
					reactOnForceValues(f, 1, 0, ptcl, day);

				}
				// this is in public space
			} else if ((ptcl.getPathNum() == 3) || (ptcl.getPathNum() == 4)
					|| (ptcl.getPathNum() == 5)) {

				// at Daytime

				if (day) {
					reactOnForceValues(f, 0, 1, ptcl, day);

				} else {
					// at nite
					reactOnForceValues(f, 1, 1, ptcl, day);

				}
				// this is in work space
			} else if ((ptcl.getPathNum() == 6) || (ptcl.getPathNum() == 7)
					|| (ptcl.getPathNum() == 8)) {
				// at Daytime
				if (day) {
					reactOnForceValues(f, 0, 2, ptcl, day);
				} else {
					// at nite
					reactOnForceValues(f, 1, 2, ptcl, day);
				}
			} else {
				// if the Particle has no path to follow just a basic action
				// takes place
				// this is for pushing the particles that build the path around

				// f.setRepel(f.pushParticle(ptcl));
				// ptcl.applyRepellForce(f.getRepel());
				// ptcl.setMaxspeed(ptcl.getMaxspeed() + 0.05f);
				// ptcl.setMass(ptcl.getMass() - 0.01f);
				// ptcl.setMaxforce(ptcl.getMaxforce() + 0.01f);
			}
		}
	}

	/**
	 * A method to test if the Particlesystem still has particles
	 * 
	 * @return boolean
	 */
	boolean dead() {
		if (particles.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the origin
	 */
	public synchronized PVector getOrigin() {
		return origin;
	}

	/**
	 * this for gettin the proper values from the <code>Force</code><br>
	 * 
	 * @param f
	 *            the <code>force</code>
	 * @param time
	 *            the <code>Property</code> value time
	 * @param space
	 *            the <code>Property</code> value space
	 * @param ptcl
	 * @param DAY
	 */
	private void reactOnForceValues(Force f, int time, int space,
			Particle ptcl, boolean DAY) {

		switch (f.valueByIndex(time, space)) {
		case -2:
			f.setRepel(f.pushParticle(ptcl));
			ptcl.applyRepellForce(f.getRepel());
			changeColorHue(ptcl, f, time, space);
			ptcl.setMaxforce(ptcl.getMaxforce() + 0.1f);
			ptcl.setMaxspeed(ptcl.getMaxspeed() + 0.2f);
			if (ptcl.getMaxspeed() > 3f) {
				ptcl.setMaxspeed(3f);
			}
			if (ptcl.getMaxforce() > 3f) {
				ptcl.setMaxforce(3f);
			}

			break;
		case -1:
			f.setRepel(f.pushParticle(ptcl));
			ptcl.applyRepellForce(f.getRepel());
			changeColorHue(ptcl, f, time, space);

			ptcl.setMaxspeed(ptcl.getMaxspeed() + 0.1f);

			if (ptcl.getMaxspeed() > 3f) {
				ptcl.setMaxspeed(3f);
			}

			break;
		case 0:
			changeColorHue(ptcl, f, time, space);
			break;
		case 1:
			// repel = r.pushParticle(ptcl);
			// ptcl.applyRepellForce(repel);
			changeColorHue(ptcl, f, time, space);
			ptcl.setMaxspeed(ptcl.getMaxspeed() - 0.1f);
			if (ptcl.getMaxspeed() < 1.5 && DAY) {
				ptcl.setMaxspeed(1.5f);
			}
			if (ptcl.getMaxspeed() < 1.5 && (DAY != true)) {
				ptcl.setMaxspeed(1.0f);
			}
			break;
		case 2:
			// repel = r.pushParticle(ptcl);
			// ptcl.applyRepellForce(repel);
			changeColorHue(ptcl, f, time, space);
			ptcl.setMaxforce(ptcl.getMaxforce() - 0.1f);
			ptcl.setMaxspeed(ptcl.getMaxspeed() - 0.2f);
			if (ptcl.getMaxforce() < 0.5) {
				ptcl.setMaxforce(0.4f);
			}
			if (ptcl.getMaxspeed() < 1.5 && DAY) {
				ptcl.setMaxspeed(1.5f);
			} else if (ptcl.getMaxspeed() < 1.5 && (DAY != true)) {
				ptcl.setMaxspeed(1.0f);
			}
			break;
		}

	}

	/**
	 * this changes the Color of an {@link Particle}
	 * 
	 * @param ptcl
	 *            the Particle to work with
	 * @param f
	 *            the {@link Force}
	 * @param time
	 *            the day or nite time
	 * @param space
	 *            to private public work.
	 */
	private void changeColorHue(Particle ptcl, Force f, int time, int space) {

		int minHue = 10;
		int maxHue = 90;

		int tempCol;
		float tempColH;

		int newHue;

		// Mapping the vales
		int newHueM;

		tempCol = ptcl.getCol1();
		tempColH = p.hue(tempCol);
		newHue = PApplet.floor(tempColH + f.valueByIndex(time, space));
		newHueM = PApplet.constrain(newHue, minHue, maxHue);
		ptcl.setColorCol1(newHueM, 100, 100, 100);

		tempCol = ptcl.getCol2();
		tempColH = p.hue(tempCol);
		newHue = PApplet.floor(tempColH + f.valueByIndex(time, space));
		newHueM = PApplet.constrain(newHue, minHue, maxHue);
		ptcl.setColorCol2(newHueM, 50, 100, 50);

	}

	/**
	 * this changes the Color of an {@link Particle}
	 * 
	 * @param ptcl
	 *            the Particle to work with
	 * @param f
	 *            the {@link Force}
	 * @param time
	 *            the day or nite time
	 * @param space
	 *            to private public work.
	 */
	private void changeColorSaturation(Particle ptcl, Force f, int time,
			int space) {

		int minSat1 = 0;
		int maxSat1 = 100;
		int minSat2 = 0;
		int maxSat2 = 50;

		int tempCol;
		float tempColS;

		int newSat;

		// Mapping the vales
		int newSatM;

		tempCol = ptcl.getCol1();
		tempColS = p.saturation(tempCol);
		newSat = PApplet.floor(tempColS + f.valueByIndex(time, space));
		newSatM = PApplet.constrain(newSat, minSat1, maxSat1);
		ptcl.setColorCol1(80, newSatM, 0, 100);

		tempCol = ptcl.getCol2();
		tempColS = p.saturation(tempCol);
		newSat = PApplet.floor(tempColS + f.valueByIndex(time, space));
		newSatM = PApplet.constrain(newSat, minSat2, maxSat2);
		ptcl.setColorCol2(80, newSatM, 0, 50);

	}

	/**
	 * this changes the Color of an {@link Particle}
	 * 
	 * @param ptcl
	 *            the Particle to work with
	 * @param f
	 *            the {@link Force}
	 * @param time
	 *            the day or nite time
	 * @param space
	 *            to private public work.
	 */
	private void changeColorSatBrightness(Particle ptcl, Force f, int time,
			int space) {

		int minBr1 = 0;
		int maxBr1 = 100;
		int minBr2 = 0;
		int maxBr2 = 50;

		int minSat1 = 0;
		int maxSat1 = 100;
		int minSat2 = 0;
		int maxSat2 = 50;

		int tempCol;
		float tempColB;
		float tempColS;

		int newSat;
		int newBr;

		// Mapping the vales
		int newSatM;
		// Mapping the vales
		int newBrM;

		tempCol = ptcl.getCol1();
		tempColB = p.hue(tempCol);
		tempColS = p.saturation(tempCol);
		newBr = PApplet.floor(tempColB + f.valueByIndex(time, space));
		newSat = PApplet.floor(tempColS + f.valueByIndex(time, space));
		newBrM = PApplet.constrain(newBr, minBr1, maxBr1);
		newSatM = PApplet.constrain(newSat, minSat1, maxSat1);

		ptcl.setColorCol1(80, newSatM, newBrM, 100);

		tempCol = ptcl.getCol2();

		tempColB = p.hue(tempCol);
		tempColS = p.saturation(tempCol);
		newBr = PApplet.floor(tempColB + f.valueByIndex(time, space));
		newSat = PApplet.floor(tempColS + f.valueByIndex(time, space));
		newBrM = PApplet.constrain(newBr, minBr2, maxBr2);
		newSatM = PApplet.constrain(newSat, minSat2, maxSat2);

		ptcl.setColorCol2(80, newSatM, newBrM, 100);

	}

	/**
	 * here the reaction on the property gets applied
	 * 
	 * @param r
	 *            the Repeller
	 * @param time
	 *            day or nite represented by 0 and 1
	 * @param space
	 *            private / public or /workspace represented by 0, 1 and 2
	 * @param repel
	 *            the replling force
	 * @param ptcl
	 *            the Particle
	 * @see #applyObstcles(List, boolean)
	 * @see Property#affectionProps
	 * @deprecated
	 */
	@Deprecated
	private void reactOnPropValues(Repeller r, int time, int space,
			PVector repel, Particle ptcl) {

		switch (r.property.valueByIndex(time, space)) {
		case -2:
			repel = r.pushParticle(ptcl);
			ptcl.applyRepellForce(repel);
			// ptcl.setColorCol1(80, 100, 100, 100);
			//
			// ptcl.setColorCol2(80, 100, 100, 100);
			// ptcl.setMaxforce(ptcl.maxforce + r.property.valueByIndex(0, 0));
			// ptcl.setMaxspeed(ptcl.maxspeed + r.property.valueByIndex(0, 0));
			break;
		case -1:
			repel = r.pushParticle(ptcl);
			ptcl.applyRepellForce(repel);
			ptcl.setColorCol1(40, 100, 100, 100);

			ptcl.setColorCol2(40, 100, 100, 100);
			// ptcl.setMaxforce(ptcl.maxforce + r.property.valueByIndex(0, 0));
			// ptcl.setMaxspeed(ptcl.maxspeed + r.property.valueByIndex(0, 0));
			break;
		case 0:
			// do nothing
			// ptcl.setColorCol1(120, 100, 100, 100);
			//
			// ptcl.setColorCol2(120, 100, 100, 100);
			break;
		case 1:
			// repel = r.pushParticle(ptcl);
			// ptcl.applyRepellForce(repel);
			ptcl.setColorCol1(40, 100, 100, 100);

			ptcl.setColorCol2(40, 100, 100, 100);
			// ptcl.setMaxforce(ptcl.maxforce + r.property.valueByIndex(0, 0));
			// ptcl.setMaxspeed(ptcl.maxspeed + r.property.valueByIndex(0, 0));
			break;
		case 2:
			// repel = r.pushParticle(ptcl);
			// ptcl.applyRepellForce(repel);
			ptcl.setColorCol1(80, 100, 100, 100);

			ptcl.setColorCol2(80, 100, 100, 100);
			// ptcl.setMaxforce(ptcl.maxforce + r.property.valueByIndex(0, 0));
			// ptcl.setMaxspeed(ptcl.maxspeed + r.property.valueByIndex(0, 0));
			break;
		}

	}

	/**
	 * this runs the ParticleSystem
	 * 
	 */
	public void run() {
		// Cycle through the ArrayList backwards b/c we are deleting
		for (int i = particles.size() - 1; i >= 0; i--) {
			Particle ptcl = particles.get(i);
			ptcl.run();
			if (ptcl.dead()) {
				particles.remove(i);
			}
		}
	}

	/**
	 * sets a new origin for the Emitter
	 * 
	 * @param newOrigin
	 * @return PVector
	 */
	public PVector setEmitterOrigin(PVector newOrigin) {

		origin = newOrigin;
		return origin;
	}

	/**
	 * @param origin
	 *            the origin to set
	 */
	public synchronized void setOrigin(PVector origin) {
		this.origin = origin;
	}

}
