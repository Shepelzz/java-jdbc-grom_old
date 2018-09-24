package lesson3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.ce5xbsungqgk.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "11111111";
    private static final String SQL_SAVE = "INSERT INTO PRODUCT VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE PRODUCT SET NAME = ?, DESCRIPTION = ?, PRICE = ? WHERE ID = ?";
    private static final String SQL_GET_PRODUCTS = "SELECT * FROM PRODUCT";
    private static final String SQL_DELETE = "DELETE FROM PRODUCT WHERE ID = ?";

    public Product save(Product product) throws Exception{
        try(Connection conn = getConnection(); PreparedStatement prpStmt = conn.prepareStatement(SQL_SAVE)){
            prpStmt.setLong(1, product.getId());
            prpStmt.setString(2, product.getName());
            prpStmt.setString(3, product.getDescription());
            prpStmt.setInt(4, product.getPrice());

            if(prpStmt.executeUpdate() == 0)
                throw new Exception("product with id "+product.getId()+" was not saved");
            return product;
        }catch (SQLException e){
            System.err.println("Something went wrong");
            throw new SQLException(e);
        }
    }

    public Product update(Product product) throws Exception{
        try(Connection conn = getConnection();  PreparedStatement prpStmt = conn.prepareStatement(SQL_UPDATE)){
            prpStmt.setString(1, product.getName());
            prpStmt.setString(2, product.getDescription());
            prpStmt.setInt(3, product.getPrice());
            prpStmt.setLong(4, product.getId());

            if(prpStmt.executeUpdate() == 0)
                throw new Exception("product with id "+product.getId()+" was not updated");
            return product;
        }catch (SQLException e){
            System.err.println("Something went wrong");
            throw new SQLException(e);
        }
    }

    public List<Product> getProducts() throws SQLException{
        try(Connection conn = getConnection(); Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(SQL_GET_PRODUCTS);
            List<Product> products = new ArrayList<>();
            while(rs.next()){
                products.add(new Product(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            return products;
        }catch (SQLException e){
            System.err.println("Something went wrong");
            throw new SQLException(e);
        }
    }

    public void delete(long id) throws Exception{
        try(Connection conn = getConnection(); PreparedStatement prpStmt = conn.prepareStatement(SQL_DELETE)){
            prpStmt.setLong(1, id);
            if(prpStmt.executeUpdate() == 0)
                throw new Exception("product with id "+id+" was not deleted");
        }catch (SQLException e){
            System.err.println("Something went wrong");
            throw new SQLException(e);
        }
    }

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

}