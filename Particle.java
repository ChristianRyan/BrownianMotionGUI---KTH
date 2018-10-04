import java.util.ArrayList;
import java.util.Random;
/**
 * Mathematical class of the particles' nature and movement.
 * @author Christian Ryan
 *
 */

class Particle {
    public double x, y; // Coordinates of the particle in 2D .
    public double L = 3; // Length of the particle's movement.
    public boolean isMoving = true; // If particle is moving.
    public boolean isTracking = false; // If particle is being tracked.
    private Random rand = new Random(); // In case of randomization of start position.
    public ArrayList<Integer> historyX = new ArrayList<Integer>();
    public ArrayList<Integer> historyY = new ArrayList<Integer>();
    
    // Randomizes start position from {x: 0-700, y: 0-600}.
    Particle () {
    	x = rand.nextInt(700);
    	y = rand.nextInt(600);
    }
    // Places start position according to given parameters: x, y.
    Particle (double x, double y){
    	this.x = x;
    	this.y = y;
    }
    // Checks if the particle's coordinates are not within the frame.
    public void checkLimits() {
    	if (x >= 747) { // The frame's Eastern border.
    		x = 747;
    		isMoving = false;
    	}
    	if (y >= 647){ // The frame's Bottom border.
    		y = 647;
    		isMoving = false;
    	}
    	if (y <= 0) { // The frame's Top border.
    		y = 0;
    		isMoving = false;
    	}
    	if (x <= 0) { // The frame's Western border.
    		x = 0;
    		isMoving = false; 
    	}
    	
    }
	public void saveLoc(){
		historyX.add((int)Math.round(x));
		historyY.add((int)Math.round(y));
		
	}

           
	public void randomMove() {
		saveLoc();
		if (isMoving == true) {
			double angle = 2 * Math.PI * new Random().nextDouble();
			x = x + Math.cos(angle) * L;
			y = y + Math.sin(angle) * L;
		} 
		checkLimits();
	}
	
	public String movingToString() {
		if (isMoving == true) {
			return "Moving";
		} else {
			return "Not moving";
		}
	}


}
       