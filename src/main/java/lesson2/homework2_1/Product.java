package lesson2.homework2_1;

import java.sql.Clob;

public class Product {
    private long id;
    private String name;
    private Clob description;
    private int price;

    public Product(long id, String name, Clob description, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Clob getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description=" + description +
                ", price=" + price +
                '}';
    }
}
