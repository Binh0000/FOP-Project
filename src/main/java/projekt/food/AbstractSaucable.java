package projekt.food;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

public abstract class AbstractSaucable extends AbstractFood implements Saucable {

	private String sauce;	
	
	public AbstractSaucable(BigDecimal price, double weight, Saucable.Variant foodVariant, List<? extends Extra<?>> extras, String sauce) {
		super(price, weight, foodVariant, extras);
		this.sauce = sauce;
	}
	
	/**
	 * Returns the private variable sauce
	 * @return sauce of this food
	 */
	public String getSauce() {
		return sauce;
	}
	
	private static class Config implements Saucable.Config {
		private List<UnaryOperator<BigDecimal>> priceMutators;
		private List<DoubleUnaryOperator> weightMutators;
		private List<UnaryOperator<String>> sauceMutators;

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
		
	}
}
