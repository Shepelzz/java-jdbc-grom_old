package lesson3.homework3_3;

import java.sql.*;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.ce5xbsungqgk.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "11111111";
    private static final String SQL_TEST_SAVE_PERFORMANCE =
            "INSERT INTO TEST_SPEED VALUES(?, ?, ?)";
    private static final String SQL_TEST_DELETE_BY_ID_PERFORMANCE =
            "DELETE FROM TEST_SPEED "+
            "WHERE ID = ?";
    private static final String SQL_TEST_DELETE_PERFORMANCE =
            "DELETE FROM TEST_SPEED "+
            "WHERE ID <= 1000";
    private static final String SQL_TEST_SELECT_BY_ID_PERFORMANCE =
            "SELECT * "+
            "FROM TEST_SPEED "+
            "WHERE ID = ?";
    private static final String SQL_TEST_SELECT_PERFORMANCE =
            "SELECT * "+
            "FROM TEST_SPEED "+
            "WHERE ID <= 1000";

    public void testSavePerformance(){
        //result: 125919
        Date start = new Date(), finish;
        long timeDiff;
        try(Connection conn = getConnection(); PreparedStatement prpStmt = conn.prepareStatement(SQL_TEST_SAVE_PERFORMANCE)){
            for(int i = 1; i <= 1000; i++){
                prpStmt.setInt(1, i);
                prpStmt.setString(2, "test_"+i);
                prpStmt.setInt(3, ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE));
                prpStmt.executeUpdate();
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
        Date start = new Date(), finish;
        long timeDiff;
        try(Connection conn = getConnection(); PreparedStatement prpStmt = conn.prepareStatement(SQL_TEST_DELETE_BY_ID_PERFORMANCE)){
            for(int i = 1; i <= 1000; i++){
                prpStmt.setInt(1, i);
                prpStmt.executeUpdate();
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
        Date start = new Date(), finish;
        long timeDiff;
        try(Connection conn = getConnection(); Statement stmt = conn.createStatement()){
            stmt.executeUpdate(SQL_TEST_DELETE_PERFORMANCE);
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
        Date start = new Date(), finish;
        long timeDiff;
        try(Connection conn = getConnection(); PreparedStatement prpStmt = conn.prepareStatement(SQL_TEST_SELECT_BY_ID_PERFORMANCE)){
            for(int i = 1; i <= 1000; i++){
                prpStmt.setInt(1, i);
                ResultSet rs = prpStmt.executeQuery();
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
        Date start = new Date(), finish;
        long timeDiff;
        try(Connection conn = getConnection(); Statement stmt = conn.createStatement()){
            ResultSet resultSet = stmt.executeQuery(SQL_TEST_SELECT_PERFORMANCE);
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
