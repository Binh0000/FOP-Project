package projekt.food;

import java.math.BigDecimal;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

public class PastaImpl extends AbstractSaucable implements Pasta {
	
	private double thickness;
	
	/**
	 * Constructs an object that is an implementation of the interface Pasta
	 */
	public PastaImpl() {
		super();
	}

	@Override
	/**
	 * Returns the private variable thickness
	 * @return thickness of pasta noodles
	 */
	public double getThickness() {
		return thickness;
	}
	
	private static class Config implements Pasta.Config {

		@Override
		public void sauce(UnaryOperator<String> op) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public UnaryOperator<String> getSauceMutator() {
			// TODO Auto-generated method stub
			return null;
		}

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
		public void thickness(UnaryOperator<Double> op) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public UnaryOperator<Double> getThicknessMutator() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

}
