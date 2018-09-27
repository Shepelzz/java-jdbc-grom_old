package lesson4.homework4_1.dao;

import lesson4.homework4_1.exception.InternalServerError;

import java.sql.*;

public abstract class GeneralDAO {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.ce5xbsungqgk.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "11111111";

    void delete(long id, String sql) throws InternalServerError, SQLException{
        try(Connection conn = getConnection(); PreparedStatement prpStmt = conn.prepareStatement(sql)){
            prpStmt.setLong(1, id);
            if(prpStmt.executeUpdate() == 0)
                throw new InternalServerError(getClass().getName()+"-delete","entity with id "+id+" was not deleted");
        }catch (SQLException e){
            throw e;
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    long getNewEntityId(String sql) throws InternalServerError, SQLException{
        try(Connection conn = getConnection(); Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
                return rs.getLong(1);
            throw new InternalServerError(getClass().getName()+"-getNewEntityId","id get fail");
        }catch (SQLException e){
            System.err.println("Something went wrong");
            throw e;
        }
    }
}
