package projekt.food;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.UnaryOperator;

abstract class AbstractSaucable extends AbstractFood implements Saucable {

	final String sauce;	
	
	/**
	 * Constructs an object that is an implementation of {@link Saucable}
	 * 
	 * @param price price of this food
	 * @param weight weight of this food
	 * @param foodVariant variant of this food
	 * @param extras extras compatible with this food
	 * @param sauce sauce of this food
	 */
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
	
	static abstract class Config extends AbstractFood.Config implements Saucable.Config {
		
		String s;
		
		List<UnaryOperator<String>> sauceMutators;
		
		Config(BigDecimal p, double w, String s) {
			super(p, w);
			this.s = s;
		}	
		
		@Override
		/**
		 * 
		 */
		public void sauce(UnaryOperator<String> sauceMutator) {
			s = sauceMutator.apply(s);
			sauceMutators.add(sauceMutator);			
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
	
	static abstract class Variant<F extends Saucable, C extends Saucable.Config> 
						extends AbstractFood.Variant<F, C> implements Saucable.Variant<F, C> {
		String baseSauce;
		
		/**
		 * 
		 * @param name
		 * @param foodType
		 * @param basePrice
		 * @param baseWeight
		 * @param baseSauce
		 */
		Variant(String name, FoodType<F, C> foodType, BigDecimal basePrice, double baseWeight, String baseSauce) {
			super(name, foodType, basePrice, baseWeight);
			this.baseSauce = baseSauce; 
		}

		@Override
		public String getBaseSauce() {
			return baseSauce;
		}
	}
}
