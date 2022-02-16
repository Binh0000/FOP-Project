package projekt.food;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

//TODO H2.10
public final class FoodTypes {
	
	public static final FoodType<Pizza, Pizza.Config> PIZZA;
	public static final FoodType<Pasta, Pasta.Config> PASTA;
	public static final FoodType<IceCream, IceCream.Config> ICE_CREAM;
	
	public static Map<String, FoodType<? extends Food, ? extends Food.Config>> ALL;
	
	/**
	 * Static block to initialize the class attributes
	 */
	static {
		PIZZA = new FoodTypeImpl<Pizza, Pizza.Config>("Pizza",
				Arrays.asList(Extras.EXTRA_HAM, Extras.EXTRA_OLIVES, 
						Extras.SPICY_SAUCE, Extras.EXTRA_SAUCE, Extras.NO_SAUCE));
		
		PASTA = new FoodTypeImpl<Pasta, Pasta.Config>("Pizza",
				Arrays.asList(Extras.EXTRA_THICK, Extras.SPICY_SAUCE,
						Extras.EXTRA_SAUCE, Extras.NO_SAUCE));
		
		ICE_CREAM = new FoodTypeImpl<IceCream, IceCream.Config>("Pizza",
				Arrays.asList(Extras.RAINBOW_SPRINKLES, Extras.EXTRA_SCOOP));
		
		ALL = Arrays.asList(PIZZA, PASTA, ICE_CREAM)
					.stream()
					.collect(Collectors.toMap(FoodType::getName, e -> e));
	}
	
	/**
	 * Private constructor to override default constructor
	 */
	private FoodTypes() {}	
	
}
