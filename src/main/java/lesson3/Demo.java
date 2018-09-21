package lesson3;

public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        Product product = new Product(12,"newProd12", "some description", 90);
        //productDAO.save(product);
        //System.out.println(productDAO.getProducts());
        //productDAO.update(product);
        //productDAO.delete(12);
    }
}
