package projekt.food;
import java.math.BigDecimal;
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
         * Concatenates the result of all previous calls to this method with the provided {@code thicknessMutator}.
         *
         * @param thicknessMutator A {@link DoubleUnaryOperator} which determines a new thickness based on the previous value
         */
		void thickness(DoubleUnaryOperator thicknessMutator);
		
        /**
         * The thickness mutator accepts a base thickness and produces a configured thickness.
         *
         * <p>
         * The function returned by this method is the result of concatenating all previous inputs into the
         * {@link #thickness(DoubleUnaryOperator)} method.
         * </p>
         *
         * @return The thickness mutation function
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
    
    //H2.13
    Pasta.Variant<Pasta,Pasta.Config> SPAGHETTI =
        new PastaImpl.Variant<>("Spaghetti", FoodTypes.PASTA, BigDecimal.valueOf(12.5), 0.2, null, 2);
    
    Pasta.Variant<Pasta,Pasta.Config> RIGATONI =
        new PastaImpl.Variant<>("Rigatoni", FoodTypes.PASTA, BigDecimal.valueOf(11.5), 0.2, null, 10);
    
    Pasta.Variant<Pasta,Pasta.Config> RAVIOLI =
        new PastaImpl.Variant<>("Ravioli", FoodTypes.PASTA, BigDecimal.valueOf(11.5), 0.2, null, 40);
    
    Pasta.Variant<Pasta,Pasta.Config> FUSILLI =
        new PastaImpl.Variant<>("Fusilli", FoodTypes.PASTA, BigDecimal.valueOf(11.5), 0.2, null, 15);
}
