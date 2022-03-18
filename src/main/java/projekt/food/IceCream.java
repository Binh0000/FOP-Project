package projekt.food;
import java.math.BigDecimal;
import java.util.function.UnaryOperator;

public interface IceCream extends Food {
	
	//TODO H2.1
	/**
	 * Returns the flavor of the ice cream
	 * @return	flavor of the ice cream
	 */
	String getFlavor();
	
    /**
     * A configurable set of functions that defines the changes needed to convert the 
     * base values of a {@link Variant} into a concrete instance of {@link IceCream}.
	 */
	interface Config extends Food.Config {

        /**
         * Concatenates the result of all previous calls to this method with the provided {@code flavorMutator}.
         *
         * @param flavorMutator A {@link UnaryOperator} which determines a new flavor based on the previous value
         */
		void flavor(UnaryOperator<String> flavorMutator);
		
        /**
         * The flavor mutator accepts a base flavor and produces a configured flavor.
         *
         * <p>
         * The function returned by this method is the result of concatenating all previous inputs into the
         * {@link #flavor(UnaryOperator)} method.
         * </p>
         *
         * @return The flavor mutation function
         */
		UnaryOperator<String> getFlavorMutator();
	}
	
	/**
	 * A specific but not yet complete variant of Ice cream; e.g.: Ice cream cone, stick, bowl,...
	 */
	interface Variant<F extends IceCream, C extends IceCream.Config> extends Food.Variant<F, C> {
		
		/**
		 * The base flavor of this variant
		 * 
		 * @return The base flavor of this variant
		 */
		String getBaseFlavor();
	}
    
    //TODO H2.13
    IceCream.Variant<IceCream,IceCream.Config> VANILLA =
        new IceCreamImpl.Variant<>("Vanilla", FoodTypes.ICE_CREAM, BigDecimal.valueOf(1.5), 0.2, "Vanilla");
    
    IceCream.Variant<IceCream,IceCream.Config> STRAWBERRY =
        new IceCreamImpl.Variant<>("Strawberry", FoodTypes.ICE_CREAM, BigDecimal.valueOf(1.5), 0.2, "Strawberry");
    
    IceCream.Variant<IceCream,IceCream.Config> CHOCOLATE =
        new IceCreamImpl.Variant<>("Chocolate", FoodTypes.ICE_CREAM, BigDecimal.valueOf(1.5), 0.2, "Chocolate");
    
    IceCream.Variant<IceCream,IceCream.Config> STRACCIATELLA =
        new IceCreamImpl.Variant<>("Stracciatella", FoodTypes.ICE_CREAM, BigDecimal.valueOf(1.5), 0.2, "Stracciatella");

}
