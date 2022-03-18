package projekt.food;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

class PastaImpl extends AbstractSaucable implements Pasta {
	
	//TODO H2.11
	static final FoodBuilder<PastaImpl, Config, Variant<PastaImpl, Config>> BUILDER =
		(Config config, Variant<PastaImpl, Config> variant, List<? extends Extra<Config>> extras) -> {
			Extra.writeToConfig(config, extras);
			return new PastaImpl(config.p, config.w, variant, extras, config.s, config.t);
	};

	final double thickness;
	
	/**
	 * Constructs a new {@link PastaImpl} representing a pizza with its parameters as attributes of the pasta
	 * 
	 * @param price price of this pasta
	 * @param weight weight of this pasta
	 * @param foodVariant variant of this pasta
	 * @param extras list of extras of this pasta
	 * @param sauce sauce of this pasta
	 * @param thickness noodles thickness of this pasta
	 */
	public PastaImpl(BigDecimal price, double weight, 
			Pasta.Variant<? extends Pasta, ? extends Pasta.Config> foodVariant, 
			List<? extends Extra<?>> extras, String sauce, double thickness) {
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
	
	//TODO H2.5
	private static class Config extends AbstractSaucable.Config implements Pasta.Config {
		
		double t;
		
		private List<DoubleUnaryOperator> thicknessMutators = new ArrayList<>();
		
		/**
		 * Constructs a {@link Config} object 
		 * 
		 * @param p price
		 * @param w weight
		 * @param s sauce
		 * @param t thickness
		 */
		Config(BigDecimal p, double w, String s, double t) {			
			super(p, w, s);
			this.t = t;
		}

		@Override
        /**
         * Concatenates the result of all previous calls to this method with the provided {@code thicknessMutator}.
         *
         * @param thicknessMutator A {@link DoubleUnaryOperator} which determines a new thickness based on the previous value
         */
		public void thickness(DoubleUnaryOperator thicknessMutator) {
			t = thicknessMutator.applyAsDouble(t);
			thicknessMutators.add(thicknessMutator);			
		}

		@Override
        /**
         * The thickness mutator accepts a base thickness and produces a configured thickness.
         *
         * <p>
         * The function returned by this method is the result of concatenating all previous inputs into the
         * {@link #thickness(DoubleUnaryOperator)} method.
         * </p>
         *
         * @return The thickness mutation function
         */
		public DoubleUnaryOperator getThicknessMutator() {
			return thicknessMutators.stream()														  
		 			 .reduce((n -> n),
		 					 (op1, op2) -> op1.compose(op2));
		}		
	}
	
	//TODO H2.12
	static class Variant<F extends Pasta, C extends Pasta.Config> extends AbstractSaucable.Variant<F, C> implements Pasta.Variant<F, C> {
		
		double baseThickness;
		
		/**
		 * Constructs a {@link Variant} of a pasta with its name and base price, weight, sauce and thickness
		 *
		 * @param name The name of this pasta variant
		 * @param foodType the food type in which this variant is grouped
		 * @param basePrice the base price of this pasta variant
		 * @param baseWeight the base weight of this pasta variant
		 * @param baseSauce the base sauce of this pasta variant
		 * @param baseThickness the base thickness of this pasta variant
		 */
		Variant(String name, FoodType<F, C> foodType, BigDecimal basePrice, double baseWeight, String baseSauce, double baseThickness) {
			super(name, foodType, basePrice, baseWeight, baseSauce);
			this.baseThickness = baseThickness;
		}

		@Override
		/**
		 * The base thickness of variant noodles
		 * 
		 * @return The base thickness of variant noodles
		 */
		public double getBaseThickness() {
			return baseThickness;
		}		
        
        @Override
        /**
         * Creates an empty {@link Config} for this variant.
         *
         * @return An empty {@link Config} for this variant
         */
        public C createEmptyConfig() {
            return (C) new Config(basePrice,baseWeight,baseSauce,baseThickness);
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
            return (F) PastaImpl.BUILDER.build((Config) createEmptyConfig(), 
                                               (Variant<PastaImpl, Config>) this, 
                                               (List<? extends Extra<Config>>) extras);
        }
	}
}
