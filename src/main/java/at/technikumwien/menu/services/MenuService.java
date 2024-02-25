package at.technikumwien.menu.services;

import at.technikumwien.menu.builders.MenuBuilder;
import at.technikumwien.menu.forms.DishForm;
import at.technikumwien.menu.forms.MenuForm;
import at.technikumwien.menu.interfaces.CustomTranslator;
import at.technikumwien.menu.objects.Dish;
import at.technikumwien.menu.objects.Menu;
import at.technikumwien.menu.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private CustomTranslator translator;

    private static MenuService singleton;

    private MenuService(){

    }

    public static MenuService getInstance() {
        if (singleton == null)
            singleton = new MenuService();
        return singleton;
    }

    public Menu createMenu(MenuForm menuForm) throws Exception {
        MenuBuilder menuBuilder = new MenuBuilder();
        menuBuilder.setLanguage(menuForm.getPreferredLanguage());
        List<Dish> newDishes = new ArrayList<>();
        for (int i = 0; i < menuForm.getDishes().size(); i++) {
            DishForm dishForm = menuForm.getDishes().get(i);
            Dish newDish = createDish(dishForm, menuForm.getOriginalLanguage(), menuForm.getPreferredLanguage());
            newDishes.add(newDish);
        }
        menuBuilder.setDishes(newDishes);
        Menu menu = menuBuilder.builder();
        menuRepository.save(menu);
        return menu;
    }

    private Dish createDish(DishForm dishForm, String fromLanguage, String toLanguage) throws Exception {
        String dishNameTranslated = translator.translate(dishForm.getName(), fromLanguage, toLanguage);
        return new Dish(0, dishNameTranslated, dishForm.getCost(), dishForm.getCurrency());
    }

    public Menu readMenu(Integer id) {
        if (id == null)
            throw new IllegalArgumentException("Id shouldn't be null");
        return menuRepository.get(id);
    }

}
