package projekt;

import java.util.*;
import projekt.food.*;

public class Main {

	public static void main(String[] args) {
		//List of extras
		List<? extends Extra<? super Pizza.Config>> pizzaExtras = 
				Arrays.asList(Extras.EXTRA_HAM, Extras.NO_SAUCE, Extras.EXTRA_OLIVES);
		List<? extends Extra<? super IceCream.Config>> iceCreamExtras = 
				Arrays.asList(Extras.EXTRA_SCOOP, Extras.RAINBOW_SPRINKLES);
		List<? extends Extra<? super Pasta.Config>> pastaExtras = Arrays.asList(
				Extras.EXTRA_THICK, Extras.SPICY_SAUCE);
		List<? extends Extra<? super Pasta.Config>> pastaExtras2 = Arrays.asList(
				Extras.EXTRA_SAUCE, Extras.SPICY_SAUCE);
		//List<? extends Extra<? super Pasta.Config>> empty = Arrays.asList();
		
		//Create food		
		Pizza pizza = Pizza.MARGHERITA.create(pizzaExtras);
		IceCream iceCream = IceCream.VANILLA.create(iceCreamExtras);
		Pasta pasta = Pasta.SPAGHETTI.create(pastaExtras);
		Pasta pasta2 = Pasta.RAVIOLI.create(pastaExtras2);
		Food[] arr = {pizza, iceCream, pasta, pasta2};
		
		//Print out result
		for(Food f : arr) {
			System.out.println("Food type: " + f.getFoodVariant().getFoodType().getName());
			System.out.println("Price: " + f.getPrice());
			System.out.println("Weight: " + f.getWeight());
			if(f instanceof Saucable) {
				System.out.println("Sauce: " + ((Saucable) f).getSauce());
				System.out.println(
						(f instanceof Pizza)? "Diameter: " + ((Pizza) f).getDiameter():
											  "Thickness: " + ((Pasta) f).getThickness()
				);
			} else {
				System.out.println("Flavor: " + ((IceCream) f).getFlavor());
			}
			
			List<? extends Extra<?>> extraList = f.getExtras();
			Iterator<? extends Extra<?>> it = extraList.iterator();
			System.out.print("Extras for this food: ");
			while(it.hasNext()) {
				System.out.print(it.next().getName() + (it.hasNext()? ", " : ""));				
				
			}
			System.out.println('\n');
		}		
	}
}
