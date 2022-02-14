package projekt.food;

public class PizzaImpl extends AbstractSaucable implements Pizza {
	private double diameter;
	
	/**
	 * Constructs an object that is an implementation of the interface Pizza
	 * @param diameter diameter of pizza
	 */
	public PizzaImpl(double diameter) {
		super();
		this.diameter = diameter;
	}

	@Override
	/**
	 * Returns the private variable diameter
	 * @return diameter of pizza
	 */
	public double getDiameter() {
		return diameter;
	}
	
}
