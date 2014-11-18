package model;

import java.util.Set;
import java.awt.Point;
import java.awt.Dimension;

public class MyPongModel implements PongModel {

// Lots of variables
	private Dimension field; // The background, two-dimensional
	private Point ball; // The x and y position of the ball, can use getX and getY

	private int leftPos; // Height position of left bar
	private int leftHeight; // The size of the left bar, will increase or decrease after loosing points

	private int rightPos; // Height position of right bar
	private int rightHeight; // The size of the right bar, will increase or decrease after loosing points

	private int leftScore; // The score of the player on the left
	private int rightScore; // The score of the player on the right


/**
 * The PongModel keeps track of the bars, the ball and the game state.
 */
	public MyPongModel {

		// Lots of this.variables


    /**
     * Takes the inputs and applies them to the model, computing one
     * simulation step. delta_t is the time that has passed since the
     * last compute step -- use this in your time integration to have
     * the items move at the same speed, regardless of the framerate.
     */
    	public void compute(Set<Input> input, long delta_t) {
    		
    	}

    /**
     * getters that take a BarKey LEFT or RIGHT
     * and return positions of the various items on the board
     */
    	public int getBarPos(BarKey k) {
    		switch(k)
	    	case LEFT:
    			// Return position of left barkey.
	    		return leftPos;
    		case RIGHT:
    			// Returns position of right barkey.
    			return rightPos;
    		default: 
    			return 0;
    	}

	    public int getBarHeight(BarKey k) {
    	    switch(k)
        	case LEFT:
        		// Return height of left barkey
                return leftHeight;
	        case RIGHT:
    	    	// Return height of right barkey.
                return rightHeight;
        	default:
        		return 0;
	    }

    	public Point getBallPos() {
    		// Create different private variables with dimension and point, get the x and y values of the ball.
            return this.getLocation();
    	}

    /**
     * Will output information about the state of the game to be
     * displayed to the players
     */
    	public String getMessage() {
    		return "Let's play some Pong!";
    	}

    /**
     * getter for the scores.
     */
    	public String getScore(BarKey k) {
            switch(k)
            case LEFT:
                return Integer.toString(leftScore);
            case RIGHT:
                return Integer.toString(rightScore);
            default:
                return 0;
    	}
    
    /**
     * a valid implementation of the model will keep the field size
     * will remain constant forever.
     */
    	public Dimension getFieldSize() {
            return this.getSize();
    	}
	}
}
