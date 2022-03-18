package projekt.food;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

abstract class AbstractFood implements Food {
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
	 * Implementation of the interface Food.Config
	 */
	static class Config implements Food.Config {
		BigDecimal p;
		double w;

		List<UnaryOperator<BigDecimal>> priceMutators = new ArrayList<>();
		List<DoubleUnaryOperator> weightMutators = new ArrayList<>();;
		
		/**
		 * Constructs a {@link AbstractFood.Config} object 
		 * 
		 * @param p price
		 * @param w weight
		 */
		Config(BigDecimal p, double w) {
			this.p = p;
			this.w = w;
		}

		@Override
        /**
         * Concatenates the result of all previous calls to this method with the provided {@code priceMutator}.
         *
         * @param priceMutator A {@link UnaryOperator} which determines a new price based on the previous value
         */
		public void price(UnaryOperator<BigDecimal> priceMutator) {
			p = priceMutator.apply(p);
			priceMutators.add(priceMutator);
		}

		@Override
        /**
         * The price mutator accepts a base price and produces a configured price.
         *
         * <p>
         * The function returned by this method is the result of concatenating all previous inputs into the
         * {@link #price(UnaryOperator)} method.
         * </p>
         *
         * @return The price mutation function
         */
		public UnaryOperator<BigDecimal> getPriceMutator() {
			return priceMutators.stream()
					 			 .reduce((n -> n),
					 					 (op1, op2) -> (UnaryOperator<BigDecimal>) op1.compose(op2));
		}

		@Override
        /**
         * Concatenates the result of all previous calls to this method with the provided {@code weightMutator}.
         *
         * <p>
         * The function returned by this method is the result of concatenating all previous inputs into the
         * {@link #weight(DoubleUnaryOperator)} method.
         * </p>
         *
         * @param weightMutator A {@link DoubleUnaryOperator} which determines a new price based on the previous value
         */
		public void weight(DoubleUnaryOperator weightMutator) {
			w = weightMutator.applyAsDouble(w);
			weightMutators.add(weightMutator);
		}

		@Override
        /**
         * The weight mutator accepts a base weight and produces a configured weight.
         *
         * @return The weight mutation function
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
		 * Constructs a {@link Variant} of a food with its name and base price and weight
		 *
		 * @param name The name of this food variant
		 * @param foodType the food type in which this variant is grouped
		 * @param basePrice the base price of this food variant
		 * @param baseWeight the base weight of this food variant
		 */
		Variant(String name, FoodType<F, C> foodType, BigDecimal basePrice, double baseWeight) {
			this.name = name;
			this.foodType = foodType;
			this.basePrice = basePrice;
			this.baseWeight = baseWeight;
		}

		@Override
        /**
         * The name of this variant.
         *
         * @return The name of this variant
         */
		public String getName() {
			return name;
		}

		@Override
        /**
         * The food type in which this variant is grouped.
         *
         * @return The food type of this variant
         */
		public FoodType<F, C> getFoodType() {
			return foodType;
		}

		@Override
        /**
         * The base price of this variant
         *
         * @return The base price of this variant
         */
		public BigDecimal getBasePrice() {
			return basePrice;
		}

		@Override
        /**
         * The base weight of this variant
         *
         * @return The base weight of this variant
         */
		public double getBaseWeight() {
			return baseWeight;
		}

		@Override
        /**
         * Creates an empty {@link Config} for this variant.
         *
         * @return An empty {@link Config} for this variant
         */
		public C createEmptyConfig() {
			return null;
		}

		@Override
        /**
         * Creates a new instance of {@link Food} described by this variant, its base values and modifications defined by 
         * the provided list of {@link Extra Extras}.
         *
         * @param extras The list of {@link Extra Extras} to configure the resultant {@link Food}
         * @return An instance of {@link Food} based on the values from this variant and configured by the provided extras
         */
		public F create(List<? extends Extra<? super C>> extras) {
			return null;
		}	
	}
}
