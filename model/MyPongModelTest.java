package model;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Point;

import org.junit.Test;
/**
 * 
 * @author Rebecca Oskarsson and Johan Hullberg
 * Testingclass for the class MyPongModel
 */
public class MyPongModelTest {
MyPongModel testmodel = new MyPongModel("player1", "player2");
BarKey l = BarKey.LEFT;
BarKey r = BarKey.RIGHT;
	@Test
	/**
	 * Tests the method getFieldSize
	 */
	public void getFieldSizeTest() {
		Dimension d = testmodel.getFieldSize();
		Dimension d2 = new Dimension(600,400);
		int i = 0;
		if (d2.equals(d)){
			i++;
		}
		assertEquals(1,i);
	}
	
/**
 * Tests the method getScore
 */
	public void getScoreTest(){
		String scoreL = testmodel.getScore(l);
		String scoreR = testmodel.getScore(r);
		assertEquals("0",scoreL);
		assertEquals("0", scoreR);
	}
/**
 * Tests the methods getMessage and increseScore
 */
	public void getMessageTest(){
		String first = testmodel.getMessage();
		assertEquals("Let's play some pong!", first);
		testmodel.increaseScore(r);
		testmodel.increaseScore(l);	
		String second = testmodel.getMessage();
		assertEquals("It's a tie!", second);
		testmodel.increaseScore(l);		
		String third = testmodel.getMessage();
		assertEquals("leftplayer is in the lead!", third);
		testmodel.increaseScore(r);
		testmodel.increaseScore(r);
		String fourth = testmodel.getMessage();
		assertEquals("rigthplayer is in the lead", fourth);
	}
	/**
	 * Tests the method getBallPos
	 */
	public void getBallPosTest(){
		Point ballPos = testmodel.getBallPos();
		Point equalto = new Point(300,200);
		int i = 0;
		if(equalto.equals(ballPos)){
			i++;
		}
		assertEquals(1,i);
		
	}
	/**
	 * Tests the method getBarHeight
	 */
	public void getBarHeightTest(){
		int barHeightR = testmodel.getBarHeight(r);
		int barHeightL = testmodel.getBarHeight(l);
		assertEquals(barHeightR, 100);
		assertEquals(barHeightL, 100);
		
	}
	/**
	 * Tests the method getBarPos
	 */
	public void getBarPosTest(){
		int barPosR = testmodel.getBarPos(r);
		int barPosL = testmodel.getBarPos(l);
		assertEquals(barPosR, 200);
		assertEquals(barPosL, 200);
		
	}

}
