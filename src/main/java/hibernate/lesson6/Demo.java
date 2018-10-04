package hibernate.lesson6;

import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

        Product product = new Product();
        product.setId(1);
        product.setName("table new");
        product.setDescription("g & b");
        product.setPrice(70);

        //productDAO.update(product);

        Product product1 = new Product();
            product1.setId(4);
        product1.setName("prod 1 UPD3");
        product1.setDescription("dfdf");
        product1.setPrice(1140);

        Product product2 = new Product();
            product2.setId(5);
        product2.setName("prod 2 UPD3");
        product2.setDescription("g & b");
        product2.setPrice(241);

        Product product3 = new Product();
            product3.setId(6);
        product3.setName("prod 3 UPD3");
        product3.setDescription("g & b");
        product3.setPrice(560);

        List<Product> products = Arrays.asList(product1, product2, product3);
        //productDAO.saveAll(products);
        //productDAO.updateAll(products);
        //productDAO.deleteAll(products);

    }

}
