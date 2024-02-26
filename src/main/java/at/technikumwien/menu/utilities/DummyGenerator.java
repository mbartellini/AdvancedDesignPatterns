package at.technikumwien.menu.utilities;

import at.technikumwien.menu.builders.MenuBuilder;
import at.technikumwien.menu.objects.Dish;
import at.technikumwien.menu.objects.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DummyGenerator {
    public List<Dish> generateEnglishDishes(int amount) {
        List<Dish> dishes = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < amount; i++) {
            int cost = random.nextInt(16) +  5;
            Dish dish = new Dish(
                i,
                String.format("Dish_%d", i),
                cost,
        "USD"
            );
            dishes.add(dish);
        }
        return dishes;
    }

    public Menu generateEnglishMenu() {
        return new MenuBuilder()
                .addDishes(generateEnglishDishes(5))
                .setLanguage("Eng")
                .build();
    }
}
