package projekt.food;

import java.util.function.UnaryOperator;

/**
 * An immutable, configured dish with sauce.
 */
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
         * Concatenates the result of all previous calls to this method with the provided {@code saucetMutator}.
         *
         * @param sauceMutator A {@link UnaryOperator} which determines a new sauce based on the previous value
         */
		void sauce(UnaryOperator<String> sauceMutator);
		
        /**
         * The sauce mutator accepts a base sauce and produces a configured sauce.
         *
         * <p>
         * The function returned by this method is the result of concatenating all previous inputs into the
         * {@link #sauce(UnaryOperator)} method.
         * </p>
         *
         * @return The sauce mutation function
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
