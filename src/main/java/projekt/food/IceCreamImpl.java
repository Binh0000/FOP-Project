package projekt.food;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

import projekt.food.Food.Variant;

public class IceCreamImpl extends AbstractFood implements IceCream {
	
	private String flavor;
	
	/**
	 * Constructs an object that is an implementation of the interface IceCream
	 */
	public IceCreamImpl(BigDecimal price, double weight, IceCream.Variant foodVariant, List<? extends Extra<?>> extras, String flavor) {
		super(price, weight, foodVariant ,extras);
	}
	
	@Override
	/**
	 * Returns the private variable flavor
	 * @return flavor of ice cream
	 */
	public String getFlavor() {
		return flavor;
	}
	
	private static class Config extends AbstractFoodConfig implements IceCream.Config {
		
		private List<UnaryOperator<String>> flavorMutators;
		
		private Config() {
			super();
		}
		
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

}
