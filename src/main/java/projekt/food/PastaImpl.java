package projekt.food;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

class PastaImpl extends AbstractSaucable implements Pasta {
	
	//TODO H2.11
	static final FoodBuilder<PastaImpl, Config, Variant<PastaImpl, Config>> BUILDER =
		(Config config, Variant<PastaImpl, Config> variant, List<? extends Extra<Config>> extras) -> {
			return new PastaImpl(config.p, config.w, variant, extras, config.s, config.t);
	};

	final double thickness;
	
	/**
	 * Constructs a new {@link PastaImpl} representing a pizza with its parameters as attributes of the pasta
	 * 
	 * @param price price of this pasta
	 * @param weight weight of this pasta
	 * @param foodVariant variant of this pasta
	 * @param extras list of extras of this pasta
	 * @param sauce sauce of this pasta
	 * @param thickness noodles thickness of this pasta
	 */
	public PastaImpl(BigDecimal price, double weight, 
			Pasta.Variant<? extends Pasta, ? extends Pasta.Config> foodVariant, 
			List<? extends Extra<?>> extras, String sauce, double thickness) {
		super(price, weight, foodVariant, extras, sauce);
		this.thickness = thickness;
	}

	@Override
	/**
	 * Returns the private variable thickness
	 * @return thickness of pasta noodles
	 */
	public double getThickness() {
		return thickness;
	}
	
	//TODO H2.5
	private static class Config extends AbstractSaucable.Config implements Pasta.Config {
		
		double t;
		
		private List<DoubleUnaryOperator> thicknessMutators = new ArrayList<>();
		
		/**
		 * 
		 * 
		 * @param p
		 * @param w
		 * @param s
		 * @param t
		 */
		Config(BigDecimal p, double w, String s, double t) {			
			super(p, w, s);
			this.t = t;
		}

		@Override
		/**
		 * 
		 */
		public void thickness(DoubleUnaryOperator thicknessMutator) {
			t = thicknessMutator.applyAsDouble(t);
			thicknessMutators.add(thicknessMutator);			
		}

		@Override
		/**
		 * 
		 */
		public DoubleUnaryOperator getThicknessMutator() {
			return thicknessMutators.stream()														  
		 			 .reduce((n -> n),
		 					 (op1, op2) -> op1.compose(op2));
		}		
	}
	
	//TODO H2.12
	static class Variant<F extends Pasta, C extends Pasta.Config> extends AbstractSaucable.Variant<F, C> implements Pasta.Variant<F, C> {
		
		double baseThickness;
		
		/**
		 * 
		 * @param name
		 * @param foodType
		 * @param basePrice
		 * @param baseWeight
		 * @param baseSauce
		 * @param baseThickness
		 */
		Variant(String name, FoodType<F, C> foodType, BigDecimal basePrice, double baseWeight, String baseSauce, double baseThickness) {
			super(name, foodType, basePrice, baseWeight, baseSauce);
			this.baseThickness = baseThickness;
		}

		@Override
		/**
		 * 
		 */
		public double getBaseThickness() {
			return baseThickness;
		}		
		
		@SuppressWarnings("unchecked")
		@Override
		public C createEmptyConfig() {
			return (C) new Config(basePrice, baseWeight, baseSauce, baseThickness);
		}

		@Override
		public F create(List<? extends Extra<? super C>> extras) {
			return null;
		}
	}
}
