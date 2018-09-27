package lesson4.homework4_1.dao;

import lesson4.homework4_1.exception.BadRequestException;
import lesson4.homework4_1.exception.InternalServerError;
import lesson4.homework4_1.model.Storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class StorageDAO extends GeneralDAO{
    private static final String SQL_SAVE = "INSERT INTO STORAGE VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE STORAGE SET FORMATS_SUPPORTED = ?, STORAGE_COUNTRY = ?, STORAGE_SIZE = ? WHERE ID = ?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM STORAGE WHERE ID = ?";
    private static final String SQL_DELETE = "DELETE FROM STORAGE WHERE ID = ?";
    private static final String SQL_GET_ID = "SELECT STORAGE_ID_SEQ.NEXTVAL FROM DUAL";
    private static final String SQL_GET_FREE_SPACE = "SELECT SUM(FILE_SIZE) FROM FILES WHERE STORAGE_ID = ?";

    public Storage save(Storage storage) throws InternalServerError, SQLException{
        try(Connection conn = getConnection(); PreparedStatement prpStmt = conn.prepareStatement(SQL_SAVE)){
            storage.setId(getNewEntityId(SQL_GET_ID));

            prpStmt.setLong(1, storage.getId());
            prpStmt.setString(2, Arrays.toString(storage.getFormatsSupported()));
            prpStmt.setString(3, storage.getStorageCountry());
            prpStmt.setLong(4, storage.getStorageSize());

            if(prpStmt.executeUpdate() == 0)
                throw new InternalServerError(getClass().getName()+"-save","entity with id "+storage.getId()+" was not saved");
            return storage;
        }catch (SQLException e){
            throw e;
        }
    }

    public Storage update(Storage storage) throws InternalServerError, SQLException{
        try(Connection conn = getConnection();  PreparedStatement prpStmt = conn.prepareStatement(SQL_UPDATE)){
            prpStmt.setString(1, Arrays.toString(storage.getFormatsSupported()));
            prpStmt.setString(2, storage.getStorageCountry());
            prpStmt.setLong(3, storage.getStorageSize());
            prpStmt.setLong(4, storage.getId());

            if(prpStmt.executeUpdate() == 0)
                throw new InternalServerError(getClass().getName()+"-update","entity with id "+storage.getId()+" was not updated");
            return storage;
        }catch (SQLException e){
            throw e;
        }
    }

    public void delete(long id) throws InternalServerError, SQLException{
        delete(id, SQL_DELETE);
    }

    public Storage findById(long id) throws SQLException, BadRequestException {
        try(Connection conn = getConnection(); PreparedStatement prpStmt = conn.prepareStatement(SQL_FIND_BY_ID)){
            prpStmt.setLong(1, id);

            ResultSet rs = prpStmt.executeQuery();
            if(rs.next()) {
                Storage storage = new Storage();
                    storage.setId(rs.getLong(1));
                    storage.setFormatsSupported(rs.getString(2).split(","));
                    storage.setStorageCountry(rs.getString(3));
                    storage.setStorageSize(rs.getLong(4));
                return storage;
            }
            throw new BadRequestException(getClass().getName()+"-findById", "there is no entity with id "+id);
        }catch (SQLException e){
            throw e;
        }
    }

    public long getUsedSpace(long id) throws SQLException, BadRequestException{
        try(Connection conn = getConnection(); PreparedStatement prpStmt = conn.prepareStatement(SQL_GET_FREE_SPACE)){
            prpStmt.setLong(1, id);

            ResultSet rs = prpStmt.executeQuery();
            if(rs.next()) {
                return rs.getLong(1);
            }
            throw new BadRequestException(getClass().getName()+"-findById", "there is no entity with id "+id);
        }catch (SQLException e){
            throw e;
        }
    }
}
