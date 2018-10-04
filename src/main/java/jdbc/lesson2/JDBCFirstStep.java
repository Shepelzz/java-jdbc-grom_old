package jdbc.lesson2;

import java.sql.*;

public class JDBCFirstStep {
    //install:install-file -Dfile=C:\\JDBC\\ojdbc7.jar -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0.1 -Dpackaging=jar

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.ce5xbsungqgk.us-east-2.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "main";
    private static final String PASS = "11111111";

    static {
        try{
            Class.forName(JDBC_DRIVER);
        }catch (ClassNotFoundException e){
            System.err.println("Class "+JDBC_DRIVER+" not found");
        }
    }

    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()){
            try(ResultSet resultSet = statement.executeQuery("SELECT * FROM ORDERS WHERE PRICE > 4000 ")){
                while(resultSet.next()){
                    long id = resultSet.getLong(1);
                    String productName = resultSet.getString(2);
                    int price = resultSet.getInt(3);
                    Date dateOrdered = resultSet.getDate(4);
                    Date dateConfirmed = resultSet.getDate(5);
                    Order order = new Order(id, productName, price, dateOrdered, dateConfirmed);
                    System.out.println(order.toString());
                }
            }
        }catch (SQLException e){
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }




}
