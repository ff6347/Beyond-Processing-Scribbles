package particleSystem;

import java.util.ArrayList;


import processing.core.PApplet;
import processing.core.PVector;
import processing.xml.XMLElement;
import util.Style;

/**
 * This Class is for helping with the <code>ParticleSystem</code><br>
 * they don't affect the Core Application but are very usefull<br>
 * 
 * @author fabiantheblind
 * @see Particle Class Particle
 * @see Path Class Path
 * @see Repeller Class Repeller
 * @author fabiantheblind
 * 
 */
@SuppressWarnings("deprecation")
public class PSUtil {

	public static int numOfPaths = 9;
	/**
	 * the PApplet
	 * 
	 */
	private static PApplet p;

	public static void applyPaths(ArrayList<Particle> ptclsList,
			boolean switchPath, ArrayList<Path> pathsList) {

		for (int i = 0; i < ptclsList.size(); i++) {
			Particle ptcl = ptclsList.get(i);
			// if the particle is not part of a path
			if (ptcl.isHidden() != true) {
				// use the switchPath variable for random path selection
				if (switchPath) {
					ptcl.setPathNum(PApplet.floor(p
							.random(0, PSUtil.numOfPaths)));// myPathNum;
				}
				ptcl.applyForces(ptclsList, pathsList.get(ptcl.getPathNum()));
				switchPath = false;

			}
			// Call the generic run method (update, borders, display, etc.)
			ptcl.run();

		}
	}

	// Particle stuff

	/**
	 * this is for displaying the repellers for debugging
	 * 
	 * @param someRepellers
	 * @see Repeller Class Repeller
	 * @see #makeSomeRepellers(ArrayList)
	 * 
	 */
	public static void displaySomeRepellers(ArrayList<Repeller> someRepellers) {

		// Display all repellers
		for (int i = 0; i < someRepellers.size(); i++) {
			Repeller r = someRepellers.get(i);
			r.display();
			r.drag();
		}
	}

	/**
	 * @return the numOfPaths
	 */
	public static synchronized int getNumOfPaths() {
		return numOfPaths;
	}


	/**
	 * This a number of points circling around the center. for a smother path
	 * give him more segments
	 * 
	 * @param segments
	 *            the number of segments in a path
	 * @param radius
	 *            the radius around the path
	 * @param size
	 *            the diameter of the circle
	 * @return Path
	 * @see Path Class Path
	 * @see <a href="Path.html#addPointPtcl(float, float)"><code>Path.addPointPtcl</code></a>
	 */
	public static Path initCirclePath(int segments, int radius, int size,int pathNum) {

		Path path = new Path(p, radius);
		for (int i = 0; i <= 360; i += 360 / segments) {

			path.addPointPtcl((p.width / 2) + (PApplet.sin(PApplet.radians(i)))
					* size, (p.height / 2) + (PApplet.cos(PApplet.radians(i)))
					* size, pathNum);
		}
		return path;
	}

	/**
	 * for easier initalizing of particles
	 * 
	 * @param numPtkls
	 *            the number of particles
	 * @param ptclRadius
	 *            the radius of the particle for collision with others
	 * @param ptclsList
	 *            the List of all Particles
	 * @return ptclsList
	 * @see Particle Class Particle
	 * @see #newPtkl(int, Particle, float, float, ArrayList, float)
	 */
	public static ArrayList<Particle> initParticles(int numPtkls,
			float ptclRadius, ArrayList<Particle> ptclsList) {

		ptclsList = new ArrayList<Particle>();
		Particle ptcl = null;
		for (int i = 0; i < numPtkls; i++) {
			newPtkl( ptcl, p.random(p.width), p.random(p.height), ptclsList,
					ptclRadius);

		}
		return ptclsList;
	}

//	/**
//	 * initalze the Propertys
//	 * @return an ArrayList of propertys
//	 */
//	public static ArrayList<Property> initPropertysList() {
//		
//		ArrayList<Property> propertysList = new ArrayList<Property>();
//		
//		XMLElement[] myObstaclObjcts = XMLImporter.getObsctlObjects();
//		String theName = null;
//		int[][] theValues = null;
//		for (int i = 0; i < myObstaclObjcts.length; i++) {
//			theName = myObstaclObjcts[i].getStringAttribute("name");
//			
//			theValues = XMLImporter.ObjectPropertys(i,
//					myObstaclObjcts[i].getParent());
//			propertysList.add(new Property(i, theName, theValues));
//		}
//		return propertysList;
//
//	}

