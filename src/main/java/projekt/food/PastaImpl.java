package projekt.food;

public class PastaImpl extends AbstractSaucable implements Pasta {
	private double thickness;
	
	/**
	 * Constructs an object that is an implementation of the interface Pasta
	 * @param thickness thickness of pasta noodles
	 */
	public PastaImpl(double thickness) {
		super();
		this.thickness = thickness;
	}

	@Override
	/**
	 * Returns the private variable thickness
	 * @return thickness of pasta noodles
	 */
	public double getThickness() {
		return thickness;
	}

}
