package projekt.food;

import java.util.function.DoubleUnaryOperator;

public interface Pasta extends Saucable {
	
	/**
	 * Returns the thickness of the pasta noodles
	 * @return thickness of the pasta noodles
	 */
	double getThickness();
	
	interface Config extends Saucable.Config {
		
		/**
		 * 
		 * @param op
		 */
		void thickness(DoubleUnaryOperator op);
		
		/**
		 * 
		 * @return
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
