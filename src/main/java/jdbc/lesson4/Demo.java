package jdbc.lesson4;

import jdbc.lesson3.Product;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) throws Exception{
        Product product1 = new Product(158, "name1", "test", 90);
        Product product2 = new Product(158, "name2", "test1", 940);
        Product product3 = new Product(169, "name3", "test232", 978);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);

        try {
            TransactionDemo.save(products);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
