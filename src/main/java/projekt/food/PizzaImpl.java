package projekt.food;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

public class PizzaImpl extends AbstractSaucable implements Pizza {
	
	private double diameter;
	
	/**
	 * Constructs an object that is an implementation of the interface Pizza
	 */
	public PizzaImpl(BigDecimal price, double weight, Pizza.Variant foodVariant, List<? extends Extra<?>> extras, String sauce ,double diameter) {
		super(price, weight, foodVariant, extras, sauce);
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
	
	private static class Config extends AbstractSauceConfig implements Pizza.Config {
		
		private List<DoubleUnaryOperator> diameterMutators;
		
		private Config() {
			super();
		}

		@Override
		/**
		 * 
		 */
		public void diameter(DoubleUnaryOperator op) {
			diameterMutators.add(op);			
		}

		@Override
		/**
		 * 
		 */
		public DoubleUnaryOperator getDiameterMutator() {
			return diameterMutators.stream()														  
		 			 .reduce((n -> n),
		 					 (op1, op2) -> op1.compose(op2));
		}
		
	}
}
