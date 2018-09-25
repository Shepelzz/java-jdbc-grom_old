package lesson4.homework4_1.dao;

import lesson4.homework4_1.exception.BadRequestException;
import lesson4.homework4_1.model.File;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileDAO extends GeneralDAO{
    private static final String SQL_SAVE = "INSERT INTO FILES VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE FILES SET NAME = ?, FORMAT = ?, FILE_SIZE = ?, STORAGE_ID = ? WHERE ID = ?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM FILES WHERE ID = ?";
    private static final String SQL_DELETE = "DELETE FROM FILES WHERE ID = ?";
    private static final String SQL_GET_ID = "SELECT FILE_ID_SEQ.NEXTVAL FROM DUAL";
    private static final String SQL_GET_ALL_BY_STORAGE = "SELECT * FROM FILES WHERE STORAGE_ID = ?";

    public File save(File file) throws Exception{
        try(Connection conn = getConnection(); PreparedStatement prpStmt = conn.prepareStatement(SQL_SAVE)){
            file.setId(getNewEntityId(SQL_GET_ID));

            prpStmt.setLong(1, file.getId());
            prpStmt.setString(2, file.getName());
            prpStmt.setString(3, file.getFormat());
            prpStmt.setLong(4, file.getSize());
            prpStmt.setLong(5, file.getStorage().getId());

            if(prpStmt.executeUpdate() == 0)
                throw new Exception("entity with id "+file.getId()+" was not saved");
            return file;
        }catch (SQLException e){
            throw e;
        }
    }

    public File update(File file) throws Exception{
        try(Connection conn = getConnection();  PreparedStatement prpStmt = conn.prepareStatement(SQL_UPDATE)){
            prpStmt.setString(1, file.getName());
            prpStmt.setString(2, file.getFormat());
            prpStmt.setLong(3, file.getSize());
            prpStmt.setLong(4, file.getStorage().getId());
            prpStmt.setLong(5, file.getId());

            if(prpStmt.executeUpdate() == 0)
                throw new Exception("entity with id "+file.getId()+" was not updated");
            return file;
        }catch (SQLException e){
            throw e;
        }
    }

    public void delete(long id) throws Exception{
        try(Connection conn = getConnection(); PreparedStatement prpStmt = conn.prepareStatement(SQL_DELETE)){
            prpStmt.setLong(1, id);
            if(prpStmt.executeUpdate() == 0)
                throw new Exception("entity with id "+id+" was not deleted");
        }catch (SQLException e){
            throw e;
        }
    }

    public File findById(long id) throws SQLException, BadRequestException{
        try(Connection conn = getConnection(); PreparedStatement prpStmt = conn.prepareStatement(SQL_FIND_BY_ID)){
            prpStmt.setLong(1, id);

            ResultSet rs = prpStmt.executeQuery();
            if(rs.next()) {
                File file = new File();
                    file.setId(rs.getLong(1));
                    file.setName(rs.getString(2));
                    file.setFormat(rs.getString(3));
                    file.setSize(rs.getLong(4));
                    file.setStorage(new StorageDAO().findById(rs.getLong(5)));
                return file;
            }
            throw new BadRequestException(getClass().getName()+"-findById", "there is no entity with id "+id);
        }catch (SQLException e){
            throw e;
        }
    }

    public List<File> getFilesByStorageId(long id) throws Exception{
        try(Connection conn = getConnection(); PreparedStatement prStmt = conn.prepareStatement(SQL_GET_ALL_BY_STORAGE)){
            prStmt.setLong(1, id);
            ResultSet rs = prStmt.executeQuery();
            List<File> files = new ArrayList<>();
            while(rs.next()){
                File file = new File();
                    file.setId(rs.getLong(1));
                    file.setName(rs.getString(2));
                    file.setFormat(rs.getString(3));
                    file.setSize(rs.getLong(4));
                    file.setStorage(new StorageDAO().findById(rs.getLong(5)));
                files.add(file);
            }
            return files;
        }catch (SQLException e){
            System.err.println("Something went wrong");
            throw new SQLException(e);
        }
    }
}