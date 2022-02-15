package projekt.food;

import java.util.function.DoubleUnaryOperator;

import projekt.food.Pizza.Variant;

public interface Pasta extends Saucable {
	
	//TODO H2.1
	/**
	 * Returns the thickness of the pasta noodles
	 * @return thickness of the pasta noodles
	 */
	double getThickness();
	
    /**
     * A configurable set of functions that defines the changes needed to convert the 
     * base values of a {@link Variant} into a concrete instance of {@link Pasta}.
	 */
	interface Config extends Saucable.Config {
		
		/**
		 * Configures the thickness attribute of pasta noodles and concatenates the result 
		 * of all previous calls to this method using {@code thicknessMutator}
		 * 
		 * @param thicknessMutator a {@link DoubleUnaryOperator} to configure the noodles thickness 
		 * using its previous value
		 */
		void thickness(DoubleUnaryOperator thicknessMutator);
		
		/**
		 * Compose all parameters from previous calls of {@link #thickness(DoubleUnaryOperator)} 
		 * to a single {@link DoubleUnaryOperator}
		 * 
		 * @return composed thickness mutator function
		 */
		DoubleUnaryOperator getThicknessMutator();
	}
	
	interface Variant extends Saucable.Variant {
		
		/**
		 * 
		 * @return
		 */
		double getBaseThickness();
	}
}
