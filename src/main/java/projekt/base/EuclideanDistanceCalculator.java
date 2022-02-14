package projekt.base;

//TODO H1.2
public class EuclideanDistanceCalculator implements DistanceCalculator {
	
	@Override
	/**
	 * 
	 */
	public double calculateDistance(Location l1, Location l2) {
				//sqrt( (x1 - x2)^2 + (y1 - y2)^2)
		return Math.sqrt(Math.pow(l1.getX() - l2.getX(), 2) + Math.pow(l1.getY() - l2.getY(), 2));
	}	
}