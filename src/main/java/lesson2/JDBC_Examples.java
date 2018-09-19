package lesson2;

import java.sql.*;

public class JDBC_Examples {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.ce5xbsungqgk.us-east-2.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "main";
    private static final String PASS = "11111111";

    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()){
//            boolean res = statement.execute("INSERT INTO PRODUCT VALUES(2, 'toy', 'for children', 60)");
//            System.out.println(res);

//            boolean res1 = statement.execute("DELETE FROM PRODUCT WHERE ID = 2");
//            System.out.println(res1);

            int res = statement.executeUpdate("INSERT INTO PRODUCT VALUES(2, 'toy2', 'for children', 150)");
            System.out.println(res);

//            int res1 = statement.executeUpdate("DELETE FROM PRODUCT WHERE NAME = 'dfg'");
//            System.out.println(res1);


        }catch (SQLException e){
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }
}
