package projekt.food;

import java.util.ArrayList;
import java.util.List;

import projekt.food.Food.Variant;

class FoodTypeImpl<F extends Food, C extends Food.Config> implements FoodType<F, C> {
	private final String name;
	private final List<? extends Extra<? super C>> compatibleExtras;
	private final List<Food.Variant<F, C>> foodVariants;
	
	FoodTypeImpl(String name, List<? extends Extra<? super C>> compatibleExtras) {
		this.name = name;
		this.compatibleExtras = compatibleExtras;
		this.foodVariants = new ArrayList<Food.Variant<F, C>>();
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<? extends Extra<? super C>> getCompatibleExtras() {
		return compatibleExtras;
	}
	
	@Override
	public List<? extends Variant<F, C>> getFoodVariants() {
		return foodVariants;
	}

	@Override
	public void addFoodVariant(Variant<F, C> variant) {
			
	}

}
