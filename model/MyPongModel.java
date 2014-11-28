package model;

import java.util.Set;
import java.util.Random;
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

    private int xAngle;
    private int yAngle;

    private int speedUpCounter;
    private int speed;

/**
 * The PongModel keeps track of the bars, the ball and the game state.
 */
	public MyPongModel (String s1, String s2) {
        Random randomized = new Random();

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


        int dir1 = (randomized.nextInt(2) + 1);
        int dir2 = (randomized.nextInt(2) + 1);
        int dirX;
        if (dir1 == 2) {dirX = 1;}
        else {dirX = -1;}
        int dirY;
        if (dir2 == 2) {dirY = 1;}
        else {dirY = -1;}

	    this.ballDirectionX = dirX;
	    this.ballDirectionY = dirY;

        
        int ang1 = (randomized.nextInt(5) + 1);
        int ang2 = 10 - ang1;
        this.xAngle = ang2;
        this.yAngle = ang1;

        this.speedUpCounter = 1;
        this.speed = 1;
	   

    }

    public void resetBall(){
    Random randomized = new Random();
    int dir1 = (randomized.nextInt(2) + 1);
    int dir2 = (randomized.nextInt(2) + 1);

    int dirX;
    if (dir1 == 2) {dirX = 1;}
    else {dirX = -1;}

    int dirY;
    if (dir2 == 2) {dirY = 1;}
    else {dirY = -1;}

	this.ball.move(300,200);
	this.ballDirectionX = dirX;
	this.ballDirectionY = dirY;

    
    int ang1 = (randomized.nextInt(5) + 1);
    int ang2 = 10 - ang1;
    this.xAngle = ang2;
    this.yAngle = ang1;

    this.speedUpCounter = 1;
    this.speed = 0;
    
}
    /**
     * Takes the inputs and applies them to the model, computing one
     * simulation step. delta_t is the time that has passed since the
     * last compute step -- use this in your time integration to have
     * the items move at the same speed, regardless of the framerate.
     */
    public void compute(Set<Input> input, long delta_t) {

	if (leftScore == 10){
	    try {
		Thread.sleep(3000);                 //1000 milliseconds is one second.
	    } catch(InterruptedException ex) {
		Thread.currentThread().interrupt();
	    }
	
	    this.leftScore = 0;
	    this.rightScore = 0;
        this.leftHeight = 100;
        this.rightHeight = 100;
	    resetBall();
	    	    
	}	
	if (rightScore == 10){
	    try {
		Thread.sleep(3000);                 //1000 milliseconds is one second.
	    } catch(InterruptedException ex) {
		Thread.currentThread().interrupt();
	    }
	    this.leftScore = 0;
	    this.rightScore = 0;
        this.leftHeight = 100;
        this.rightHeight = 100;
	    resetBall();
	   
	    	    
	}
        for (Input i: input) {
	    switch(i.key){
	        case LEFT:
                switch(i.dir) {
                    case UP:
                        if((this.leftPos - (this.leftHeight/2)) >= 0){
                        this.leftPos -= 10;
                        }
                        break;
                    case DOWN:
                        if((this.leftPos + (this.leftHeight/2)) <= this.field.height){
                        this.leftPos += 10;
                    }   
                        break;
                    }
                    break;
	        case RIGHT:
                    switch(i.dir) {
                    case UP:
                        if((this.rightPos - (this.rightHeight/2)) >= 0){
                        this.rightPos -= 10;
                    }
                        break;
                    case DOWN:
                        if((this.rightPos + (this.rightHeight/2)) <= this.field.height)
                        this.rightPos += 10;
                        break;
                    }
            	break;       
	    }
        }

    	moveBall();
        speedUpCounter++;	

    }
    // -1 * [0,10]
    // (Math.round(10 * Math.cos(this.xAngle))).intValue()
    /*
 Long l = 123L;
Integer correctButComplicated = Integer.valueOf(l.intValue());
Integer withBoxing = l.intValue();
Integer terrible = (int) (long) l;
    */
    
    public void moveBall() {

        if (speedUpCounter % 500 == 0) {
            speed += 3;
        }
        double ballY = this.ball.getY();
        int leftBarLower = this.leftPos + this.leftHeight/2;
        int leftBarHigher = this.leftPos - this.leftHeight/2;

        double ballX = this.ball.getX();
        int rightBarLower = this.rightPos + this.rightHeight/2;
        int rightBarHigher = this.rightPos - this.rightHeight/2;

        // Everything regarding left movement:
        if (this.ballDirectionX == -1) {
            if (this.ball.getX() <= 10 && this.ball.getX() > 0) { //if within range of field
                if (((ballY - 10) <= leftBarLower) && (ballY + 10) >= leftBarHigher) {  //if hitting barkey
                    if ((ballY - 10) >= (leftBarLower - 5)) {   //if hitting lower five
                        this.yAngle = 5;
                        this.xAngle = 5;
                        this.ballDirectionY = 1;
                    }
                    if ((ballY + 10) <= (leftBarHigher + 5)) {  //if hitting higher five
                        this.yAngle = 5;
                        this.xAngle = 5;
                        this.ballDirectionY = -1;
                    }

                    if ((((ballY - 10) < (leftBarLower - 5)) && ((ballY - 10) >= (leftBarLower - 15)))) {
                        this.yAngle = 4;
                        this.xAngle = 6;
                        this.ballDirectionY = 1;
                    }
                    if (((ballY + 10) > (leftBarHigher + 5)) && ((ballY + 10) <= (leftBarHigher + 15))) {
                        this.yAngle = 4;
                        this.xAngle = 6;
                        this.ballDirectionY = -1;
                    }

                    if (((ballY - 10) < (leftBarLower - 15)) && ((ballY - 10) >= (leftBarLower - 30))) {
                        this.yAngle = 3;
                        this.xAngle = 7;
                        this.ballDirectionY = 1;
                    }                    
                    if (((ballY + 10) > (leftBarHigher + 15)) && ((ballY + 10) <= (leftBarHigher + 30))) {
                        this.yAngle = 3;
                        this.xAngle = 7;
                        this.ballDirectionY = -1;
                    }

                    this.ballDirectionX = 1;
                }
            }
            if (this.ball.getX() <= 0) {
                    resetBall();
                    this.leftHeight += 6;
                    this.rightScore++;
                }
            if (this.ball.getX() > 0) {
                this.ball.translate((-1 * this.xAngle) - speed, 0);
            }            
        }   
        
        // Everything regarding right movement:
        if (this.ballDirectionX == 1) {
            /* if (this.ball.getX() >= (this.field.getWidth() - 10) && this.ball.getX() < (this.field.getWidth())) {
                if (((this.ball.getY() - 10) <= (this.rightPos + this.rightHeight/2)) && (this.ball.getY() + 10) > (this.rightPos - this.rightHeight/2)) {
                    this.ballDirectionX = -1;
                }
            } */
            if (this.ball.getX() >= (this.field.getWidth() - 10) && this.ball.getX() < this.field.getWidth()) { //if within range of field
                if (((ballY - 10) <= rightBarLower) && (ballY + 10) >= rightBarHigher) {  //if hitting barkey
                    if ((ballY - 10) >= (rightBarLower - 5)) {   //if hitting lower five
                        this.yAngle = 5;
                        this.xAngle = 5;
                        this.ballDirectionY = 1;
                    }
                    if ((ballY + 10) <= (rightBarHigher + 5)) {  //if hitting higher five
                        this.yAngle = 5;
                        this.xAngle = 5;
                        this.ballDirectionY = -1;
                    }

                    if ((((ballY - 10) < (rightBarLower - 5)) && ((ballY - 10) >= (rightBarLower - 15)))) {
                        this.yAngle = 6;
                        this.xAngle = 4;
                        this.ballDirectionY = 1;
                    }
                    if (((ballY + 10) > (rightBarHigher + 5)) && ((ballY + 10) <= (rightBarHigher + 15))) {
                        this.yAngle = 6;
                        this.xAngle = 4;
                        this.ballDirectionY = -1;
                    }

                    if (((ballY - 10) < (rightBarLower - 15)) && ((ballY - 10) >= (rightBarLower - 30))) {
                        this.yAngle = 7;
                        this.xAngle = 3;
                        this.ballDirectionY = 1;
                    }                    
                    if (((ballY + 10) > (rightBarHigher + 15)) && ((ballY + 10) <= (rightBarHigher + 30))) {
                        this.yAngle = 7;
                        this.xAngle = 3;
                        this.ballDirectionY = -1;
                    }

                    this.ballDirectionX = -1;
                }
            }
            if (this.ball.getX() >= this.field.getWidth()) {
                resetBall();
                this.rightHeight += 6;
                this.leftScore++;
                }
            if (this.ball.getX() < this.field.getWidth()) {
                this.ball.translate((1 * this.xAngle) + speed, 0);
            }
        }

        // Everything regarding 'up' movement:
        if (this.ballDirectionY == -1) {
            if (this.ball.getY() > 10) {
                this.ball.translate(0, (-1 * this.yAngle) - speed);
            }
            if(this.ball.getY() <= 10) {
                this.ballDirectionY = 1;
            }
        }

        // Everything regarding 'down' movement:
        if (this.ballDirectionY == 1) {
            if (this.ball.getY() < this.field.getHeight() - 10) {
                this.ball.translate(0, (1 * this.yAngle) + speed);
            }
            if (this.ball.getY() >= this.field.getHeight() - 10) {
                this.ballDirectionY = -1;
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
	if (leftScore == 0 && rightScore == 0){
	    return "Let's play some Pong!";
	}
	if(leftScore == rightScore){
	    return"It's a tie!";
}
	if (leftScore < rightScore){
	    if (rightScore == 10){
            if (leftScore == 0) {
                return "Left player is completely crushed!";
            }
    		else return "Right player wins!";
	    }
	    return "Right player's in the lead!";
	}
	if (rightScore < leftScore) {
	    if (leftScore == 10){
            if (rightScore == 0) {
                return "Right player is completely crushed!";
            }
		    else return "Left player wins!";
	    }
	    return "Left player's in the lead!";
		    
	}
	return "";

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
