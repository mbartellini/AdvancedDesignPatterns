package at.technikumwien.menu.builders;

import at.technikumwien.menu.objects.Dish;
import at.technikumwien.menu.objects.Menu;

import java.util.List;

public class MenuBuilder {
    List<Dish> dishes;
    String language;
    public Menu builder() {
        return new Menu(dishes, language);
    }

    public MenuBuilder setDishes(List<Dish> dishes) {
        this.dishes = dishes;
        return this;
    }

    public MenuBuilder setLanguage(String language) {
        this.language = language;
        return this;
    }
}
