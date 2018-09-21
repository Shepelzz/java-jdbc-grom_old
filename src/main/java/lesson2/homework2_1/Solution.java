package lesson2.homework2_1;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.ce5xbsungqgk.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "11111111";

    public static Set<Product> getAllProducts(){
        Set<Product> result = new LinkedHashSet<>();
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()){
            try(ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT")){
                while(resultSet.next()){
                    result.add(getProductFromResultSet(resultSet));
                }
            }
        }catch (SQLException e){
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return result;
    }

    public static Set<Product> getProductsByPrice(){
        Set<Product> result = new LinkedHashSet<>();
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()){
            try(ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT WHERE PRICE <= 100")){
                while(resultSet.next()){
                    result.add(getProductFromResultSet(resultSet));
                }
            }
        }catch (SQLException e){
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return result;
    }

    public static Set<Product> getProductsByDescription(){
        Set<Product> result = new LinkedHashSet<>();
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()){
            try(ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT WHERE LENGTH(DESCRIPTION) > 50")){
                while(resultSet.next()){
                    result.add(getProductFromResultSet(resultSet));
                }
            }
        }catch (SQLException e){
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return result;
    }

    private static Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(1);
        String name = resultSet.getString(2);
        Clob description = resultSet.getClob(3);
        int price = resultSet.getInt(4);
        return new Product(id, name, description, price);
    }

}
