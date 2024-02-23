package at.technikumwien.menu;

import at.technikumwien.menu.builders.MenuBuilder;
import at.technikumwien.menu.objects.Menu;
import at.technikumwien.menu.utilities.DummyGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


@SpringBootTest
public class MenuBuilderTests {
    @Test
    public void testMenuBuilder() {
        int dishesSize = 5;
        String language = "Eng";
        DummyGenerator generator = new DummyGenerator();
        Menu menu = new MenuBuilder()
                .setDishes(generator.generateEnglishDishes(dishesSize))
                .setLanguage(language)
                .builder();

        assertDoesNotThrow(() -> {
            try {
                menu.setId(1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        assertThat(menu.getId()).isEqualTo(1);
        assertThat(menu.getLanguage()).isEqualTo(language);
        assertThat(menu.getDishes()).hasSize(dishesSize);
    }
}
