package projekt.food;

import java.util.List;

@FunctionalInterface
/**
 * Constructs a final {@link Food} object
 *
 * @param <F> The target {@link Food} type
 * @param <C> The target {@link Food.Config} type
 * @param <V> The target {@link Food.Variant} type
 */
public interface FoodBuilder<F extends Food, C extends Food.Config, V extends Food.Variant<? extends F, ? extends C>> {
	
	/**
	 * Builds a new {@link Food} object using a {@link Food.Config} , {@link Food.Variant} 
	 * and a {@link List} of {@link Extra}
	 * 
	 * @param config
	 * @param variant
	 * @param extras
	 * 
	 * @return a completed food
	 */
	F build(C config, V variant, List<? extends Extra<C>> extras);
}
