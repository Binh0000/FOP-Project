package projekt.food;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

public abstract class AbstractFoodConfig implements Food.Config{
	
	private List<UnaryOperator<BigDecimal>> priceMutators;
	private List<DoubleUnaryOperator> weightMutators;

	@Override
	/**
	 * 
	 */
	public void price(UnaryOperator<BigDecimal> priceMutator) {
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
