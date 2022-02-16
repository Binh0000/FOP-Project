package projekt.food;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

class AbstractFood implements Food {
	final BigDecimal price;
	final double weight;
	final Food.Variant<? extends Food, ? extends Food.Config> foodVariant;
	final List<? extends Extra<?>> extras;
	
	/**
	 * Constructs an object that is an implementation of {@link Food}
	 * 
	 * @param price price of this food
	 * @param weight weight of this food
	 * @param foodVariant variant of this food
	 * @param extras extras compatible with this food
	 */
	public AbstractFood(BigDecimal price, double weight, 
			Food.Variant<? extends Food, ? extends Food.Config> foodVariant, 
			List<? extends Extra<?>> extras) {
		this.price = price;
		this.weight = weight;
		this.foodVariant = foodVariant;
		this.extras = extras;
	}
	
	@Override
    /**
     * The price of this food.
     *
     * @return The price of this food
     */
	public BigDecimal getPrice() {
		return price;
	}
	
	@Override
    /**
     * The weight of this food.
     *
     * @return The weight of this food
     */
	public double getWeight() {
		return weight;
	}

	@Override
    /**
     * The food variant.
     *
     * @return The food variant
     */
	public Food.Variant<? extends Food, ? extends Food.Config> getFoodVariant() {
		return foodVariant;
	}

	@Override
    /**
     * The extras that this food was configured with.
     *
     * @return The extras that this food was configured with
     */
	public List<? extends Extra<?>> getExtras() {
		return extras;
	}
	
	/**
	 * 
	 * 
	 *
	 */
	static class Config implements Food.Config {
		BigDecimal p;
		double w;
		
		List<UnaryOperator<BigDecimal>> priceMutators;
		List<DoubleUnaryOperator> weightMutators;
		
		Config(BigDecimal p, double w) {
			this.p = p;
			this.w = w;
		}

		@Override
		/**
		 * 
		 */
		public void price(UnaryOperator<BigDecimal> priceMutator) {
			p = priceMutator.apply(p);
			priceMutators.add(priceMutator);
		}

		@Override
		/**
		 * 
		 */
		public UnaryOperator<BigDecimal> getPriceMutator() {			
			return priceMutators.stream()														  
					 			 .reduce((n -> n),
					 					 (op1, op2) -> (UnaryOperator<BigDecimal>) op1.compose(op2));
		}

		@Override
		/**
		 * 
		 */
		public void weight(DoubleUnaryOperator weightMutator) {
			w = weightMutator.applyAsDouble(w);
			weightMutators.add(weightMutator);
		}

		@Override
		/**
		 * 
		 */
		public DoubleUnaryOperator getWeightMutator() {
			return weightMutators.stream()														  
					 			 .reduce((n -> n),
					 					 (op1, op2) -> op1.compose(op2));
		}		
	}
	
	static class Variant<F extends Food, C extends Food.Config> implements Food.Variant<F, C> {
		
		String name;
		FoodType<F, C> foodType;
		BigDecimal basePrice;
		double baseWeight;
		
		/**
		 * 
		 * @param name
		 * @param foodType
		 * @param basePrice
		 * @param baseWeight
		 */
		Variant(String name, FoodType<F, C> foodType, BigDecimal basePrice, double baseWeight) {
			this.name = name;
			this.foodType = foodType;
			this.basePrice = basePrice;
			this.baseWeight = baseWeight;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public FoodType<F, C> getFoodType() {
			return foodType;
		}

		@Override
		public BigDecimal getBasePrice() {
			return basePrice;
		}

		@Override
		public double getBaseWeight() {
			return baseWeight;
		}

		@Override
		public C createEmptyConfig() {
			return null;
		}

		@Override
		public F create(List<? extends Extra<? super C>> extras) {			
			return null;
		}

		
	}
}
