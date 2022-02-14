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
		private List<UnaryOperator<BigDecimal>> priceMutators;
		private List<DoubleUnaryOperator> weightMutators;
		private List<UnaryOperator<String>> sauceMutators;
		private List<DoubleUnaryOperator> diameterMutators;

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
		public void diameter(DoubleUnaryOperator op) {
			diameterMutators.add(op);			
		}

		@Override
		public DoubleUnaryOperator getDiameterMutator() {
			return diameterMutators.stream()														  
		 			 .reduce((n -> n),
		 					 (op1, op2) -> op1.compose(op2));
		}
		
	}
}
