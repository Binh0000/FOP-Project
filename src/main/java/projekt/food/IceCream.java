package projekt.food;

import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

public interface IceCream extends Food {
	
	//TODO H2.1
	/**
	 * Returns the flavor of the ice cream
	 * @return	flavor of the ice cream
	 */
	String getFlavor();
	
    /**
     * A configurable set of functions that defines the changes needed to convert the 
     * base values of a {@link Variant} into a concrete instance of {@link IceCream}.
	 */
	interface Config extends Food.Config {
		
		/**
		 * Configures the flavor attribute of ice cream and concatenates the result 
		 * of all previous calls to this method using {@code flavorMutator}
		 * 
		 * @param flavorMutator a {@link UnaryOperator} to configure the ice cream flavor
		 * using its previous value
		 */
		void flavor(UnaryOperator<String> flavorMutator);
		
		/**
		 * Compose all parameters from previous calls of {@link #flavor(UnaryOperator)} 
		 * to a single {@link UnaryOperator}
		 * 
		 * @return composed flavor mutator function
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
