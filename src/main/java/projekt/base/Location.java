package projekt.base;

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
	 * 
	 * @param location
	 * @return
	 */
	public Location add(Location location) {
		return new Location(x + location.getX(), y + location.getY());
	}
	
	/**
	 * 
	 * @param location
	 * @return
	 */
	public Location subtract(Location location) {
		return new Location(Math.abs(x - location.getX()), Math.abs(y - location.getY()));
	}
}
