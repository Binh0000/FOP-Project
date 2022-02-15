package projekt.food;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

import projekt.food.AbstractFood.Config;
import projekt.food.Food.Variant;

abstract class AbstractSaucable extends AbstractFood implements Saucable {

	final String sauce;	
	
	public AbstractSaucable(BigDecimal price, double weight, 
			Saucable.Variant<? extends Saucable, ? extends Saucable.Config> foodVariant, 
			List<? extends Extra<?>> extras, String sauce) {
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
	
	static class Config extends AbstractFood.Config implements Saucable.Config {
		
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
	}
	
	static class Variant<F extends Saucable, C extends Saucable.Config> extends AbstractFood.Variant<F, C> implements Saucable.Variant<F, C> {

		@Override
		public String getBaseSauce() {
			return null;
		}	
	}
}
