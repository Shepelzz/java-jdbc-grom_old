package lesson3.homework3_1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.ce5xbsungqgk.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "11111111";

    public List<Product> findProductsByPrice(int price, int delta){
        final String query = "SELECT * FROM PRODUCT WHERE PRICE BETWEEN ? AND ?";
        try(Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, price-delta);
            preparedStatement.setInt(2, price+delta);

            ResultSet rs = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();
            while(rs.next()){
                products.add(getProductFromResultSet(rs));
            }
            return products;
        }catch (SQLException e){
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> findProductsByName(String word){
        final String query = "SELECT * FROM PRODUCT WHERE NAME LIKE ?";
        try(Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, "%"+word+"%");

            ResultSet rs = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();
            while(rs.next()){
                products.add(getProductFromResultSet(rs));
            }
            return products;
        }catch (SQLException e){
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> findProductsWithEmptyDescription(){
        final String query = "SELECT * FROM PRODUCT WHERE DESCRIPTION IS NULL";
        try(Connection connection = getConnection();
                Statement statement = connection.createStatement()){
            List<Product> products = new ArrayList<>();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                products.add(getProductFromResultSet(rs));
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

    private Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(1);
        String name = resultSet.getString(2);
        String description = resultSet.getString(3);
        int price = resultSet.getInt(4);
        return new Product(id, name, description, price);
    }
}
