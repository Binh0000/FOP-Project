package projekt.food;

import java.util.function.UnaryOperator;

public interface Pasta extends Saucable{
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
		void thickness(UnaryOperator<Double> op);
		
		/**
		 * 
		 * @return
		 */
		UnaryOperator<Double> getThicknessMutator();
	}
}
