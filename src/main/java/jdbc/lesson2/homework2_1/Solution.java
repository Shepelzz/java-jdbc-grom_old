package jdbc.lesson2.homework2_1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.ce5xbsungqgk.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "11111111";
    private static final String SQL_GET_ALL_PRODUCTS = "SELECT * FROM PRODUCT";
    private static final String SQL_GET_PRODUCTS_BY_PRICE =
            "SELECT * "+
            "FROM PRODUCT "+
            "WHERE PRICE <= 100";
    private static final String SQL_GET_PRODUCTS_BY_DESCRIPTION =
            "SELECT * "+
            "FROM PRODUCT "+
            "WHERE LENGTH(DESCRIPTION) > 50";

    public static List<Product> getAllProducts(){
        try(Connection conn = getConnection(); Statement stmt = conn.createStatement()){
            List<Product> result = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery(SQL_GET_ALL_PRODUCTS);
            while(resultSet.next()){
                result.add(getProductFromResultSet(resultSet));
            }
            return result;
        }catch (SQLException e){
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    public static List<Product> getProductsByPrice(){
        try(Connection conn = getConnection(); Statement stmt = conn.createStatement()){
            List<Product> result = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery(SQL_GET_PRODUCTS_BY_PRICE);
            while(resultSet.next()){
                result.add(getProductFromResultSet(resultSet));
            }
            return result;
        }catch (SQLException e){
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    public static List<Product> getProductsByDescription(){
        try(Connection conn = getConnection(); Statement stmt = conn.createStatement()){
            List<Product> result = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery(SQL_GET_PRODUCTS_BY_DESCRIPTION);
            while(resultSet.next()) {
                result.add(getProductFromResultSet(resultSet));
            }
            return result;
        }catch (SQLException e){
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    private static Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(1);
        String name = resultSet.getString(2);
        String description = resultSet.getString(3);
        int price = resultSet.getInt(4);
        return new Product(id, name, description, price);
    }

    private static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

}
