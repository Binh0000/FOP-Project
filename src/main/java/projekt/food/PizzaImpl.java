package projekt.food;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

class PizzaImpl extends AbstractSaucable implements Pizza {
	
	//TODO H2.11
	static final FoodBuilder<PizzaImpl, Config, Variant<PizzaImpl, Config>> BUILDER = 
		(Config config, Variant<PizzaImpl, Config> variant, List<? extends Extra<Config>> extras) -> {			
			Extra.writeToConfig(config, extras);
			return new PizzaImpl(config.p, config.w, variant, extras, config.s, config.d);			
	};
	
	final double diameter;
	
	/**
	 * Constructs a new {@link PizzaImpl} representing a pizza with its parameters as attributes of the pizza
	 * 
	 * @param price price of this pizza
	 * @param weight weight of this pizza
	 * @param foodVariant variant of this pizza
	 * @param extras list of extras of this pizza
	 * @param sauce sauce of this pizza
	 * @param diameter diameter of this pizza
	 */
	public PizzaImpl(BigDecimal price, double weight, 
					 Pizza.Variant<? extends Pizza, ? extends Pizza.Config> foodVariant, 
					 List<? extends Extra<?>> extras, String sauce, double diameter) {
		
		super(price, weight, foodVariant, extras, sauce);
		this.diameter = diameter;
	}

	@Override
	/**
	 * Returns the diameter of this pizza
	 * 
	 * @return diameter of pizza
	 */
	public double getDiameter() {
		return diameter;
	}
	
	//TODO H2.5
	/**
	 * Extending class of {@link AbstractSaucable.Config}
	 */
	private static class Config extends AbstractSaucable.Config implements Pizza.Config {
		
		double d;
		
		private List<DoubleUnaryOperator> diameterMutators = new ArrayList<>();
		
		/**
		 * Constructs a {@link Config} object 
		 * 
		 * @param p price
		 * @param w weight
		 * @param s sauce
		 * @param d diameter
		 */
		Config(BigDecimal p, double w, String s, double d) {
			super(p, w, s);
			this.d = d;
		}
		
		@Override
        /**
         * Concatenates the result of all previous calls to this method with the provided {@code diameterMutator}.
         *
         * @param diameterMutator A {@link DoubleUnaryOperator} which determines a new diameter based on the previous value
         */
		public void diameter(DoubleUnaryOperator diameterMutator) {
			d = diameterMutator.applyAsDouble(d);
			diameterMutators.add(diameterMutator);			
		}

        /**
         * The diameter mutator accepts a base diameter and produces a configured diameter.
         *
         * <p>
         * The function returned by this method is the result of concatenating all previous inputs into the
         * {@link #diameter(DoubleUnaryOperator)}  method.
         * </p>
         *
         * @return The diameter mutation function
         */
		public DoubleUnaryOperator getDiameterMutator() {
			return diameterMutators.stream()														  
		 			 .reduce((n -> n),
		 					 (op1, op2) -> op1.compose(op2));
		}		
	}
	
	//TODO H2.12
	/**
	 * Extending class of {@link AbstractSaucable.Variant}
	 *
     * @param <F> The target {@link Pizza} type
     * @param <C> The target {@link Pizza.Config} type
	 */
	static class Variant<F extends Pizza, C extends Pizza.Config> extends AbstractSaucable.Variant<F, C> implements Pizza.Variant<F, C> {

		double baseDiameter;
		
		/**
		 * Constructs a {@link Variant} of a pizza with its name and base price, weight, sauce and diameter
		 * 
		 * @param name The name of this pizza variant
		 * @param foodType the food type in which this variant is grouped
		 * @param basePrice the base price of this pizza variant
		 * @param baseWeight the base weight of this pizza variant
		 * @param baseSauce the base sauce of this pizza variant
		 * @param baseDiameter the base diameter of this pizza variant
		 */
		Variant(String name, FoodType<F, C> foodType, BigDecimal basePrice, double baseWeight, String baseSauce, double baseDiameter) {
			super(name, foodType, basePrice, baseWeight, baseSauce);
			this.baseDiameter = baseDiameter;
		}

		@Override
		/**
		 * The base diameter of this variant
		 * 
		 * @return The base diameter of this variant
		 */
		public double getBaseDiameter() {
			return baseDiameter;
		}	
		
        @SuppressWarnings("unchecked")
		@Override
        /**
         * Creates an empty {@link Config} for this variant.
         *
         * @return An empty {@link Config} for this variant
         */
        public C createEmptyConfig() {
            return (C) new PizzaImpl.Config(basePrice, baseWeight, baseSauce, baseDiameter);
        }
        
        @SuppressWarnings("unchecked")
		@Override
        /**
         * Creates a new instance of {@link Food} described by this variant, its base values and modifications defined by 
         * the provided list of {@link Extra Extras}.
         *
         * @param extras The list of {@link Extra Extras} to configure the resultant {@link Food}
         * @return An instance of {@link Food} based on the values from this variant and configured by the provided extras
         */        
        public F create(List<? extends Extra<? super C>> extras) {
            return (F) PizzaImpl.BUILDER.build((Config) createEmptyConfig(), 
                                               (Variant<PizzaImpl, Config>) this, 
                                               (List<? extends Extra<PizzaImpl.Config>>) extras);
        }
	}
}
