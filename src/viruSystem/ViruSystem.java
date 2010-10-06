/**
 * @author fabiantheblind
 *
 */
package viruSystem;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class ViruSystem {

	
	private PVector origin;
	private PApplet p;
	ArrayList<Virus> virs;

	public ViruSystem(PApplet p, int num, PVector v,
			ArrayList<Virus> virs) {
		this.virs = virs; // Initialize the arraylist
		this.p = p;
		this.origin = v.get(); // Store the origin point
		// Add "num" amount of virus to the arraylist
		for (int i = 0; i < num; i++) {
			virs.add(new Virus(p, origin));
		}
	}
	void addVirus(Virus vi) {
		virs.add(vi);
	}
	
	public void addVirusEmitter(boolean pointOrigin) {

		Virus vi;

		if (pointOrigin) {
			vi = new Virus( p, origin);
	
		} else {
			PVector myOrigin = new PVector(p.random(p.width),
					p.random(p.height));
			vi = new Virus( p, myOrigin);

		}

		virs.add(vi);
	}
	public void applyForce(PVector f) {
		Virus vi = null;
		for (int i = 0; i < virs.size(); i++) {
			vi = virs.get(i);
			vi.applyRepellForce(f);
		}
	}
	public void run() {
		// Cycle through the ArrayList backwards b/c we are deleting
		for (int i = virs.size() - 1; i >= 0; i--) {
			Virus vi = virs.get(i);
			vi.run();
			if (vi.dead()) {
				virs.remove(i);
			}
		}
	}
}
