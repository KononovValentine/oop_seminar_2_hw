public class Product {
    private String name;
    private double cost;

    Product(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Наименование: " + this.name + ", Стоимость: " + cost;
    }


    // Сравнивает два товара по имени и стоимости
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Product product)) {
            return false;
        }
        return name.equals(product.name)
                && cost == product.cost;
    }
}
