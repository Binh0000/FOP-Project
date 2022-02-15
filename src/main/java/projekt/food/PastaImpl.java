package projekt.food;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

class PastaImpl extends AbstractSaucable implements Pasta {
	
	static final FoodBuilder<Pasta, Pasta.Config, Food.Variant<Pasta, Pasta.Config>> BUILDER;
	
	static {
		BUILDER = null;
	}
	
	final double thickness;
	
	/**
	 * Constructs an object that is an implementation of the interface Pasta
	 */
	public PastaImpl(BigDecimal price, double weight, 
			Pasta.Variant<? extends Pasta, ? extends Pasta.Config> foodVariant, 
			List<? extends Extra<?>> extras, String sauce, double thickness) {
		super(price, weight, foodVariant, extras, sauce);
		this.thickness = thickness;
		BUILDER.build(null, null, null);
	}

	@Override
	/**
	 * Returns the private variable thickness
	 * @return thickness of pasta noodles
	 */
	public double getThickness() {
		return thickness;
	}
	
	private static class Config extends AbstractSaucable.Config implements Pasta.Config {
		
		private List<DoubleUnaryOperator> thicknessMutators = new ArrayList<>();

		@Override
		/**
		 * 
		 */
		public void thickness(DoubleUnaryOperator op) {
			
			thicknessMutators.add(op);			
		}

		@Override
		/**
		 * 
		 */
		public DoubleUnaryOperator getThicknessMutator() {
			return thicknessMutators.stream()														  
		 			 .reduce((n -> n),
		 					 (op1, op2) -> op1.compose(op2));
		}		
	}
	
	static class Variant<F extends Pasta, C extends Pasta.Config> extends AbstractSaucable.Variant<F, C> implements Pasta.Variant<F, C> {

		@Override
		public double getBaseThickness() {
			return 0;
		}		
	}
}
