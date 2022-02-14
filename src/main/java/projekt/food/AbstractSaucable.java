package projekt.food;

import java.math.BigDecimal;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

public abstract class AbstractSaucable extends AbstractFood implements Saucable {
	
	private String sauce;
	
	/**
	 * Returns the private variable sauce
	 * @return sauce of this food
	 */
	public String getSauce() {
		return sauce;
	}
	
	private static class Config implements Saucable.Config {

		@Override
		public void price(UnaryOperator<BigDecimal> priceMutator) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public UnaryOperator<BigDecimal> getPriceMutator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void weight(DoubleUnaryOperator weightMutator) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public DoubleUnaryOperator getWeightMutator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void sauce(UnaryOperator<String> op) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public UnaryOperator<String> getSauceMutator() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
