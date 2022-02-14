package projekt.food;

import java.util.List;
import java.util.function.UnaryOperator;

public class AbstractSauceConfig extends AbstractFoodConfig implements Saucable.Config{
	
	private List<UnaryOperator<String>> sauceMutators;
	
	AbstractSauceConfig() {
		super();
	}
	
	@Override
	/**
	 * 
	 */
	public void sauce(UnaryOperator<String> op) {
		sauceMutators.add(op);			
	}

	@Override
	/**
	 * 
	 */
	public UnaryOperator<String> getSauceMutator() {
		return sauceMutators.stream()
							.reduce((n -> n), 
									(op1, op2) -> (UnaryOperator<String>) op1.compose(op2));
	}

}
