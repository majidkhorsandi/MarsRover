package classes;

/**
 * This enum is written to avoid hard coding directions in Rover class.
 * @author majid
 *
 */
public enum Direction {
	NORTH("N") {@Override public Direction turnRight() {return EAST;}
				@Override public Direction turnLeft() {return WEST;}},
	EAST("E")  {@Override public Direction turnRight() {return SOUTH;}
				@Override public Direction turnLeft() {return NORTH;}},
	SOUTH("S") {@Override public Direction turnRight() {return WEST;}
				@Override public Direction turnLeft() {return EAST;}},
	WEST("W")  {@Override public Direction turnRight() {return NORTH;}
				@Override public Direction turnLeft() {return SOUTH;}};

			public abstract Direction turnRight();
			public abstract Direction turnLeft();
			private final String symbol;
			Direction(String symbol){this.symbol = symbol;}
			@Override public String toString(){return this.symbol;}
}