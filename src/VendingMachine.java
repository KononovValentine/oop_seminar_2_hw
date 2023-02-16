import java.util.ArrayList;
import java.util.Scanner;

public class VendingMachine {
    Scanner scanner = new Scanner(System.in);
    private final String[] places = {"a1", "a2", "a3", "b1", "b2", "b3", "c1", "c2", "c3"};
    private final ArrayList<Product> a1 = new ArrayList<>();
    private final ArrayList<Product> a2 = new ArrayList<>();
    private final ArrayList<Product> a3 = new ArrayList<>();
    private final ArrayList<Product> b1 = new ArrayList<>();
    private final ArrayList<Product> b2 = new ArrayList<>();
    private final ArrayList<Product> b3 = new ArrayList<>();
    private final ArrayList<Product> c1 = new ArrayList<>();
    private final ArrayList<Product> c2 = new ArrayList<>();
    private final ArrayList<Product> c3 = new ArrayList<>();
    private boolean isDoorOpen = false;
    private int password = 1111;

    VendingMachine() {
    }

    // добавляет заданное количество продуктов по позиции
    public void addProductByPlace(String placeName, int count, Product product) {
        if (isDoorOpen) {
            ArrayList<Product> selectList = getPlaceByName(placeName);
            if (selectList.isEmpty()) {
                for (int i = 0; i < count; i++) {
                    selectList.add(product);
                }
            } else if (selectList.get(0).equals(product)) {
                for (int i = 0; i < count; i++) {
                    selectList.add(product);
                }
            } else {
                System.out.println("Ошибка: В данной ячейке находятся товары другой категории.");
            }
        } else {
            System.out.println("Ошибка: Перед добавлением товара откройте дверцу.");
        }
    }

    // Запрашивает покупку продукта по выбранному параметру
    public void buyProduct() {
        if (!isDoorOpen) {
            System.out.println("Введите 1 для покупки по позиции товара, 2 для покупки по цене товара, " +
                    "3 для покупки по имени товара");
            int number = Integer.parseInt(scanner.nextLine());
            if (number == 1) {
                System.out.println("Введите позицию товара из представленных: ");
                String[] places = getAllPlaces();
                for (String place : places) {
                    System.out.println(place);
                }
                String place = scanner.nextLine();
                Product product = getProductByPlace(place);
                System.out.println("Возьмите товар: " + product.toString());
            }
            if (number == 2) {
                System.out.println("Введите цену товара из представленных: ");
                double[] costs = getAllCosts();
                for (double cost : costs) {
                    System.out.println(cost);
                }
                double cost = Double.parseDouble(scanner.nextLine());
                Product product = getProductByCost(cost);
                System.out.println("Возьмите товар: " + product.toString());
            }
            if (number == 3) {
                System.out.println("Введите наименование товара из представленных: ");
                String[] names = getAllNames();
                for (String name : names) {
                    System.out.println(name);
                }
                String name = scanner.nextLine();
                Product product = getProductByName(name);
                System.out.println("Возьмите товар: " + product.toString());
            }
        } else {
            System.out.println("Ошибка: Дверца не закрыта!");
        }
    }

    // Открывает дверь
    public void openDoor() {
        if (!isDoorOpen) {
            System.out.println("Для открытия дверцы введите пин-код администратора: ");
            int pass = Integer.parseInt(scanner.nextLine());
            if (pass == password) {
                isDoorOpen = true;
                System.out.println("Дверца открыта.");
            }
        } else {
            System.out.println("Ошибка: Дверца уже открыта!");
        }
    }

    // Закрывает дверь
    public void closeDoor() {
        if (isDoorOpen) {
            isDoorOpen = false;
            System.out.println("Дверца закрыта.");
        } else {
            System.out.println("Ошибка: Дверца уже закрыта!");
        }
    }

