package projekt.food;

import java.math.BigDecimal;
import java.util.List;

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

}
