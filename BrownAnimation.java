import javax.swing.JFrame;


/**
 * This program will simulate Brownian motion. A window consisting of particles
 * which moves randomly until the reach the wall or another particle that has
 * already reached the wall. There are also some options allowing do manipulate
 * the animation during the simulation.
 * 
 *
 */

public class BrownAnimation extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int NUMBER_OF_PARTICLES = 1000;
	// Static constant for the number of particles when program starts
	
	
	/**
	 * This constructor will create the program itself. It will set the setting
	 *  for the frame and inserts all the objects to the frame.
	 * @param numberOfParticles
	 */
	BrownAnimation(int numberOfParticles) {
		setLayout(null);
		setVisible(true);
		setSize(820,690);		
		setLocationRelativeTo(null);	//puts frame on middle of screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Model model = new Model(numberOfParticles);
		View view = new View(model);
		add(view.drawModifiers());
		add(view);
		
		view.clock.start();
		
		
		
	}
	
	public static void main(String[] args) {
		
		new BrownAnimation(NUMBER_OF_PARTICLES);
	}
}
	

