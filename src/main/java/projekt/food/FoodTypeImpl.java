package projekt.food;

import java.util.ArrayList;
import java.util.List;

import projekt.food.Food.Variant;

//TODO H2.9
class FoodTypeImpl<F extends Food, C extends Food.Config> implements FoodType<F, C> {
	
	private final String name;
	private final List<? extends Extra<? super C>> compatibleExtras;
	private final List<Food.Variant<F, C>> foodVariants;
	
	/**
	 * Constructs a {@link FoodTypeImpl} representing a food type
	 * 
	 * @param name name of this food type
	 * @param compatibleExtras list of {@link Extra Extras} compatible with this food type
	 */
	FoodTypeImpl(String name, List<? extends Extra<? super C>> compatibleExtras) {
		this.name = name;
		this.compatibleExtras = compatibleExtras;
		this.foodVariants = new ArrayList<Food.Variant<F, C>>();
	}
	
	@Override
    /**
     * The name of this food type.
     *
     * @return The name of this type
     */
	public String getName() {
		return name;
	}

	@Override
    /**
     * The {@link Extra Extras} compatible with this food type.
     *
     * @return The {@link Extra Extras} compatible with this food type
     */
	public List<? extends Extra<? super C>> getCompatibleExtras() {
		return compatibleExtras;
	}
	

	@Override
    /**
     * Adds a {@link Food.Variant} to this food type.
     *
     * @param variant The {@link Food.Variant} to add to this food type
     */
	public void addFoodVariant(Variant<F, C> variant) {
			
	}
	
	@Override
    /**
     * The {@link Food.Variant food variants} that this food type are part of.
     * 
     * @return The {@link Food.Variant food variants} that this food type are part of
     */
	public List<? extends Variant<F, C>> getFoodVariants() {
		return foodVariants;
	}

}
