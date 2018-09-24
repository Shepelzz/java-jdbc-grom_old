package lesson2.homework2_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.ce5xbsungqgk.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "11111111";
    private static final String SQL_INCREASE_PRICE = "UPDATE PRODUCT SET PRICE = PRICE+100 WHERE PRICE > 1970";
    private static final String SQL_CHANGE_DESCRIPTION = "UPDATE PRODUCT SET DESCRIPTION = SUBSTR(DESCRIPTION, 1, INSTR(DESCRIPTION,'.',-1)-1) WHERE LENGTH(DESCRIPTION) > 100";

    public static void increasePrice() throws Exception{
        try(Connection conn = getConnection(); Statement stmt = conn.createStatement()){
            int res = stmt.executeUpdate(SQL_INCREASE_PRICE);
            if(res == 0){
                throw new Exception("no rows was updated");
            }
        }catch (SQLException e){
            System.err.println("Something went wrong");
            throw new SQLException(e);
        }
    }

    public static void changeDescription() throws Exception{
        try(Connection conn = getConnection(); Statement stmt = conn.createStatement()){
            int res = stmt.executeUpdate(SQL_CHANGE_DESCRIPTION);
            if(res == 0){
                throw new Exception("no rows was updated");
            }
        }catch (SQLException e){
            System.err.println("Something went wrong");
            throw new SQLException(e);
        }
    }

    private static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
