package lesson3.homework3_1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.ce5xbsungqgk.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "11111111";
    private static final String SQL_FIND_PRODUCT_BY_PRICE = "SELECT * FROM PRODUCT WHERE PRICE BETWEEN ? AND ?";
    private static final String SQL_FIND_PRODUCT_BY_NAME = "SELECT * FROM PRODUCT WHERE NAME LIKE ?";
    private static final String SQL_FIND_PRODUCTS_WITH_EMPTY_DESCRIPTION = "SELECT * FROM PRODUCT WHERE DESCRIPTION IS NULL";

    public List<Product> findProductsByPrice(int price, int delta) throws SQLException{
        try(Connection conn = getConnection(); PreparedStatement prpStmt = conn.prepareStatement(SQL_FIND_PRODUCT_BY_PRICE)){
            prpStmt.setInt(1, price-delta);
            prpStmt.setInt(2, price+delta);

            List<Product> products = new ArrayList<>();
            ResultSet rs = prpStmt.executeQuery();
            while(rs.next()){
                products.add(getProductFromResultSet(rs));
            }
            return products;
        }catch (SQLException e){
            System.err.println("Something went wrong");
            throw e;
        }
    }

    public List<Product> findProductsByName(String word) throws SQLException{
        try(Connection conn = getConnection(); PreparedStatement prpStmt = conn.prepareStatement(SQL_FIND_PRODUCT_BY_NAME)){
            prpStmt.setString(1, "%"+word+"%");

            List<Product> products = new ArrayList<>();
            ResultSet rs = prpStmt.executeQuery();
            while(rs.next()){
                products.add(getProductFromResultSet(rs));
            }
            return products;
        }catch (SQLException e){
            System.err.println("Something went wrong");
            throw e;
        }
    }

    public List<Product> findProductsWithEmptyDescription() throws SQLException{
        try(Connection conn = getConnection(); Statement stmt = conn.createStatement()){
            List<Product> products = new ArrayList<>();
            ResultSet rs = stmt.executeQuery(SQL_FIND_PRODUCTS_WITH_EMPTY_DESCRIPTION);
            while(rs.next()){
                products.add(getProductFromResultSet(rs));
            }
            return products;
        }catch (SQLException e){
            System.err.println("Something went wrong");
            throw e;
        }
    }

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(1);
        String name = resultSet.getString(2);
        String description = resultSet.getString(3);
        int price = resultSet.getInt(4);
        return new Product(id, name, description, price);
    }
}
