package jdbc.lesson4;

import jdbc.lesson3.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TransactionDemo {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.ce5xbsungqgk.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "11111111";
    private static final String SQL_SAVE = "INSERT INTO PRODUCT VALUES(?, ?, ?, ?)";

    public static void save(List<Product> products) throws Exception{
        try(Connection conn = getConnection()){
            saveList(products, conn);
        }catch (Exception e){
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

                try {
                    prpStmt.executeUpdate();
                }catch (Exception e) {
                    throw new Exception("product with id " + product.getId() + " was not saved. "+e.getMessage());
                }
                System.out.println("product "+product.toString()+" was saved");
            }

            connection.commit();
        }catch (Exception e){
            connection.rollback();
            throw e;
        }
    }

    private static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
