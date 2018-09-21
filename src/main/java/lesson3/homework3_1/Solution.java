package lesson3.homework3_1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.ce5xbsungqgk.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "11111111";

    public List<Product> findProductsByPrice(int price, int delta){
        try(Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PRODUCT WHERE PRICE BETWEEN ? AND ?")){
            preparedStatement.setInt(1, price-delta);
            preparedStatement.setInt(2, price+delta);

            ResultSet rs = preparedStatement.executeQuery();
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

    public List<Product> findProductsByName(String word){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PRODUCT WHERE NAME LIKE ?")){
            preparedStatement.setString(1, "%"+word+"%");

            ResultSet rs = preparedStatement.executeQuery();
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

    public List<Product> findProductsWithEmptyDescription(){
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()){

            ResultSet rs = statement.executeQuery("SELECT * FROM PRODUCT WHERE DESCRIPTION IS NULL");
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

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
