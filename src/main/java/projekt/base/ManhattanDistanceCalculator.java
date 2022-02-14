package projekt.base;

//TODO H1.2
public class ManhattanDistanceCalculator implements DistanceCalculator{

	@Override
	/**
	 * 
	 */
	public double calculateDistance(Location l1, Location l2) {
		return Math.abs(l1.getX() - l2.getX()) + Math.abs(l1.getY() - l2.getY());
	}

}