	/**
	 * this is just for debugging
	 * 
	 * @param someRepellers
	 *            a list of some repellers
	 * @see Repeller Class Repeller
	 */
	public static void makeSomeRepellers(ArrayList<Repeller> someRepellers) {

		int numRepellers = 12;
		// make some repellers in a circle
//		for (int i = 0; i <= 360; i += 360 / numRepellers) {
//
//			Repeller rep = new Repeller(p, p.width / 2
//					+ PApplet.sin(PApplet.radians(i)) * 180, p.height / 2
//					+ PApplet.cos(PApplet.radians(i)) * 180);
//			rep.setG(PApplet.pow(10, 3));
//			someRepellers.add(rep);
//		}
		
		for(int x =0;x< p.width+5;x = x+20 ){
			for(int y = 0; y < p.height +5 ; y = y +20){
				Repeller rep = new Repeller(p,new PVector (x,y),10);
			
					rep.setColor2(260, 100, 100, 100);
//					rep.setG(-100);
					rep.setG(PApplet.pow(10, 2));
			
		someRepellers.add(rep);
				
			}
		}

	}

	/**
	 * @param someRepellers
	 * @param propertysList
	 * @deprecated
	 */
	public static void makeSomeRepellersWithPropertys(
			ArrayList<Repeller> someRepellers, ArrayList<Property> propertysList) {

		int numRepellers = 7;
		int index = 0;
		// make some repellers
		for (int i = 0; i <= 360; i += 360 / numRepellers) {
			PVector loc = new PVector(p.width / 2
					+ PApplet.sin(PApplet.radians(i)) * 180, p.height / 2
					+ PApplet.cos(PApplet.radians(i)) * 180);
			Repeller rep = new Repeller(p, loc, ((10 * 10) * 10), 10,
					propertysList.get(index));// Repeller(p,p.width / 2 +
												// PApplet.sin(PApplet.radians(i))*180,p.height
												// / 2 +
												// PApplet.cos(PApplet.radians(i))*180);
			rep.setG(PApplet.pow(10, 3));
			someRepellers.add(rep);
			index++;
		}

	}

//	/**this builds 9 paths to follow
//	 * @param pathsList
//	 * @see #initCirclePath(int, int, int, int)
//	 */
//	public static void makeSpaces(ArrayList<Path> pathsList) {
//
//		setNumOfPaths(9);
//		for (int p = 0; p < numOfPaths; p++) {
//			pathsList.add(initCirclePath(13, Style.pathsRadius9[p], Style.pathsSize9[p], p));
//		}
//
//	}

	/**
	 * 	 * builds a new <code>Particle</code> and adds him to the <code>ArrayList</code> of <code>Particle</code>s

	 * @param counter
	 * @param ptcl
	 * @param x
	 * @param y
	 * @param ptclsList
	 * @param ptclRadius
	 */
	public static void newPtkl( Particle ptcl, float x, float y,
			ArrayList<Particle> ptclsList, float ptclRadius) {


		ptcl = new Particle(p, new PVector(x, y), new PVector(x, y),ptclRadius,0.5f,0.2f, true, false);

		ptclsList.add(ptcl);

	}

	/**
	 * not used right now
	 * 
	 * @param obstclObjList
	 * @param ptclsList
	 * @deprecated also old stuff
	 */
	@Deprecated
//	public static void ptclsReactOnObject(
//			ArrayList<ObstacleObject> obstclObjList,
//			ArrayList<Particle> ptclsList) {
//		// println(obstclObjList.get(0).ObstclsRepellerList.get(0).radius);
//		float mySize = obstclObjList.get(0).ObstclsRepellerList.get(0)
//				.getRadius();
//		float myNewForce = PApplet.map(mySize, 10, 100, 0.5f, 13f);
//
//		float myNewSpeed = PApplet.map(mySize, 10, 100, 0.5f, 13f);
//		for (int i = 0; i < ptclsList.size(); i++) {
//
//			ptclsList.get(i).setMaxforce(myNewForce);
//			ptclsList.get(i).setMaxspeed(myNewSpeed);
//
//		}
//	}

	/**
	 * resets the Pathpoints
	 * @param pathsList
	 */
	public static void resetPath(ArrayList<Path> pathsList) {

		// TODO Auto-generated method stub
		for (int i = 0; i < pathsList.size(); i++) {

			pathsList.get(i).resetPointPtcls();
		}
	}

	/**
	 * @param numOfPaths
	 *            the numOfPaths to set
	 */
	public static synchronized void setNumOfPaths(int numOfPaths) {
		PSUtil.numOfPaths = numOfPaths;
	}

	/**
	 * 
	 * this sets the PApplet for all the functions it has to be called first
	 * 
	 * @param p_
	 */
	public static void setPApplet(PApplet p_) {

		p = p_;

	}

}
