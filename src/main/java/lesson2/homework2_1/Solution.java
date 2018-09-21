package lesson2.homework2_1;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.ce5xbsungqgk.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "11111111";

    public static List<Product> getAllProducts(){
        final String query = "SELECT * FROM PRODUCT";
        try(Connection connection = getConnection();
                Statement statement = connection.createStatement()){
            List<Product> result = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(query);
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
        final String query = "SELECT * FROM PRODUCT WHERE PRICE <= 100";
        try(Connection connection = getConnection();
                Statement statement = connection.createStatement()){
            List<Product> result = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(query);
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
        final String query = "SELECT * FROM PRODUCT WHERE LENGTH(DESCRIPTION) > 50";
        try(Connection connection = getConnection();
                Statement statement = connection.createStatement()){
            List<Product> result = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(query);
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
