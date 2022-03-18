package projekt.food;

import java.math.BigDecimal;
import java.util.ArrayList;
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
	
	static class Config extends AbstractFood.Config implements Saucable.Config {
		
		String s;
		
		List<UnaryOperator<String>> sauceMutators = new ArrayList<>();;
		
		/**
		 * Constructs a {@link Config} object 
		 * 
		 * @param p price
		 * @param w weight
		 * @param s sauce
		 */
		Config(BigDecimal p, double w, String s) {
			super(p, w);
			this.s = s;
		}	
		
		@Override
        /**
         * Concatenates the result of all previous calls to this method with the provided {@code saucetMutator}.
         *
         * @param sauceMutator A {@link UnaryOperator} which determines a new sauce based on the previous value
         */
		public void sauce(UnaryOperator<String> sauceMutator) {
			s = sauceMutator.apply(s);
			sauceMutators.add(sauceMutator);			
		}

		@Override
        /**
         * The sauce mutator accepts a base sauce and produces a configured sauce.
         *
         * <p>
         * The function returned by this method is the result of concatenating all previous inputs into the
         * {@link #sauce(UnaryOperator)} method.
         * </p>
         *
         * @return The sauce mutation function
         */
		public UnaryOperator<String> getSauceMutator() {
			return sauceMutators.stream()
								.reduce((n -> n), 
										(op1, op2) -> (UnaryOperator<String>) op1.compose(op2));
		}
	}
	
	static class Variant<F extends Saucable, C extends Saucable.Config> 
						extends AbstractFood.Variant<F, C> implements Saucable.Variant<F, C> {
		String baseSauce;
		
		/**
		 * Constructs a {@link Variant} of a food with its name and base price and weight
		 * 
		 * @param name The name of this food variant
		 * @param foodType the food type in which this variant is grouped
		 * @param basePrice the base price of this food variant
		 * @param baseWeight the base weight of this food variant
		 * @param baseSauce the base sauce of this food variant
		 */
		Variant(String name, FoodType<F, C> foodType, BigDecimal basePrice, double baseWeight, String baseSauce) {
			super(name, foodType, basePrice, baseWeight);
			this.baseSauce = baseSauce; 
		}

		@Override
        /**
         * The base sauce of this variant
         *
         * @return The base sauce of this variant
         */
		public String getBaseSauce() {
			return baseSauce;
		}	
	}
}
