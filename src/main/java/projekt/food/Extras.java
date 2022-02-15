package projekt.food;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public final class Extras {
	public static final Extra<Pizza.Config> EXTRA_HAM;
	public static final Extra<Pizza.Config> EXTRA_OLIVES;
	public static final Extra<Pasta.Config> EXTRA_THICK;
	public static final Extra<Saucable.Config> SPICY_SAUCE;
	public static final Extra<Saucable.Config> EXTRA_SAUCE;
	public static final Extra<Saucable.Config> NO_SAUCE;
	public static final Extra<IceCream.Config> RAINBOW_SPRINKLES;
	public static final Extra<IceCream.Config> EXTRA_SCOOP;

	public static final Map<String, Extra<?>> ALL;

	static {
		EXTRA_HAM = new ExtraImpl<Pizza.Config>("Extra Ham", 5,
				new Consumer<Food.Config>() {
					public void accept(Food.Config t) {
						t.price(p -> BigDecimal.valueOf(p.doubleValue() + 1));
						t.weight(w -> w + 0.1);
					}
				}
		);

		EXTRA_OLIVES = new ExtraImpl<Pizza.Config>("Extra Olives", 5,
				new Consumer<Food.Config>() {
					public void accept(Food.Config t) {
						t.price(p -> BigDecimal.valueOf(p.doubleValue() + 0.8));
						t.weight(w -> w + 0.05);
					}
				}
		);

		EXTRA_THICK = new ExtraImpl<Pasta.Config>("Extra Thick", 4,
				new Consumer<Food.Config>() {
					public void accept(Food.Config t) {
						t.price(p -> BigDecimal.valueOf(p.doubleValue() + 4));
						t.weight(w -> w * 2);
						((Pasta.Config) t).thickness(thickness -> thickness * 2);
					}
				}
		);

		SPICY_SAUCE = new ExtraImpl<Saucable.Config>("Spicy Sauce", 3,
				new Consumer<Food.Config>() {
					public void accept(Food.Config t) {
						t.price(p -> BigDecimal.valueOf(p.doubleValue() + 0.5));
						((Saucable.Config) t).sauce(s -> "Spicy " + s);
					}
				}
		);

		EXTRA_SAUCE = new ExtraImpl<Saucable.Config>("Extra Sauce", 4,
				new Consumer<Food.Config>() {
					public void accept(Food.Config t) {
						t.price(p -> BigDecimal.valueOf(p.doubleValue() + 1.25));
						t.weight(w -> w + 0.12);
						((Saucable.Config) t).sauce(s -> "Extra " + s);
					}
				}
		);

		NO_SAUCE = new ExtraImpl<Saucable.Config>("No Sauce", 5,
				new Consumer<Food.Config>() {
					public void accept(Food.Config t) {
						t.price(p -> BigDecimal.valueOf(p.doubleValue() <= 1? 0.0 : p.doubleValue() - 1));
						t.weight(w -> w <= 0.1? 0.0 : w - 0.1);
						((Saucable.Config) t).sauce(s -> null);
					}
				}
		);

		RAINBOW_SPRINKLES = new ExtraImpl<IceCream.Config>("Rainbow Sprinkles", 5,
				new Consumer<Food.Config>() {
					public void accept(Food.Config t) {
						t.price(p -> BigDecimal.valueOf(p.doubleValue() + 0.5));
						t.weight(w -> w + 0.03);
					}
				}
		);

		EXTRA_SCOOP = new ExtraImpl<IceCream.Config>("Extra Scoop", 2,
				new Consumer<Food.Config>() {
					public void accept(Food.Config t) {
						t.price(p -> BigDecimal.valueOf(p.doubleValue() + 3));
						t.weight(w -> w + 0.1);
					}
				}
		);

		ALL = Arrays.asList(EXTRA_HAM, EXTRA_OLIVES, EXTRA_THICK, SPICY_SAUCE,
							EXTRA_SAUCE, NO_SAUCE, RAINBOW_SPRINKLES, EXTRA_SCOOP)
					.stream()
					.collect(Collectors.toMap(Extra::getName, e -> e));
	}

	/**
	 * Private constructor to override public constructer
	 */
	private Extras() {}
}
