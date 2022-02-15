package projekt.food;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

class PizzaImpl extends AbstractSaucable implements Pizza {
	
	static final FoodBuilder<Pizza, Pizza.Config, Food.Variant<Pizza, Pizza.Config>> BUILDER;
	
	static {
		BUILDER = (config, variant, extras) -> {
			return null;
		};
	}
	
	final double diameter;
	
	/**
	 * Constructs an object that is an implementation of the interface Pizza
	 */
	public PizzaImpl(BigDecimal price, double weight, 
					 Pizza.Variant<? extends Pizza, ? extends Pizza.Config> foodVariant, 
					 List<? extends Extra<?>> extras, String sauce, double diameter) {
		
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
	
	private static class Config extends AbstractSaucable.Config implements Pizza.Config {
		
		private List<DoubleUnaryOperator> diameterMutators = new ArrayList<>();

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
	
	static class Variant<F extends Pizza,C extends Pizza.Config> extends AbstractSaucable.Variant<F, C> implements Pizza.Variant<F, C> {

		@Override
		public double getBaseDiameter() {
			return 0;
		}		
	}
}
