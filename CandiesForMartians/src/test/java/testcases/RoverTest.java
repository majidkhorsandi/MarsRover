/**
 * 
 */
package testcases;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import classes.Rover;

/**
 * @author majid
 *
 */
public class RoverTest {

	private Rover testRover;
	private final static int MAX_X = Rover.getMaximumX();
	private final static int MAX_Y = Rover.getMaximumY();
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testRover = Rover.launch("00W");
	}

	/**
	 * this method tests Launch method of Rover. We want to make sure after 
	 * launch rover is located where it is said to
	 */
	public final void testLaunch(){
		//Rover already launched in setUp. an empty move will give position.
		assertEquals("00W", testRover.move(""));
	}
	
	/**
	 * this method tests logic inside Direction enum
	 */
	@Test
	public final void testDirection(){
		assertEquals("00S" , testRover.move("L"));
		assertEquals("00E" , testRover.move("L"));
		assertEquals("00N" , testRover.move("L"));
		assertEquals("00W" , testRover.move("L"));
		assertEquals("00N" , testRover.move("R"));
		assertEquals("00E" , testRover.move("R"));
		assertEquals("00S" , testRover.move("R"));
		assertEquals("00W" , testRover.move("R"));
	}

	/**
	 * Test method for {@link classes.Rover#move(java.lang.String)}.
	 */
	@Test
	public final void testMoveInBoundries() throws Exception {
		StringBuilder horizentalMovementBuilder = new StringBuilder(MAX_X + 2);
		StringBuilder verticalMovementBuilder = new StringBuilder(MAX_Y + 2);
		for (int i=0; i<= MAX_X + 1; i++) horizentalMovementBuilder.append("M");
		for (int i=0; i<= MAX_Y + 1; i++) verticalMovementBuilder.append("M");
		
		//test a move that should not cross eastern border of plateau
		assertEquals("00W" , testRover.move("mmm"));
		//test turn right and a move that should not cross western border
		assertEquals(MAX_X + "0E" , testRover.move("Rr" + 
				horizentalMovementBuilder.toString()));
		//test turn left and a move that should not cross northern border
		assertEquals(Integer.toString(MAX_X) + Integer.toString(MAX_Y) + "N" , 
				testRover.move("L" + verticalMovementBuilder.toString()));
		//test a move that should not cross southern border
		assertEquals(Integer.toString(MAX_X) + "0S" , testRover.move("LL" + 
				verticalMovementBuilder.toString()));
	}
	
	/**
	 * this method tests if an illegal argument exception is thrown if an 
	 * invalid landing position is sent to rover.
	 * @throws Exception
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testIllegalLandingPosition() throws Exception{
		StringBuilder illegalPositionStringBuilder = new StringBuilder();
		illegalPositionStringBuilder.append(MAX_X + 1);
		illegalPositionStringBuilder.append(MAX_Y + 1);
		illegalPositionStringBuilder.append("R");
		Rover.launch(illegalPositionStringBuilder.toString());
	}
	
	/**
	 * this method tests if an illegal argument exception is thrown if an 
	 * invalid movement is sent to rover.
	 * @throws Exception
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testIllegalMovement() throws Exception{
		testRover.move("III");
	}
	
	/**
	 * this method tests if the output string is valid using regular expression
	 * matching
	 */
	@Test
	public final void testOutput() throws Exception{
		String outputRegx = "[0-" + Rover.getMaximumX() + "]{1}[0-" + 
				Rover.getMaximumY()  + "]{1}[NnSsWwEe]";
		assertTrue(testRover.move("LLRRMMMMLMMMMRMRMRMLL").matches(outputRegx));
	}
	
}
