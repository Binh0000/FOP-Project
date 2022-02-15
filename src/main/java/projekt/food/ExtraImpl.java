package projekt.food;

import java.util.function.Consumer;

import projekt.food.Food.Config;

public class ExtraImpl<C extends Food.Config> implements Extra<C> {
	private final String name;
	private final int priority;
	private final Consumer<? super Food.Config> configMutator;
	
	public ExtraImpl(String name, int priority, Consumer<? super Food.Config> configMutator) {
		this.name = name;
		this.priority = priority;
		this.configMutator = configMutator;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getPriority() {
		return priority;
	}

	@Override
	public void apply(Config config) {
		configMutator.accept(config);
	}
	
	
}
