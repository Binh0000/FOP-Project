package projekt.food;

import java.util.function.Consumer;

//TODO 2.6
public class ExtraImpl<C extends Food.Config> implements Extra<C> {
	
	private final String name;
	private final int priority;
	private final Consumer<? super Food.Config> configMutator;
	
	/**
	 * Constructs a new {@link ExtraImpl} that represents an extra with 3 final attributes
	 * 
	 * @param name initializes name of this extra
	 * @param priority initializes priority of this extra
	 * @param configMutator initializes configMutator of this extra
	 */
	public ExtraImpl(String name, int priority, Consumer<? super Food.Config> configMutator) {
		this.name = name;
		this.priority = priority;
		this.configMutator = configMutator;
	}
	
	@Override
    /**
     * The name of this extra.
     *
     * @return The name of this extra
     */
	public String getName() {
		return name;
	}

	@Override
    /**
     * The priority of the extra, lower is calculated first
     *
     * @return The priority of the extra
     */
	public int getPriority() {
		return priority;
	}

	@Override
    /**
     * Applies the modifications of this extra to the provided {@link C config}.
     * 
     * @param {@link C config} to apply this extra to
     */
	public void apply(Food.Config config) {
		configMutator.accept(config);
	}	
	
}
