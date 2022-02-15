package projekt.food;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;

class AbstractFood implements Food{
	private static BigDecimal price;
	private static double weight;
	private static Variant<?, ?> foodVariant;
	private static List<? extends Extra<?>> extras;
	
	public AbstractFood(BigDecimal price, double weight, Variant<?, ?> foodVariant, List<? extends Extra<?>> extras) {
		AbstractFood.price = price;
		AbstractFood.weight = weight;
		AbstractFood.foodVariant = foodVariant;
		AbstractFood.extras = extras;
	}
	
	@Override
	/**
	 * 
	 */
	public BigDecimal getPrice() {
		return price;
	}
	
	@Override
	/**
	 * 
	 */
	public double getWeight() {
		return weight;
	}

	@Override
	/**
	 * 
	 */
	public Variant<?, ?> getFoodVariant() {
		return foodVariant;
	}

	@Override
	/**
	 * 
	 */
	public List<? extends Extra<?>> getExtras() {
		return extras;
	}
	
	static class Config implements Food.Config {
		private List<UnaryOperator<BigDecimal>> priceMutators;
		private List<DoubleUnaryOperator> weightMutators;

		@Override
		/**
		 * 
		 */
		public void price(UnaryOperator<BigDecimal> priceMutator) {
			price = priceMutator.apply(price);
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
			weight = weightMutator.applyAsDouble(weight);
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
