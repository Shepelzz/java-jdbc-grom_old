package hibernate.lesson6;

import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

        Product product = new Product();
        //product.setId(1);
        product.setName("product new");
        product.setDescription("g & b");
        product.setPrice(70);
        //System.out.println(productDAO.save(product));


        Product product1 = new Product();
            //product1.setId(4);
        product1.setName("traktor");
        product1.setDescription("dfdf");
        product1.setPrice(140);

        Product product2 = new Product();
            //product2.setId(5);
        product2.setName("kot");
        product2.setDescription("4");
        product2.setPrice(455);

        Product product3 = new Product();
            //product3.setId(6);
        product3.setName("gogy");
        product3.setDescription("rettttt");
        product3.setPrice(60);

        List<Product> products = Arrays.asList(product1, product2, product3);
        productDAO.saveAll(products);
        //productDAO.updateAll(products);
        //productDAO.deleteAll(products);

    }

}
