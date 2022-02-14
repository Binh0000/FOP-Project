package projekt.food;

import java.util.function.UnaryOperator;

public interface IceCream extends Food {
	/**
	 * Returns the flavor of the ice cream
	 * @return	flavor of the ice cream
	 */
	String getFlavor();
	
	interface Config extends Food.Config {
		
		void flavor(UnaryOperator<String> op);
		
		UnaryOperator<String> getFlavorMutator();
	}
}
