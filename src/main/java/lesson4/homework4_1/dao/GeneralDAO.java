package lesson4.homework4_1.dao;

import java.sql.*;

public abstract class GeneralDAO {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.ce5xbsungqgk.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "11111111";
    private Connection connection = null;

    Connection createConnection() throws SQLException {
        return connection = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    Connection getConnection() throws SQLException{
        return connection;
    }

    long getNewEntityId(String sql) throws Exception{
        try(Connection conn = getConnection(); Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
                return rs.getLong(1);
            throw new Exception("id get fail"); //TODO error
        }catch (SQLException e){
            System.err.println("Something went wrong");
            throw e;
        }
    }
}
