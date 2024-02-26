package at.technikumwien.menu.services;

import at.technikumwien.menu.forms.DishForm;
import at.technikumwien.menu.forms.MenuForm;
import at.technikumwien.menu.interfaces.CustomTranslator;
import at.technikumwien.menu.objects.Dish;
import at.technikumwien.menu.objects.Menu;
import at.technikumwien.menu.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private CustomTranslator translator;

    private MenuService(){

    }

    public Menu createMenu(MenuForm menuForm) throws Exception {
        List<Dish> newDishes = menuForm.getDishes().stream().map(df -> {
            try {
                return createDish(df, menuForm.getOriginalLanguage(), menuForm.getPreferredLanguage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
        Menu menu = Menu.builder()
                .addDishes(newDishes)
                .setLanguage(menuForm.getPreferredLanguage())
                .build();
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
