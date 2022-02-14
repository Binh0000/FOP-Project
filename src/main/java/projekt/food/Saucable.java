package projekt.food;

import java.util.function.UnaryOperator;

public interface Saucable {
	/**
	 * Returns which type of sauce this food has
	 * @return type of sauce
	 */
	String getSauce();
	
	/**
	 * 
	 * @author phian_1uq03zl
	 *
	 */
	interface Config extends Food.Config {
		/**
		 * 
		 * @param op
		 */
		void sauce(UnaryOperator<String> op);
		
		/**
		 * 
		 * @return
		 */
		UnaryOperator<String> getSauceMutator();
	}
	
	interface Variant extends Food.Variant<Food, Config> {
		
	}
}
