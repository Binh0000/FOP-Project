package projekt.food;

import java.util.Arrays;

public final class FoodTypes {
	public static final FoodType<? extends Food, Pizza.Config> PIZZA;
	public static final FoodType<? extends Food, Pasta.Config> PASTA;
	public static final FoodType<IceCream, IceCream.Config> ICE_CREAM;
	
	static {
		PIZZA = new FoodTypeImpl<Food, Pizza.Config>("Pizza",
				Arrays.asList(Extras.EXTRA_HAM, Extras.EXTRA_OLIVES, 
						Extras.SPICY_SAUCE, Extras.EXTRA_SAUCE, Extras.NO_SAUCE));
		
		PASTA = new FoodTypeImpl<Food, Pasta.Config>("Pizza",
				Arrays.asList(Extras.EXTRA_THICK, Extras.SPICY_SAUCE,
						Extras.EXTRA_SAUCE, Extras.NO_SAUCE));
		
		ICE_CREAM = new FoodTypeImpl<IceCream, IceCream.Config>("Pizza",
				Arrays.asList(Extras.RAINBOW_SPRINKLES, Extras.EXTRA_SCOOP));
	}
	
	/**
	 * Private constructor to override default constructor
	 */
	private FoodTypes() {}
	
	
}
