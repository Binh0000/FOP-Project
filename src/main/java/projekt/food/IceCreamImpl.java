package projekt.food;

import java.math.BigDecimal;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

public class IceCreamImpl extends AbstractFood implements IceCream {
	
	private String flavor;
	
	/**
	 * Constructs an object that is an implementation of the interface IceCream
	 */
	public IceCreamImpl() {
		super();
	}
	
	@Override
	/**
	 * Returns the private variable flavor
	 * @return flavor of ice cream
	 */
	public String getFlavor() {
		return flavor;
	}
	
	private static class Config implements IceCream.Config {

		@Override
		public void price(UnaryOperator<BigDecimal> priceMutator) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public UnaryOperator<BigDecimal> getPriceMutator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void weight(DoubleUnaryOperator weightMutator) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public DoubleUnaryOperator getWeightMutator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void flavor(UnaryOperator<String> op) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public UnaryOperator<String> getFlavorMutator() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

}