    // Меняет пин-код администратора
    public void changePassword() {
        System.out.println("Для смены пин-кода, введите текущий пин-код администратора (по умолчанию 1111): ");
        int pass = Integer.parseInt(scanner.nextLine());
        if (pass == password) {
            while (true) {
                System.out.println("Введите новый пин-код: ");
                int newPass = Integer.parseInt(scanner.nextLine());
                if (newPass == password) {
                    System.out.println("Ошибка: Старый пин-код не может совпадать с текущим!");
                } else {
                    while (true) {
                        System.out.println("Подтвердите новый пин-код введя его повторно: ");
                        int newPassRewrite = Integer.parseInt(scanner.nextLine());
                        if (newPassRewrite == newPass) {
                            password = newPass;
                            break;
                        } else {
                            System.out.println("Ошибка: Повторный ввод не совпадает, пожалуйста повторите: ");
                        }
                    }
                    System.out.println("Пин-код успешно изменен.");
                    break;
                }
            }
        } else {
            System.out.println("Ошибка: Введен не верный пин-код!");
        }
    }

    // Выдает один продукт по имени
    private Product getProductByName(String name) {
        ArrayList<ArrayList<Product>> productLists = getCommonList();
        Product result = new Product("", 0);
        for (ArrayList<Product> selectList : productLists) {
            if (!selectList.isEmpty()) {
                if (selectList.get(0).getName().equals(name)) {
                    result = selectList.get(0);
                    selectList.remove(0);
                }
            }
        }
        return result;
    }

    // Выдает один продукт по цене
    private Product getProductByCost(double cost) {
        ArrayList<ArrayList<Product>> productLists = getCommonList();
        Product result = new Product("", 0);
        for (ArrayList<Product> selectList : productLists) {
            if (!selectList.isEmpty()) {
                if (selectList.get(0).getCost() == cost) {
                    result = selectList.get(0);
                    selectList.remove(0);
                }
            }
        }
        return result;
    }

    // Выдает один продукт по позиции
    private Product getProductByPlace(String placeName) {
        ArrayList<Product> selectList = getPlaceByName(placeName);
        Product result = new Product("", 0);
        if (!selectList.isEmpty()) {
            result = selectList.get(0);
            selectList.remove(0);
        }
        return result;
    }

    // Возвращает список всех позиций
    private ArrayList<ArrayList<Product>> getCommonList() {
        ArrayList<ArrayList<Product>> list = new ArrayList<>();
        if (!a1.isEmpty()) list.add(a1);
        if (!a2.isEmpty()) list.add(a2);
        if (!a3.isEmpty()) list.add(a3);
        if (!c1.isEmpty()) list.add(c1);
        if (!c2.isEmpty()) list.add(c2);
        if (!c3.isEmpty()) list.add(c3);
        if (!b1.isEmpty()) list.add(b1);
        if (!b2.isEmpty()) list.add(b2);
        if (!b3.isEmpty()) list.add(b3);
        return list;
    }

    // Возвращает список товаров на определенной позиции
    private ArrayList<Product> getPlaceByName(String name) {
        ArrayList<Product> result = new ArrayList<>();
        switch (name) {
            case "a1" -> result = a1;
            case "a2" -> result = a2;
            case "a3" -> result = a3;
            case "b1" -> result = b1;
            case "b2" -> result = b2;
            case "b3" -> result = b3;
            case "c1" -> result = c1;
            case "c2" -> result = c2;
            case "c3" -> result = c3;
        }
        return result;
    }

    // Возвращает список всех позиций товаров
    private String[] getAllPlaces() {
        ArrayList<ArrayList<Product>> productLists = getCommonList();
        String[] result = new String[productLists.size()];
        int index = 0;
        for (int i = 0; i < places.length; i++) {
            if (!getPlaceByName(places[i]).isEmpty()) {
                result[index] = places[i];
                index++;
            }
        }
        return result;
    }

    // Возвращает список всех цен товаров
    private double[] getAllCosts() {
        ArrayList<ArrayList<Product>> productLists = getCommonList();
        double[] result = new double[productLists.size()];
        for (int i = 0; i < productLists.size(); i++) {
            result[i] = productLists.get(i).get(0).getCost();
        }
        return result;
    }

    // Возвращает список всех имен товаров
    private String[] getAllNames() {
        ArrayList<ArrayList<Product>> productLists = getCommonList();
        String[] result = new String[productLists.size()];
        for (int i = 0; i < productLists.size(); i++) {
            result[i] = productLists.get(i).get(0).getName();
        }
        return result;
    }
}
