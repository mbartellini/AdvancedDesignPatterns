package at.technikumwien.menu.services;

import at.technikumwien.menu.builders.MenuBuilder;
import at.technikumwien.menu.forms.DishForm;
import at.technikumwien.menu.forms.MenuForm;
import at.technikumwien.menu.objects.Dish;
import at.technikumwien.menu.objects.Menu;
import at.technikumwien.menu.repositories.MenuRepository;

import java.util.ArrayList;
import java.util.List;

public class MenuService {

    private MenuRepository menuRepository = MenuRepository.getInstance();

    private static MenuService singleton;

    private MenuService(){

    }

    public static MenuService getInstance() {
        if (singleton == null)
            singleton = new MenuService();
        return singleton;
    }

    public Menu createMenu(MenuForm menuForm) {
        MenuBuilder menuBuilder = new MenuBuilder();
        menuBuilder.setLanguage(menuForm.getPreferredLanguage());
        List<Dish> newDishes = new ArrayList<>();
        for (int i = 0; i < menuForm.getDishes().size(); i++) {
            DishForm dishForm = menuForm.getDishes().get(i);
            // TODO: Add translation
            Dish newDish = new Dish(i, dishForm.getName(), dishForm.getCost(), dishForm.getCurrency());
            newDishes.add(newDish);
        }
        menuBuilder.setDishes(newDishes);
        Menu menu = menuBuilder.builder();
        menuRepository.save(menu);
        return menu;
    }

    public Menu readMenu(Integer id) {
        if (id == null)
            throw new IllegalArgumentException("Id shouldn't be null");
        return menuRepository.get(id);
    }

}
