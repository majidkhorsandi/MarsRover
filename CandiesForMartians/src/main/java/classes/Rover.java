package classes;



public class Rover{
	// define borders for the Rover. Since board is square we could define 1 
	// constant but defining both x and y is more readable and easier to change
	// if board is rectangle.
	private static final int MAX_X = 9;
	private static final int MAX_Y = 9;
	
	private int x;
	private int y;
	private Direction currentDirection;
	
	//private constructor
	private Rover(int x, int y, Direction landingDirection){
		this.x = x;
		this.y = y; 
		this.currentDirection = landingDirection;
	}
	/**
	 * Gets landing position as string and does a sanity check on it. If sanity
	 *  check passes it parses the string and extracs (x,y) 
	 * coordinates and the landing direction. Then instantiates a rover with 
	 * extracted information.
	 * @param whereToSit and string showing position and direction of Rover
	 * @return and instance of Rover landed on whereToSit position.
	 * @throws Exception if landing position string is invalid
	 */
	public static Rover launch(String whereToSit) throws Exception{
		if (sanityCheckLandingPosition(whereToSit)){
			return parseLocationAndInstantiateRover(whereToSit.toLowerCase());
		} else throw new IllegalArgumentException("Provided landing location "
				+ "could be found on the plateau.\n Correct landing position "
				+ "string should be in form [0-" + MAX_X + "][0-" + MAX_Y + 
				"]N|n|S|s|W|w|E|e");
	}
	
	/**
	 * Does a sanity check on movement string, if it is valid, moves the rover
	 * based on movement string and returns the position after move. If movement
	 *  string is empty, it returns current position of rover.
	 * @param movement
	 * @return current position of rover after move
	 */
	public String move(String movement){
		if (!sanityCheckMovementString(movement)){
			throw new IllegalArgumentException("Invalid movement string.A valid"
					+ " movement string can only contain characters 'R' 'L' 'M'");
		} else if (movement.isEmpty()){
			return new StringBuilder().append(x).
					append(y).append(currentDirection.toString()).toString();
		}
			
		StringBuilder currentPosition = new StringBuilder();
		movement = movement.toLowerCase();
		for(char ch : movement.toCharArray()){
			if (ch == 'l'){
				currentDirection = currentDirection.turnLeft();
			}else if (ch == 'r') {
				currentDirection = currentDirection.turnRight();
			} else {
				switch (currentDirection) {
				case NORTH :
					if (y < MAX_Y) y = y+1 ;
					break;
				case SOUTH :
					if (y > 0) y = y-1;
					break;
				case WEST :
					if (x > 0) x = x-1;
					break;
				case EAST :
					if (x < MAX_X) x = x+1;
					break;
				default :
					break;
				}
			}
		}
		currentPosition.append(x);
		currentPosition.append(y);
		currentPosition.append(currentDirection.toString());
		return currentPosition.toString().toUpperCase();
	}
	
	/**
	 * sanity check of landingPosition using regular expression
	 * @param landingPosition
	 * @return true if landingPosition is valid and false otherwise
	 */
	private static boolean sanityCheckLandingPosition(String landingPosition){
		return landingPosition.matches("[0-" + MAX_X + "]{1}[0-" + MAX_Y + 
				"]{1}[NnSsWwEe]");
	}
	
	/**
	 * sanity check of movement string using regular expressions. 
	 * @param movement
	 * @return true if movement is valid and false otherwise
	 */
	private static boolean sanityCheckMovementString(String movement){
		return movement.matches("[LlRrMm]*");
	}
	
	/**
	 * parses location string and returns an instance of rover based on extraced
	 * information.
	 * @param location is a valid location string
	 * @return rover instance.
	 */
	private static Rover parseLocationAndInstantiateRover(String location){
		final int x = Character.getNumericValue(location.charAt(0));
		final int y = Character.getNumericValue(location.charAt(1));
		final Direction landingDirection;
		switch (location.charAt(2)){
		case 'n' : 
			landingDirection = Direction.NORTH;
			break;
		case 's' : 
			landingDirection = Direction.SOUTH;
			break;
		case 'w' :
			landingDirection = Direction.WEST;
			break;
		case 'e' :
			landingDirection = Direction.EAST;
			break;
		default :
			throw new IllegalArgumentException("Landing Position does not "
					+ "contain direction");
		}
		return new Rover(x,y, landingDirection);
	}
	
	public static int getMaximumX(){
		return MAX_X;
	}
	
	public static int getMaximumY(){
		return MAX_Y;
	}
	
	

}
