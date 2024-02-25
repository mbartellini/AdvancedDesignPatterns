package at.technikumwien.menu.repositories;

import at.technikumwien.menu.objects.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuRepository {

    private static MenuRepository singleton;
    private List<Menu> menues = new ArrayList<>();

    private MenuRepository() {

    }

    public static MenuRepository getInstance() {
        if (singleton == null)
            singleton = new MenuRepository();
        return singleton;
    }

    public void save(Menu menu) {
        if (menu.getId() == null) {
            try {
                menu.setId(menues.size());
                menues.add(menu);
            } catch (Exception e) {
                throw new RuntimeException(e); // TODO: Check
            }
        } else {
            menues.set(menu.getId(), menu);
        }
    }

    public Menu get(int id) {
        if (id < 0 || id >= menues.size())
            throw new ArrayIndexOutOfBoundsException("Id non existent");
        return menues.get(id);
    }

}
