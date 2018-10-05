package hibernate.lesson6.homework6_2;

public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

        System.out.println(productDAO.findById(7));
    }



}
