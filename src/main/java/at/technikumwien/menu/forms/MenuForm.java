package at.technikumwien.menu.forms;

import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWarDeployment;
import org.springframework.lang.NonNull;

import java.util.List;

public class MenuForm {

    List<DishForm> dishes;
    String originalLanguage;
    String preferredLanguage;

    public List<DishForm> getDishes() {
        return dishes;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

}