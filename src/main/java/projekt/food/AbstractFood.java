package projekt.food;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;

public class AbstractFood implements Food{
	private BigDecimal price;
	private double weight;
	private Variant<?, ?> foodVariant;
	private List<? extends Extra<?>> extras;
	
	@Override
	public BigDecimal getPrice() {
		return price;
	}
	
	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public Variant<?, ?> getFoodVariant() {
		return foodVariant;
	}

	@Override
	public List<? extends Extra<?>> getExtras() {
		return extras;
	}
	
	private static class Config implements Food.Config {
		private List<UnaryOperator<BigDecimal>> priceMutators;
		private List<DoubleUnaryOperator> weightMutators;

		@Override
		public void price(UnaryOperator<BigDecimal> priceMutator) {
			priceMutators.add(priceMutator);
		}

		@Override
		public UnaryOperator<BigDecimal> getPriceMutator() {
			UnaryOperator<BigDecimal> composition = priceMutators.stream().collect(null);
			UnaryOperator<BigDecimal> temp = null;
			for(UnaryOperator<BigDecimal> priceMutator: priceMutators) {
				if(temp == null) {
					temp = priceMutator;
				} else {
					temp = (n -> priceMutator.apply(n));
				}
			}
			return composition;
		}

		@Override
		public void weight(DoubleUnaryOperator weightMutator) {
			weightMutators.add(weightMutator);
		}

		@Override
		public DoubleUnaryOperator getWeightMutator() {
			
			return null;
		}
		
	}

}
