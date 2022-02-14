package projekt.food;

import java.math.BigDecimal;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

public class PizzaImpl extends AbstractSaucable implements Pizza {
	
	private double diameter;
	
	/**
	 * Constructs an object that is an implementation of the interface Pizza
	 */
	public PizzaImpl(double diameter) {
		super();
	}

	@Override
	/**
	 * Returns the private variable diameter
	 * @return diameter of pizza
	 */
	public double getDiameter() {
		return diameter;
	}
	
	private static class Config implements Pizza.Config {

		@Override
		public void sauce(UnaryOperator<String> op) {
			
		}

		@Override
		public UnaryOperator<String> getSauceMutator() {
			return null;
		}

		@Override
		public void price(UnaryOperator<BigDecimal> priceMutator) {
			
		}

		@Override
		public UnaryOperator<BigDecimal> getPriceMutator() {
			return null;
		}

		@Override
		public void weight(DoubleUnaryOperator weightMutator) {
			
		}

		@Override
		public DoubleUnaryOperator getWeightMutator() {
			return null;
		}

		@Override
		public void diameter(UnaryOperator<Double> op) {
			
		}

		@Override
		public UnaryOperator<Double> getDiameterMutator() {
			return null;
		}
		
	}
}
