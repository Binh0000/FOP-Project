package projekt.food;

public class IceCreamImpl extends AbstractFood implements IceCream {
	private String flavor;
	
	/**
	 * Constructs an object that is an implementation of the interface IceCream
	 * @param flavor flavor of ice cream
	 */
	public IceCreamImpl(String flavor) {
		super();
		this.flavor = flavor;
	}
	
	@Override
	/**
	 * Returns the private variable flavor
	 * @return flavor of ice cream
	 */
	public String getFlavor() {
		return flavor;
	}

}
