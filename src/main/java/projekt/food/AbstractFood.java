package projekt.food;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

public class AbstractFood implements Food{
	private BigDecimal price;
	private double weight;
	private Variant<?, ?> foodVariant;
	
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
		return null;
	}
	
	private static class Config implements Food.Config {

		@Override
		public void price(UnaryOperator<BigDecimal> priceMutator) {
			
		}

		@Override
		public UnaryOperator<BigDecimal> getPriceMutator() {
			return null;
		}

		@Override
		public void weight(DoubleUnaryOperator weightMutator) {
			
		}

		@Override
		public DoubleUnaryOperator getWeightMutator() {
			
			return null;
		}
		
	}

}
