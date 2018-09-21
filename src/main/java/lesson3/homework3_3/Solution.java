package lesson3.homework3_3;

import java.sql.*;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.ce5xbsungqgk.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "11111111";

    public void testSavePerformance(){
        //result: 125919
        final String query = "INSERT INTO TEST_SPEED VALUES(?, ?, ?)";

        Date start = new Date(), finish;
        long timeDiff;
        try(Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)){

            for(int i = 1; i <= 1000; i++){
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, "test_"+i);
                preparedStatement.setInt(3, ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE));
                preparedStatement.executeUpdate();
            }
        }catch (SQLException e){
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        finish = new Date();
        timeDiff = finish.getTime()-start.getTime();
        System.out.println("testSavePerformance: "+timeDiff);
    }

    public void testDeleteByIdPerformance(){
        //result: 125624
        final String query = "DELETE FROM TEST_SPEED WHERE ID = ?";

        Date start = new Date(), finish;
        long timeDiff;
        try(Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)){

            for(int i = 1; i <= 1000; i++){
                preparedStatement.setInt(1, i);
                preparedStatement.executeUpdate();
            }
        }catch (SQLException e){
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        finish = new Date();
        timeDiff = finish.getTime()-start.getTime();
        System.out.println("testSavePerformance: "+timeDiff);
    }

    public void testDeletePerformance(){
        //result: 2145
        final String query = "DELETE FROM TEST_SPEED WHERE ID <= 1000";

        Date start = new Date(), finish;
        long timeDiff;
        try(Connection connection = getConnection();
                Statement statement = connection.createStatement()){

            statement.executeUpdate(query);
        }catch (SQLException e){
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        finish = new Date();
        timeDiff = finish.getTime()-start.getTime();
        System.out.println("testSavePerformance: "+timeDiff);
    }

    public void testSelectByIdPerformance(){
        //result: 124387
        final String query = "SELECT * FROM TEST_SPEED WHERE ID = ?";

        Date start = new Date(), finish;
        long timeDiff;
        try(Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)){

            for(int i = 1; i <= 1000; i++){
                preparedStatement.setInt(1, i);
                ResultSet rs = preparedStatement.executeQuery();
            }
        }catch (SQLException e){
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        finish = new Date();
        timeDiff = finish.getTime()-start.getTime();
        System.out.println("testSavePerformance: "+timeDiff);
    }

    public void testSelectPerformance(){
        //result: 2201
        final String query = "SELECT * FROM TEST_SPEED WHERE ID <= 1000";

        Date start = new Date(), finish;
        long timeDiff;
        try(Connection connection = getConnection();
                Statement statement = connection.createStatement()){

            ResultSet resultSet = statement.executeQuery(query);
        }catch (SQLException e){
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        finish = new Date();
        timeDiff = finish.getTime()-start.getTime();
        System.out.println("testSavePerformance: "+timeDiff);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
