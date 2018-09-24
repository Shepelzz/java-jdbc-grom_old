package lesson4;

import lesson3.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDemo {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.ce5xbsungqgk.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "11111111";
    private static final String SQL_SAVE = "INSERT INTO PRODUCT VALUES(?, ?, ?, ?)";

    public static void main(String[] args) throws Exception{
        Product product1 = new Product(55, "name1", "test", 90);
        Product product2 = new Product(66, "name2", "test1", 940);
        Product product3 = new Product(66, "name3", "test232", 978);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);

        save(products);
    }

    public static void save(List<Product> products) throws Exception{
        try(Connection conn = getConnection()){
            saveList(products, conn);
        }catch (SQLException e){
            System.err.println("Something went wrong");
            throw e;
        }
    }

    private static void saveList(List<Product> products, Connection connection) throws Exception{
        try(PreparedStatement prpStmt = connection.prepareStatement(SQL_SAVE)){
            connection.setAutoCommit(false);

            for(Product product : products) {
                prpStmt.setLong(1, product.getId());
                prpStmt.setString(2, product.getName());
                prpStmt.setString(3, product.getDescription());
                prpStmt.setInt(4, product.getPrice());

                if (prpStmt.executeUpdate() == 0)
                    throw new Exception("product with id " + product.getId() + " was not saved");
                System.out.println("product "+product.toString()+" was saved");
            }

            connection.commit();
        }catch (SQLException e){
            connection.rollback();
            throw e;
        }
    }

    private static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
