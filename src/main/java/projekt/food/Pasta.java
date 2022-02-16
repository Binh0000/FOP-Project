package projekt.food;

import java.util.function.DoubleUnaryOperator;

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
	
	/**
	 * A specific but not yet complete variant of Pasta; e.g.: Carbonara, Lasagna, Spaghetti Bolognese... 
	 */
	interface Variant<F extends Pasta, C extends Pasta.Config> extends Saucable.Variant<F, C> {
		
		/**
		 * The base thickness of variant noodles
		 * 
		 * @return The base thickness of variant noodles
		 */
		double getBaseThickness();
	}
}
