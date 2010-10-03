package util;



import processing.core.PApplet;

/**
 * this is for debbuging only and has no effect on the Application<br>
 * 
 * @author fabiantheblind
 * @author PDXIII
 * 
 * 
 */
public class Debug {

	/**
	 * for writing images if true
	 * 
	 * @see #writeIMGs()
	 * 
	 */
	public static boolean writeImg = false;
	/**
	 * the PApplet
	 */
	private static PApplet p;

	/**
	 * a unique number for the image
	 * 
	 * @see #writeIMGs()
	 */
	private static int imgNum = 0;

	/**
	 * to pass the PApplet to all Methods a static Class doesn't need a
	 * Constructor
	 * 
	 * @param p_
	 *            the PApplet
	 */
	public static void setPAppletDebug(PApplet p_) {
		p = p_;

	}

	/**
	 * write some images (TIff Sequenzes for videos)
	 * 
	 * @see #imgNum
	 * @see #writeImg
	 */
	public static void writeIMGs() {
		if (writeImg) {
			String sa = PApplet.nf(imgNum, 6);
			p.saveFrame("box-" + sa + ".tif");
			imgNum++;
		}
	}



	/**
	 * Draw a Grid for adjustment
	 */
	public static void drawGrid() {

		float gridSize = 100;

		for (int i = 0; i < 100; i++) {
			p.strokeWeight(1);
			p.stroke(0);

			p.line(i * gridSize, 0, i * gridSize, p.height);
			p.line(0, i * gridSize, p.width, i * gridSize);

			p.noStroke();
		}
	}

	
	/**
	 * this is an overlay for not using the console draws the
	 * processing.frameRate on the screen
	 */
	public static void drawFrameRate() {

		p.noStroke();
		p.fill(Style.textColorWhite);
		p.text("Framerate: " + p.frameRate, 50, 50);
		p.noFill();
	}

	/**
	 * this is an overlay for not using the console draws the
	 * processing.frameCount on the screen
	 */
	public static void drawFrameCount() {

		p.noStroke();
		p.fill(Style.textColorWhite);
		p.text("Framecount: " + p.frameCount, 50, 64);
		p.noFill();
	}

	/**
	 * for writing single images on keystroke
	 * 
	 * @param time
	 */
	public static void saveFrame(float time) {
		// TODO Auto-generated method stub
		p.saveFrame("./data/MyImg" + time + ".tif");

	}
	
	

}
