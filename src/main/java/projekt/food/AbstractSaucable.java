package projekt.food;

public abstract class AbstractSaucable extends AbstractFood implements Saucable {
	private String sauce;
	
	/**
	 * Returns the private variable sauce
	 * @return sauce of this food
	 */
	public String getSauce() {
		return sauce;
	}
}
