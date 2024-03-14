package at.technikumwien.menu.forms;

public class DishForm {

    String name;
    double cost;
    String currency;

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public String getCurrency() {
        return currency;
    }

    public DishForm(String name, double cost, String currency) {
        this.name =  name;
        this.cost =  cost;
        this.currency =  currency;
    }
}
