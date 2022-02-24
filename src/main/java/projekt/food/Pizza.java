package projekt.food;
import java.math.BigDecimal;
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
	
	/**
	 * A specific but not yet complete variant of Pizza; e.g.: Italian pizza, American pizza,... 
	 */
	interface Variant<F extends Pizza, C extends Pizza.Config> extends Saucable.Variant<F, C> {
		
		/**
		 * The base diameter of this variant
		 * 
		 * @return The base diameter of this variant
		 */
		double getBaseDiameter();
	}
	
	//TODO H2.13
    Pizza.Variant<Pizza, Pizza.Config> MARGHERITA =
        new PizzaImpl.Variant<>("Margherita", FoodTypes.PIZZA, BigDecimal.valueOf(9.75),
        0.8,"Tomato",30.0);

    Pizza.Variant<Pizza, Pizza.Config> HAWAII =
        new PizzaImpl.Variant<>("Hawaii", FoodTypes.PIZZA, BigDecimal.valueOf(13.75),
        1.0,"Tomato",30.0);

    Pizza.Variant<Pizza, Pizza.Config> RUCOLA =
        new PizzaImpl.Variant<>("Rucola", FoodTypes.PIZZA,BigDecimal.valueOf(14.50),
            0.9,"Tomato", 30.0);

    Pizza.Variant<Pizza, Pizza.Config> BBQ =
        new PizzaImpl.Variant<>("BBQ", FoodTypes.PIZZA, BigDecimal.valueOf(14.50),
            1.1,"BBQ",30.0);
}
