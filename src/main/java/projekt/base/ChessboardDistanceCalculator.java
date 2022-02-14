package projekt.base;

public class ChessboardDistanceCalculator implements DistanceCalculator {

	@Override
	/**
	 * 
	 */
	public double calculateDistance(Location l1, Location l2) {
		return Math.max(Math.abs(l1.getX() - l2.getX()), Math.abs(l1.getY() - l2.getY()));
	}
	
}
