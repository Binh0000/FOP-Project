package projekt.food;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

public class PastaImpl extends AbstractSaucable implements Pasta {
	
	private double thickness;
	
	/**
	 * Constructs an object that is an implementation of the interface Pasta
	 */
	public PastaImpl(BigDecimal price, double weight, Pasta.Variant foodVariant, List<? extends Extra<?>> extras, String sauce, double thickness) {
		super(price, weight, foodVariant, extras, sauce);
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
	
	private static class Config extends AbstractSauceConfig implements Pasta.Config {
		
		private List<DoubleUnaryOperator> thicknessMutators;
		
		private Config() {
			super();
		}

		@Override
		public void thickness(DoubleUnaryOperator op) {
			thicknessMutators.add(op);			
		}

		@Override
		public DoubleUnaryOperator getThicknessMutator() {
			return thicknessMutators.stream()														  
		 			 .reduce((n -> n),
		 					 (op1, op2) -> op1.compose(op2));
		}
		
	}

}
