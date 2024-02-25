package at.technikumwien.menu.objects;

public class Dish {
    int number;
    String name;
    double cost;
    String currency;
    public Dish(int number, String name, double cost, String currency) {
        this.number = number;
        this.name = name;
        this.cost = cost;
        this.currency = currency;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public String getCurrency() {
        return currency;
    }
}
