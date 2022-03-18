package projekt.food;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.UnaryOperator;

//TODO H2.4
class IceCreamImpl extends AbstractFood implements IceCream {
	
	//TODO H2.11
	static final FoodBuilder<IceCreamImpl, Config, Variant<IceCreamImpl, Config>> BUILDER = 
		(Config config, Variant<IceCreamImpl, Config> variant, List<? extends Extra<Config>> extras) -> {			
			Extra.writeToConfig(config, extras);
			return new IceCreamImpl(config.p, config.w, variant, extras, config.f);			
	};
	
	final String flavor;
	
	/**
	 * Constructs an object that is an implementation of the interface IceCream
	 */
	public IceCreamImpl(BigDecimal price, double weight, 
			IceCream.Variant<? extends IceCream, ? extends IceCream.Config> foodVariant, 
			List<? extends Extra<?>> extras, String flavor) {
		super(price, weight, foodVariant, extras);
		this.flavor = flavor;
	}
	
	@Override
	/**
	 * Returns the private variable flavor
	 * 
	 * @return flavor of ice cream
	 */
	public String getFlavor() {
		return flavor;
	}
	
	//TODO H2.5
	private static class Config extends AbstractFood.Config implements IceCream.Config {
		
		String f;
		private List<UnaryOperator<String>> flavorMutators;
		
		/**
		 * Constructs a {@link Config} object 
		 * 
		 * @param p price
		 * @param w weight
		 * @param f flavor
		 */
		Config(BigDecimal p, double w, String f) {
			super(p, w);
			this.f = f;
		}
		
		@Override
        /**
         * Concatenates the result of all previous calls to this method with the provided {@code flavorMutator}.
         *
         * @param flavorMutator A {@link UnaryOperator} which determines a new flavor based on the previous value
         */
		public void flavor(UnaryOperator<String> flavorMutator) {
			f = flavorMutator.apply(f);
			flavorMutators.add(flavorMutator);			
		}

		@Override
        /**
         * The flavor mutator accepts a base flavor and produces a configured flavor.
         *
         * <p>
         * The function returned by this method is the result of concatenating all previous inputs into the
         * {@link #flavor(UnaryOperator)} method.
         * </p>
         *
         * @return The flavor mutation function
         */
		public UnaryOperator<String> getFlavorMutator() {
			return flavorMutators.stream()														  
		 			 .reduce((n -> n),
		 					 (op1, op2) -> (UnaryOperator<String>) op1.compose(op2));
		}
		
	}
	
	//TODO H2.12
	static class Variant<F extends IceCream, C extends IceCream.Config> 
						extends AbstractFood.Variant<F, C> implements IceCream.Variant<F, C> {
		
		String baseFlavor;
		
		/**
		 * Constructs a {@link Variant} of an ice cream with its name and base price, weight and flavor
		 *
		 * @param name The name of this ice cream variant
		 * @param foodType the food type in which this variant is grouped
		 * @param basePrice the base price of this ice cream variant
		 * @param baseWeight the base weight of this ice cream variant
		 * @param baseFlavor the base flavor of this ice cream variant
		 */
		Variant(String name, FoodType<F, C> foodType, BigDecimal basePrice, double baseWeight, String baseFlavor) {
			super(name, foodType, basePrice, baseWeight);
			this.baseFlavor = baseFlavor;
		}
				
		@Override
		/**
		 * The base flavor of this variant
		 * 
		 * @return The base flavor of this variant
		 */
		public String getBaseFlavor() {
			return baseFlavor;
		}
        
        @Override
        /**
         * Creates an empty {@link Config} for this variant.
         *
         * @return An empty {@link Config} for this variant
         */
        public C createEmptyConfig() {
            return (C) new IceCreamImpl.Config(basePrice,baseWeight,baseFlavor);
        }

        @Override
        /**
         * Creates a new instance of {@link Food} described by this variant, its base values and modifications defined by 
         * the provided list of {@link Extra Extras}.
         *
         * @param extras The list of {@link Extra Extras} to configure the resultant {@link Food}
         * @return An instance of {@link Food} based on the values from this variant and configured by the provided extras
         */
        public F create(List<? extends Extra<? super C>> extras) {
            return (F) IceCreamImpl.BUILDER.build((Config) createEmptyConfig(), 
                                                  (Variant<IceCreamImpl, Config>) this, 
                                                  (List<? extends Extra<IceCreamImpl.Config>>) extras);
        }
		
	}

}
