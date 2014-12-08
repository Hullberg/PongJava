package model;

import java.util.Set;
import java.util.Random;
import java.awt.Point;
import java.awt.Dimension;

/**
 * @details [The field, the ball, the BarKeys, the scores of both players, the name of said players, where the ball is heading and the speed it has.]
 * 
 */
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

    private int speed;
    private int barKeySpeed;

/**
 * The PongModel keeps track of the bars, the ball and the game state.
 */
/**
 * @details [Creates and resets the ball, moves the BarKeys and keeps score of the game.]
 * 
 * @param s1 [Name of left player]
 * @param s2 [Name of right player]
 * 
 * @return [description]
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

        this.speed = 0;
        this.barKeySpeed = 0;
	   

    }

    /**
     * @brief [Sets the ball in the middle of the field, with a randomized angle and direction]
     */
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

    this.speed = 0;
    this.barKeySpeed = 0;
    
}
    /**
     * Takes the inputs and applies them to the model, computing one
     * simulation step. delta_t is the time that has passed since the
     * last compute step -- use this in your time integration to have
     * the items move at the same speed, regardless of the framerate.
     */
    /**
     * @details [Computes where the barkeys are and the position of the ball. Before that it checks if someone has won the game, if so it resets.]
     * 
     * @param input [Input from the keyboard]
     * @param delta_t [No idea]
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
                        this.leftPos = this.leftPos - 10 - this.barKeySpeed;
                        }
                        break;
                    case DOWN:
                        if((this.leftPos + (this.leftHeight/2)) <= this.field.height){
                        this.leftPos = this.leftPos + 10 + this.barKeySpeed;
                    }   
                        break;
                    }
                    break;
	        case RIGHT:
                    switch(i.dir) {
                    case UP:
                        if((this.rightPos - (this.rightHeight/2)) >= 0){
                        this.rightPos = this.rightPos - 10 - this.barKeySpeed;
                    }
                        break;
                    case DOWN:
                        if((this.rightPos + (this.rightHeight/2)) <= this.field.height)
                        this.rightPos = this.rightPos + 10 + this.barKeySpeed;
                        break;
                    }
            	break;       
	    }
        }

    	moveBall();	


    }
    
    /**
     * @details [Increases a speedup counter after each time the ball hits a BarKey, and moves the ball according to the current angle it is moving.]
     */
    public void moveBall() {
        double ballY = this.ball.getY();
        int leftBarLower = this.leftPos + this.leftHeight/2;
        int leftBarHigher = this.leftPos - this.leftHeight/2;

        double ballX = this.ball.getX();
        int rightBarLower = this.rightPos + this.rightHeight/2;
        int rightBarHigher = this.rightPos - this.rightHeight/2;

        // Everything regarding left movement:
        if (this.ballDirectionX == -1) {
            if ((this.ball.getX() <= (-1 * ((-1 * this.xAngle) - this.speed))) && (this.ball.getX() > 10)) {
                this.ball.setLocation(5, this.ball.getY());
            }
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
		            if(this.speed < 10){
                        this.speed += 1;
                        this.barKeySpeed += 1;
                    }
                    this.ballDirectionX = 1;
                }
            }
            if (this.ball.getX() <= 0) {
                    resetBall();
                    this.leftHeight += 4;
                    this.rightScore++;
                }
            if (this.ball.getX() > 0) {
                this.ball.translate((-1 * this.xAngle) - this.speed, 0);
            }            
        }   
        
        // Everything regarding right movement:
        if (this.ballDirectionX == 1) {
            if ((this.ball.getX() >= (this.field.getWidth() - ((1 * this.xAngle) + this.speed))) && (this.ball.getX() < (this.field.getWidth() - 10))) {
                this.ball.setLocation(this.field.getWidth() - 5, this.ball.getY());
            }
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
                    if (this.speed < 10) {
                        this.speed += 1;
                        this.barKeySpeed += 1;
                    }
                    this.ballDirectionX = -1;
                }
            }
            if (this.ball.getX() >= this.field.getWidth()) {
                resetBall();
                this.rightHeight += 4;
                this.leftScore++;
                }
            if (this.ball.getX() < this.field.getWidth()) {
                this.ball.translate((1 * this.xAngle) + this.speed, 0);
            }
        }

        // Everything regarding 'up' movement:
        if (this.ballDirectionY == -1) {
            if (this.ball.getY() > 10) {
                this.ball.translate(0, (-1 * this.yAngle) - this.speed);
            }
            if(this.ball.getY() <= 10) {
                this.ballDirectionY = 1;
            }
        }

        // Everything regarding 'down' movement:
        if (this.ballDirectionY == 1) {
            if (this.ball.getY() < this.field.getHeight() - 10) {
                this.ball.translate(0, (1 * this.yAngle) + this.speed);
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
    /**
     * @brief [The current location of the BarKey, how close to the top or bottom it is]
     * 
     * @param k [Left or Right BarKey]
     * @return [Where the BarKey is at the moment]
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

    /**
     * @brief [The height of a BarKey]
     * 
     * @param k [Left or Right BarKey]
     * @return [The height of said BarKey]
     */
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

    /**
     * @brief [Returns x- and y-values of the ball in the field]
     * @return [The coordinates of the ball in the field]
     */
	public Point getBallPos() {
        return this.ball.getLocation();
    }

    /**
     * Will output information about the state of the game to be
     * displayed to the players
     */
    /**
     * @brief [Different messages at different scores.]
     * @return [A string in the top middle, with different message depending on the current state of the game]
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
                return "" + this.s1 + " was completely crushed!";
            }
    		else return "" + this.s2 + " wins!";
	    }
	    return "" + this.s2 + " is in the lead!";
	}
	if (rightScore < leftScore) {
	    if (leftScore == 10){
            if (rightScore == 0) {
                return "" + this.s2 + " was completely crushed!";
            }
		    else return "" + this.s1 + " wins!";
	    }
	    return "" + this.s1 + " is in the lead!";
		    
	}
	return "";

    }

    /**
     * getter for the scores.
     */
    /**
     * @brief [Retrieves the integer representing the score of a player, and turns it into a string for display.]
     * 
     * @param k [Right or Left BarKey]
     * @return [A string representing the score, between 0 and 10.]
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
    
    /**
     * a valid implementation of the model will keep the field size
     * will remain constant forever.
     */
    /**
     * @brief [Returns the x and y values of the gaming field]
     * @return [X- and Y-values in the type of Dimension]
     */
	public Dimension getFieldSize() {
        return this.field.getSize();
    }
	
}
