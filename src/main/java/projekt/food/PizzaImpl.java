package projekt.food;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

class PizzaImpl extends AbstractSaucable implements Pizza {
	
	//TODO H2.11
	static final FoodBuilder<PizzaImpl, Config, Variant<PizzaImpl, Config>> BUILDER = 
		(Config config, Variant<PizzaImpl, Config> variant, List<? extends Extra<Config>> extras) -> {			
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
	 * @return diameter of pizza
	 */
	public double getDiameter() {
		return diameter;
	}
	
	//TODO H2.5
	private static class Config extends AbstractSaucable.Config implements Pizza.Config {
		
		double d;
		double w = super.w;
		
		private List<DoubleUnaryOperator> diameterMutators = new ArrayList<>();
		
		/**
		 * Constructs 
		 * 
		 * @param p
		 * @param w
		 * @param s
		 * @param d
		 */
		Config(BigDecimal p, double w, String s, double d) {
			super(p, w, s);
			this.d = d;
		}
		
		@Override
		/**
		 * 
		 */
		public void diameter(DoubleUnaryOperator diameterMutator) {
			d = diameterMutator.applyAsDouble(d);
			diameterMutators.add(diameterMutator);			
		}

		@Override
		/**
		 * 
		 */
		public DoubleUnaryOperator getDiameterMutator() {
			return diameterMutators.stream()														  
		 			 .reduce((n -> n),
		 					 (op1, op2) -> op1.compose(op2));
		}		
	}
	
	//TODO H2.12
	static class Variant<F extends Pizza,C extends Pizza.Config> extends AbstractSaucable.Variant<F, C> implements Pizza.Variant<F, C> {

		double baseDiameter;
		
		/**
		 * 
		 * @param name
		 * @param foodType
		 * @param basePrice
		 * @param baseWeight
		 * @param baseSauce
		 * @param baseDiameter
		 */
		Variant(String name, FoodType<F, C> foodType, BigDecimal basePrice, double baseWeight, String baseSauce, double baseDiameter) {
			super(name, foodType, basePrice, baseWeight, baseSauce);
			this.baseDiameter = baseDiameter;
		}

		@Override
		/**
		 * 
		 */
		public double getBaseDiameter() {
			return baseDiameter;
		}		
        /**
         * 
         * @return
         */
        @Override
        public C createEmptyConfig() {
            return (C) new PizzaImpl.Config(basePrice,baseWeight,baseSauce,baseDiameter);
        }

        /**
         * 
         * @param extras The list of {@link Extra Extras} to configure the resultant {@link Food}
         * @return
         */
        @Override
        public F create(List<? extends Extra<? super C>> extras) {

            return (F) PizzaImpl.BUILDER.build(null,null, (List<? extends Extra<PizzaImpl.Config>>) extras);
        }
	}
}
