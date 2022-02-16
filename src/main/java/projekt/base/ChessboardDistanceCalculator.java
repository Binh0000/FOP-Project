package projekt.base;

//TODO H1.2
public class ChessboardDistanceCalculator implements DistanceCalculator {
	
	@Override
	/**
	 * Calculates the chessboard distance between 2 locations
	 * @param l1 location 1
	 * @param l2 location 2
	 * @return distance between 2 locations
	 */
	public double calculateDistance(Location l1, Location l2) {
		return Math.max(Math.abs(l1.getX() - l2.getX()), Math.abs(l1.getY() - l2.getY()));
	}	
}
