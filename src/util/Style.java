package util;

import processing.core.PApplet;

public class Style {

	
	
	/**
	 * the PApplet
	 */
	private static PApplet p;
	public static int textColorWhite ;
	public static int bgBlur;
	public static int boxCol;


	
	public static void setPAppletStyle(PApplet p_) {
		p = p_;

	}
	
	public static void create(){
		
		textColorWhite = p.color(255, 0, 100, 100);
		bgBlur = p.color(0, 0, 0, 100);
		boxCol = p.color(255);

		
	}

}
