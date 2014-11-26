package model;

import java.util.Set;
import java.awt.Point;
import java.awt.Dimension;

public class MyPongModel implements PongModel {

// Lots of variables
	private Dimension field; // The background, two-dimensional, can use getSize
	private Point ball; // The x and y position of the ball, can use getX and getY

	private int leftPos; // Height position of left bar
	private int leftHeight; // The size of the left bar, will increase or decrease after loosing points

	private int rightPos; // Height position of right bar
	private int rightHeight; // The size of the right bar, will increase or decrease after loosing points

	private int leftScore; // The score of the player on the left
	private int rightScore; // The score of the player on the right

    private String s1; // Name of player 1
    private String s2; // Name of player 2

    private int ballDirectionX;
    private int ballDirectionY;

/**
 * The PongModel keeps track of the bars, the ball and the game state.
 */
	public MyPongModel (String s1, String s2) {
		// Lots of this.variables
        this.field = new Dimension(600, 400);
        this.ball = new Point(300,200);
        this.leftPos = 200;
        this.leftHeight = 100;
        this.rightPos = 200;
        this.rightHeight = 100;
        this.leftScore = 0;
        this.rightScore = 0;
        this.s1 = s1;
        this.s2 = s2;
	this.ballDirectionX = 1;
	this.ballDirectionY = 0;
	

    }

    public void resetBall(){
	this.ball.move(300,200);
	this.ballDirectionX = 1;
	this.ballDirectionY = 0;
}
    /**
     * Takes the inputs and applies them to the model, computing one
     * simulation step. delta_t is the time that has passed since the
     * last compute step -- use this in your time integration to have
     * the items move at the same speed, regardless of the framerate.
     */
    public void compute(Set<Input> input, long delta_t) {
        for (Input i: input) {
	    switch(i.key){
	        case LEFT:
                switch(i.dir) {
                    case UP:
                        if((this.leftPos - (this.leftHeight/2)) >= 0){
                        this.leftPos-=5;
                        }
                        break;
                    case DOWN:
                        if((this.leftPos + (this.leftHeight/2)) <= this.field.height){
                        this.leftPos+=5;
                    }   
                        break;
                    }
                    break;
	        case RIGHT:
                    switch(i.dir) {
                    case UP:
                        if((this.rightPos - (this.rightHeight/2)) >= 0){
                        this.rightPos-=5;
                    }
                        break;
                    case DOWN:
                        if((this.rightPos + (this.rightHeight/2)) <= this.field.height)
                        this.rightPos+=5;
                        break;
                    }
            	break;       
	    }
        }

    	jMoveBall();	
    }

    public void moveBall(){
    if ((this.ball.getX() <= this.field.getWidth()) && this.ballDirectionX == 1 ){    

        if (this.ball.getX() == this.field.getWidth()) {
        this.ballDirectionX = -1;

    }
        this.ball.translate(5,0);

    }
    else if (this.ball.getX() >= 0){
        this.ball.translate(-5,0);
        if ((this.ball.getY() <= (this.rightPos + this.rightHeight)) && this.ball.getY() > (this.rightPos - this.rightHeight)){
        this.ballDirectionX = -1;   
        }
        else if (this.ball.getX() == this.field.getWidth()){
        resetBall();
        this.leftScore++;
        }
    }

    if ((this.ball.getY() <= this.field.getHeight()) && this.ballDirectionY == 1){
        this.ball.translate(0,5);
    }
    
    }
    
    
    public void jMoveBall() {
        // Everything regarding left movement:
        if (this.ballDirectionX == -1) {
            if (this.ball.getX() > 0) {
                this.ball.translate(-5,0);
            }
            if (this.ball.getX() == 0) {
                    resetBall();
                    this.rightScore++;
                }
            if (this.ball.getX() == 10) {
                if (((this.ball.getY() - 10) <= (this.leftPos + this.leftHeight/2)) && (this.ball.getY() + 10) > this.leftPos - this.leftHeight/2) {
                    this.ballDirectionX = 1;
                }
            }
        }   
        
        // Everything regarding right movement:
        if (this.ballDirectionX == 1) {
            if (this.ball.getX() < this.field.getWidth()) {
                this.ball.translate(5,0);
            }
            if (this.ball.getX() == this.field.getWidth()) {
                resetBall();
                this.leftScore++;
                }
            if (this.ball.getX() == (this.field.getWidth() - 10)) {
                if (((this.ball.getY() - 10) <= (this.rightPos + this.rightHeight/2)) && (this.ball.getY() + 10) > (this.rightPos - this.rightHeight/2)) {
                    this.ballDirectionX = -1;
                }
            }
        }
    }

    
    /**
     * getters that take a BarKey LEFT or RIGHT
     * and return positions of the various items on the board
     */
	public int getBarPos(BarKey k) {
    	switch(k) {
    	case LEFT:
	   		return this.leftPos;
    	case RIGHT:
    		return this.rightPos;
    	default: 
			return 0;
        }
    }

    public int getBarHeight(BarKey k) {
        switch(k) {
        case LEFT:
            return this.leftHeight;
	    case RIGHT:
            return this.rightHeight;
        default:
    		return 0;
        }
	}

	public Point getBallPos() {
        return this.ball.getLocation();
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
        switch(k) {
        case LEFT:
            return Integer.toString(this.leftScore);
        case RIGHT:
            return Integer.toString(this.rightScore);
        default:
            return "";
        }
    }

    public int increaseScore(BarKey k) {
        switch(k) {
        case LEFT:
            this.leftScore++;
        case RIGHT:
            this.rightScore++;
        default:
            return 0;
        }
    }
    
    /**
     * a valid implementation of the model will keep the field size
     * will remain constant forever.
     */
	public Dimension getFieldSize() {
        return this.field.getSize();
    }
	
}
