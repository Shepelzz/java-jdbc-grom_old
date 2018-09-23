package lesson3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.ce5xbsungqgk.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "11111111";
    private static final String SQL_SAVE =
            "INSERT INTO PRODUCT VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE =
            "UPDATE PRODUCT "+
            "SET NAME = ?, DESCRIPTION = ?, PRICE = ? "+
            "WHERE ID = ?";
    private static final String SQL_GET_PRODUCTS =
            "SELECT * FROM PRODUCT";
    private static final String SQL_DELETE =
            "DELETE FROM PRODUCT WHERE ID = ?";

    public Product save(Product product){
        try(Connection conn = getConnection(); PreparedStatement prpStmt = conn.prepareStatement(SQL_SAVE)){
            prpStmt.setLong(1, product.getId());
            prpStmt.setString(2, product.getName());
            prpStmt.setString(3, product.getDescription());
            prpStmt.setInt(4, product.getPrice());

            int res = prpStmt.executeUpdate();
            System.out.println("save was finished with result "+res);
        }catch (SQLException e){
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return product;
    }

    public Product update(Product product){
        try(Connection conn = getConnection();  PreparedStatement prpStmt = conn.prepareStatement(SQL_UPDATE)){
            prpStmt.setString(1, product.getName());
            prpStmt.setString(2, product.getDescription());
            prpStmt.setInt(3, product.getPrice());
            prpStmt.setLong(4, product.getId());

            int res = prpStmt.executeUpdate();
            System.out.println("update was finished with result "+res);
        }catch (SQLException e){
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> getProducts(){
        try(Connection conn = getConnection(); Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(SQL_GET_PRODUCTS);
            List<Product> products = new ArrayList<>();
            while(rs.next()){
                products.add(new Product(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            return products;
        }catch (SQLException e){
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    public void delete(long id){
        try(Connection conn = getConnection(); PreparedStatement prpStmt = conn.prepareStatement(SQL_DELETE)){
            prpStmt.setLong(1, id);
            prpStmt.executeUpdate();
        }catch (SQLException e){
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

}