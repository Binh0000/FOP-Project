package projekt.food;

import java.util.function.UnaryOperator;

public interface IceCream extends Food {
	
	/**
	 * Returns the flavor of the ice cream
	 * @return	flavor of the ice cream
	 */
	String getFlavor();
	
	interface Config extends Food.Config {
		
		/**
		 * 
		 * @param op
		 */
		void flavor(UnaryOperator<String> op);
		
		/**
		 * 
		 * @return
		 */
		UnaryOperator<String> getFlavorMutator();
	}
	
	interface Variant extends Food.Variant<Food, Config> {
		
		/**
		 * 
		 * @return
		 */
		String getBaseFlavor();
	}
}
