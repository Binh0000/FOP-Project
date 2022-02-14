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
	
	private static class Config implements Pasta.Config {
		private List<UnaryOperator<BigDecimal>> priceMutators;
		private List<DoubleUnaryOperator> weightMutators;
		private List<UnaryOperator<String>> sauceMutators;
		private List<DoubleUnaryOperator> thicknessMutators;

		@Override
		/**
		 * 
		 */
		public void sauce(UnaryOperator<String> op) {
			sauceMutators.add(op);			
		}

		@Override
		/**
		 * 
		 */
		public UnaryOperator<String> getSauceMutator() {
			return sauceMutators.stream()
								.reduce((n -> n), 
										(op1, op2) -> (UnaryOperator<String>) op1.compose(op2));
		}

		@Override
		/**
		 * 
		 */
		public void price(UnaryOperator<BigDecimal> priceMutator) {
			priceMutators.add(priceMutator);
		}

		@Override
		/**
		 * 
		 */
		public UnaryOperator<BigDecimal> getPriceMutator() {			
			return priceMutators.stream()														  
					 			 .reduce((n -> n),
					 					 (op1, op2) -> (UnaryOperator<BigDecimal>) op1.compose(op2));
		}

		@Override
		/**
		 * 
		 */
		public void weight(DoubleUnaryOperator weightMutator) {
			weightMutators.add(weightMutator);
		}

		@Override
		/**
		 * 
		 */
		public DoubleUnaryOperator getWeightMutator() {
			return weightMutators.stream()														  
					 			 .reduce((n -> n),
					 					 (op1, op2) -> op1.compose(op2));
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
