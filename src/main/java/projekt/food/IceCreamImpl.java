package projekt.food;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.UnaryOperator;

class IceCreamImpl extends AbstractFood implements IceCream {
	
	static final FoodBuilder<IceCream, IceCream.Config, Food.Variant<IceCream, IceCream.Config>> BUILDER;
	
	static {
		BUILDER = (config, variant, extras) -> {
			return null;
		};
	}
	
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
	
	private static class Config extends AbstractFood.Config implements IceCream.Config {
		
		private List<UnaryOperator<String>> flavorMutators;
		
		@Override
		/**
		 * 
		 */
		public void flavor(UnaryOperator<String> op) {
			
			flavorMutators.add(op);			
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
	
	static class Variant<F extends IceCream, C extends IceCream.Config> extends AbstractFood.Variant<F, C> implements IceCream.Variant<F, C> {

		@Override
		public String getBaseFlavor() {
			return null;
		}
		
	}

}
