package projekt.food;

import java.util.List;

@FunctionalInterface
public interface FoodBuilder<F extends Food, C extends Food.Config, V extends Food.Variant<? extends F, ? extends C>> {
	
	/**
	 * Builds a new {@link Food} object using Configs, Variants and Extras
	 * 
	 * @param config
	 * @param variant
	 * @param extras
	 * 
	 * @return a completed food
	 */
	F build(C config, V variant, List<? extends Extra<C>> extras);
}
