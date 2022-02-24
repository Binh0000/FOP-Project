package projekt.food;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.UnaryOperator;

//TODO H2.4
class IceCreamImpl extends AbstractFood implements IceCream {
	
	//TODO H2.11
	static final FoodBuilder<IceCreamImpl, Config, Variant<IceCreamImpl, Config>> BUILDER = 
		(Config config, Variant<IceCreamImpl, Config> variant, List<? extends Extra<Config>> extras) -> {			
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
	 * @return flavor of ice cream
	 */
	public String getFlavor() {
		return flavor;
	}
	
	//TODO H2.5
	private static class Config extends AbstractFood.Config implements IceCream.Config {
		
		String f;
		private List<UnaryOperator<String>> flavorMutators;
		
		Config(BigDecimal p, double w, String f) {
			super(p, w);
			this.f = f;
		}
		
		@Override
		/**
		 * 
		 */
		public void flavor(UnaryOperator<String> flavorMutator) {
			f = flavorMutator.apply(f);
			flavorMutators.add(flavorMutator);			
		}

		@Override
		/**
		 * 
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
		
		Variant(String name, FoodType<F, C> foodType, BigDecimal basePrice, double baseWeight, String baseFlavor) {
			super(name, foodType, basePrice, baseWeight);
			this.baseFlavor = baseFlavor;
		}
				
		@Override
		/**
		 * 
		 */
		public String getBaseFlavor() {
			return null;
		}
        
        /**
         * 
         * @return
         */
        @Override
        public C createEmptyConfig() {
            return (C) new IceCreamImpl.Config(basePrice,baseWeight,baseFlavor);
        }

        /**
         * 
         * @param extras The list of {@link Extra Extras} to configure the resultant {@link Food}
         * @return
         */
        @Override
        public F create(List<? extends Extra<? super C>> extras) {
            return (F) IceCreamImpl.BUILDER.build((Config) createEmptyConfig(), 
                                                  (Variant<IceCreamImpl, Config>) this, 
                                                  (List<? extends Extra<IceCreamImpl.Config>>) extras);
        }
		
	}

}
