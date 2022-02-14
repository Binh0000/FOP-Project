package projekt.food;

import java.util.function.DoubleUnaryOperator;

public interface Pizza extends Saucable {
	
	/**
	 * Returns the diameter of the pizza
	 * @return diameter of the pizza
	 */
	double getDiameter();
	
	/**
	 * 
	 *
	 */
	interface Config extends Saucable.Config {
		
		/**
		 * 
		 * @param op
		 */
		void diameter(DoubleUnaryOperator op);
		
		/**
		 * 
		 * @return
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
