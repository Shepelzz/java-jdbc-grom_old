package lesson2.homework2_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.ce5xbsungqgk.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "11111111";

    public static void increasePrice(){
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()){
            statement.executeUpdate("UPDATE PRODUCT SET PRICE = PRICE+100 WHERE PRICE < 970");
        }catch (SQLException e){
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public static void changeDescription(){
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()){
            statement.executeUpdate("UPDATE PRODUCT SET DESCRIPTION = SUBSTR(DESCRIPTION, 1, INSTR(DESCRIPTION,' ',-1)-1) WHERE LENGTH(DESCRIPTION) > 100");
        }catch (SQLException e){
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }
}
