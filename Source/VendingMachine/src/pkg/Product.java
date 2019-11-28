package pkg;

public class Product {

    private int num;
    private String name;
    private int price;

    public Product(int num, String name, int price) {
        this.num = num;
        this.name = name;
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

}
