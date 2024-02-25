package at.technikumwien.menu.objects;


import java.util.List;

public class Menu {
    Integer id;
    List<Dish> dishes;
    String language;


    public Menu(List<Dish> dishes, String language) {
        this.dishes = dishes;
        this.language = language;
    }

    public void setId(Integer id) throws Exception {
        if(this.id != null) {
            throw new Exception("Id is already set can not overwrite it");
        }
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public List<Dish> getDishes() {
        return dishes;
    }
    public String getLanguage() {
        return language;
    }
}
