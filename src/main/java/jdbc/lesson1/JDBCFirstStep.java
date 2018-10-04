package jdbc.lesson1;

import java.sql.*;

public class JDBCFirstStep {
    //install:install-file -Dfile=C:\\Temp\\ojdbc7.jar -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0.1 -Dpackaging=jar

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.ce5xbsungqgk.us-east-2.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "main";
    private static final String PASS = "11111111";

    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()){
            //1. DB driver
            //2. Create connection
            //3. Create query
            //4. Execute query
            //5. Work with result
            //6. Close all connections

            try{
                Class.forName(JDBC_DRIVER);
            }catch (ClassNotFoundException e){
                System.err.println("Class "+JDBC_DRIVER+" not found");
                return;
            }

            try(ResultSet resultSet = statement.executeQuery("SELECT * FROM ORDERS")){
                while(resultSet.next()){
                    System.out.println(resultSet.getString(2));
                }
            }
        }catch (SQLException e){
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }




}
