package projekt.food;

import java.util.function.UnaryOperator;

public interface Saucable extends Food {
	
	//TODO H2.1	
	/**
	 * Returns which type of sauce of this food 
	 * @return type of sauce
	 */
	String getSauce();
	
    /**
     * A configurable set of functions that defines the changes needed to convert the 
     * base values of a {@link Variant} into a concrete instance of {@link Saucable}.
	 */
	interface Config extends Food.Config {
		/**
		 * Configures the sauce attribute of a food and concatenates the result 
		 * of all previous calls to this method using {@code sauceMutator}
		 * 
		 * @param sauceMutator a {@link UnaryOperator} to configure the sauce using its previous value
		 */
		void sauce(UnaryOperator<String> sauceMutator);
		
		/**
		 * Compose all parameters from previous calls of {@link #price(UnaryOperator)} to a single
		 * {@link UnaryOperator}
		 * @return composed sauce mutator function
		 */
		UnaryOperator<String> getSauceMutator();
	}
	
	/**
	 * A specific but not yet complete variant of Saucable foods; e.g.: A sauce can be 
	 * tomato, bolognese or carbonara sauce.
	 */
	interface Variant<F extends Saucable, C extends Saucable.Config> extends Food.Variant<F, C> {
		
		/**
		 * The base sauce of this variant
		 * 
		 * @return The base sauce of this variant
		 */
		String getBaseSauce();
	}
}
