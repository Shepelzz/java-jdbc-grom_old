package hibernate.lesson6.homework6_1;

public class Demo {
    public static void main(String[] args) throws Exception{
        ProductDAO productDAO = new ProductDAO();

        //System.out.println(productDAO.findById(7));
        System.out.println(productDAO.findByName("prod").toString());
        //System.out.println(productDAO.findByContainedName("pr").toString());
        System.out.println(productDAO.findByPrice(200, 400).toString());

        System.out.println(productDAO.findByNameSortedAsc("o"));
//        System.out.println(productDAO.findByNameSortedDesc("o"));

        System.out.println(productDAO.findByPriceSortedDesc(200, 400).toString());
    }
}
