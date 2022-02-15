package projekt.food;

import java.util.function.DoubleUnaryOperator;

public interface Pizza extends Saucable {
	
	//TODO H2.1
	/**
	 * Returns the diameter of the pizza
	 * @return diameter of the pizza
	 */
	double getDiameter();
	
    /**
     * A configurable set of functions that defines the changes needed to convert the 
     * base values of a {@link Variant} into a concrete instance of {@link Pizza}.
	 */
	interface Config extends Saucable.Config {
		
		/**
		 * Configures the diameter attribute of a pizza and concatenates the result 
		 * of all previous calls to this method using {@code diameterMutator}
		 * 
		 * @param diameterMutator a {@link DoubleUnaryOperator} to configure the pizza diameter 
		 * using its previous value
		 */
		void diameter(DoubleUnaryOperator diameterMutator);
		
		/**
		 * Compose all parameters from previous calls of {@link #diameter(DoubleUnaryOperator)} 
		 * to a single {@link DoubleUnaryOperator}
		 * 
		 * @return composed diameter mutator function
		 */
		DoubleUnaryOperator getDiameterMutator();
	}
	
	interface Variant extends Saucable.Variant {
		
		/**
		 * 
		 * @return
		 */
		double getBaseDiameter();
	}
}
