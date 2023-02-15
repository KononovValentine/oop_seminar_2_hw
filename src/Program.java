public class Program {
    public static void main(String[] args) {

        String[] names = {"Cola", "Fanta", "Sprite", "Lipton", "Dobriy-Malina", "Dobriy-Yabloko", "Dobriy-Vishnya",
                "Dobriy-Apelsin", "Dobriy-Meltifruct"};
        double[] costs = {120.0, 119.0, 113.0, 80.0, 90.0, 91.0, 92.0, 93.0, 95.0};
        String[] places = {"a1", "a2", "a3", "b1", "b2", "b3", "c1", "c2", "c3"};
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.changePassword();
        vendingMachine.openDoor();
        Product prod = new Product("Cola", 120.0);
        vendingMachine.addProductByPlace("a1", 1, prod);
        for (int i = 0; i < names.length; i++) {
            Product product = new Product(
                    names[i],
                    costs[i]
            );
            vendingMachine.addProductByPlace(places[i], 10, product);
        }
        vendingMachine.buyProduct();
        vendingMachine.closeDoor();
        vendingMachine.buyProduct();
    }
}