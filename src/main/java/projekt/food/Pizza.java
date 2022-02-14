package projekt.food;

import java.util.function.UnaryOperator;

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
		void diameter(UnaryOperator<Double> op);
		
		/**
		 * 
		 * @return
		 */
		UnaryOperator<Double> getDiameterMutator();
	}
	
	interface Variant extends Saucable.Variant {
		
		/**
		 * 
		 * @return
		 */
		double getBaseDiameter();
	}
}
