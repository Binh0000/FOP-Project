package projekt.food;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A modification that targets configurable values in a {@link Food.Config}.
 *
 * @param <C> The target {@link Food.Config} type
 */
public interface Extra<C extends Food.Config> {

    /**
     * The name of this extra.
     *
     * @return The name of this extra
     */
    String getName();

    /**
     * The priority of the extra, lower is calculated first
     *
     * @return The priority of the extra
     */
    int getPriority();

    /**
     * Applies the modifications of this extra to the provided {@link C config}.
     * @param {@link C config} to apply this extra to
     */
    void apply(C config);
    
    /**
     * Sorts a list of {@link Config} by their priority and apply them to an existing {@link Config}
     * 
     * @param <C> The target {@link Saucable.Config} type
     * @param config {@link Config} to apply to
     * @param extras list of {@link Config} to apply 
     */
    static <C extends Food.Config> void writeToConfig(C config, List<? extends Extra<C>> extras) {
    	List<? extends Extra<C>> sorted = 
			extras.stream()
				  .sorted((c1, c2) -> {
					  return Integer.valueOf(c1.getPriority()).compareTo(c2.getPriority());
				  })
				  .collect(Collectors.toList());	
    	
    	sorted.sort((c1, c2) -> {
    		if(c1.getPriority() == c2.getPriority()) {
    			return c1.getName().compareTo(c2.getName());
    		} 
    		else return 0;
    	});    	
    	
    	for(Extra<C> c: sorted) {
    		c.apply(config);
    	}
    }
}
