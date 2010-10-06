package util;

import processing.core.PApplet;

public class Style {

	
	
	public static  int superSoftWhite;
	/**
	 * the PApplet
	 */
	private static PApplet p;
	public static int textColorWhite ;
	public static int bgBlur;
	public static int boxCol;
	public static  int ptclCol1;
	public static  int ptclCol2;

	public static int ptclCol3 ;




	
	public static void setPAppletStyle(PApplet p_) {
		p = p_;

	}
	
	public static void create(){
		
		textColorWhite = p.color(255, 0, 100, 100);
		bgBlur = p.color(0, 0, 0, 100);
		boxCol = p.color(255);
		ptclCol1 = p.color(0,100);
		ptclCol2 = p.color(0,100);
		ptclCol3 = p.color(0,100);
		superSoftWhite = p.color(255,255,255,25);



		
	}

}
