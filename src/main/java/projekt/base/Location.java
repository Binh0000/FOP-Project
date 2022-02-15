package projekt.base;

//TODO H1.1
public class Location {
	private final int x;
	private final int y;
	
	/**
	 * Constructs a new Location object 
	 * @param x initializes private constant x
	 * @param y initializes private constant y
	 */
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets the value of the private constant x
	 * @return value of x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gets the value of the private constant y
	 * @return value of y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Adds this location's x and y coordinates to another location's coordinates
	 * and return them in a new Location object
	 * 
	 * @param location location to add
	 * @return Location containing added coordinates
	 */
	public Location add(Location location) {
		return new Location(x + location.getX(), y + location.getY());
	}
	
	/**
	 * Subtracts this location's x and y coordinates with another location's coordinates
	 * and return the absolute value of the result in a new Location object
	 * 
	 * @param location location to subtract
	 * @return Location containing result
	 */
	public Location subtract(Location location) {
		return new Location(Math.abs(x - location.getX()), Math.abs(y - location.getY()));
	}
}
