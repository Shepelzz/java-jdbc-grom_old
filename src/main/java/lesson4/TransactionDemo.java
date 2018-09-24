package lesson4;

import lesson3.Product;

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

    public void save(List<Product> products) throws Exception{
        try(Connection conn = getConnection(); PreparedStatement prpStmt = conn.prepareStatement(SQL_SAVE)){
            conn.setAutoCommit(false);

            for(Product product : products) {
                prpStmt.setLong(1, product.getId());
                prpStmt.setString(2, product.getName());
                prpStmt.setString(3, product.getDescription());
                prpStmt.setInt(4, product.getPrice());

                if (prpStmt.executeUpdate() == 0)
                    throw new Exception("product with id " + product.getId() + " was not saved");


            }

            conn.commit();
        }catch (SQLException e){
            System.err.println("Something went wrong");
            throw new SQLException(e);
        }
    }

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
