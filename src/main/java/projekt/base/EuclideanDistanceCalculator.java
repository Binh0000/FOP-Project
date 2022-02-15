package projekt.base;

//TODO H1.2
public class EuclideanDistanceCalculator implements DistanceCalculator {
	
	@Override
	/**
	 * Calculates the Euclidean distance between 2 locations
	 * @param l1 location 1
	 * @param l2 location 2
	 * @return distance between 2 locations
	 */
	public double calculateDistance(Location l1, Location l2) {
		return Math.sqrt(Math.pow(l1.getX() - l2.getX(), 2) + Math.pow(l1.getY() - l2.getY(), 2));
	}	
}