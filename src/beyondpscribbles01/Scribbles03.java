/**
 * 
 */
package beyondpscribbles01;

import java.util.ArrayList;

import particleSystem.PSUtil;
import particleSystem.Particle;
import particleSystem.ParticleSystem;
import particleSystem.Repeller;
import processing.core.PApplet;
import processing.core.PVector;
import util.Style;

/**
 * @author fabianmoronzirfas
 *
 */
public class Scribbles03 extends PApplet{


	
	
	public ArrayList<Repeller> repList;
	public ArrayList<Particle> virusList;
	public ParticleSystem ps;
	
		public void setup(){
			size(800,800,P3D);
			background(255,100);

			smooth();
			Style.setPAppletStyle(this);
			Style.create();
			PSUtil.setPApplet(this);
			repList = new ArrayList<Repeller>();
			virusList = new ArrayList<Particle>();
//			PSUtil.makeSomeRepellers(repList);
			
			Repeller repCenter = new Repeller(this,new PVector (width/2,height/2),10);	
			repList.add(repCenter);

			for(int x =0;x< 2;x++ ){
				
					Repeller rep = new Repeller(this,new PVector (random(width),random(height)),10);	
						rep.setG(-100);
//						rep.setG(PApplet.pow(10, 2));
				
						repList.add(rep);
					
				
			}
			
			virusList = PSUtil.initParticles(20, 5, virusList);
			ps = new ParticleSystem(this, new PVector(width / 2, height / 2), virusList);
			
			for(int i = 0; i<virusList.size();i++){
				
				virusList.get(i).setMass(0.1f);
				virusList.get(i).setMaxspeed(3);
				virusList.get(i).setGravity(10);
				virusList.get(i).setMaxforce(-0.5f);

			}

		}
		
		
		public void draw(){
			ps.run();

			
			ps.applyRepellers(repList);

			PSUtil.displaySomeRepellers(repList);
//			ps.addParticleEmitter(false);

		}

		
		
		
		/**
		 * @param args
		 */
		public static void main(String[] args) {
			// TODO Auto-generated method stub
		}

}
