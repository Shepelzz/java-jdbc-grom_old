package lesson4.homework4_1.service;

import lesson4.homework4_1.dao.FileDAO;
import lesson4.homework4_1.dao.StorageDAO;
import lesson4.homework4_1.exception.BadRequestException;
import lesson4.homework4_1.exception.InternalServerError;
import lesson4.homework4_1.model.File;
import lesson4.homework4_1.model.Storage;
import oracle.jdbc.internal.OracleStatement;

import java.sql.SQLException;

public class Service {
    private FileDAO fileDAO = new FileDAO();
    private StorageDAO storageDAO = new StorageDAO();

    public File put(Storage storage, File file) throws InternalServerError, SQLException{
        if(file.getName().equals("") || file.getFormat().equals("") || file.getSize() <= 0)
            throw new BadRequestException(getClass().getName()+"-put", "Values can not be negative or empty");

        checkInputFileFormat(storage, file);
        checkInputFileSize(storage, file);

        file.setStorage(storage);
        return fileDAO.save(file);
    }

    public void delete(Storage storage, File file) throws InternalServerError, SQLException {
        if(fileDAO.findById(file.getId()).getStorage().getId() != storage.getId())
            throw new BadRequestException(getClass().getName()+"-delete", "there is no file with id:"+file.getId()+" in storage id:"+storage.getId());

        fileDAO.delete(file.getId());
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws InternalServerError, SQLException{

        long runningFileSize = 0;
        for(File file : fileDAO.getFilesByStorageId(storageFrom.getId())){
            runningFileSize += file.getSize();

            checkInputFileFormat(storageTo, file);
            if(runningFileSize > storageTo.getStorageSize())
                throw new BadRequestException(getClass().getName()+"-transferAll","storage is full. storage id:"+storageTo.getId()+(file.getId()==0?"":" file id:"+file.getId()));
        }

        fileDAO.transferAll(storageFrom, storageTo);
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws InternalServerError, SQLException{
        File file = fileDAO.findById(id);

        if(storageDAO.getUsedSpace(storageTo.getId())+file.getSize() > storageTo.getStorageSize())
            throw new BadRequestException(getClass().getName()+"-transfer file", "there is no enough free space in storage id:"+storageTo.getId()+" file id:"+id);

        checkInputFileFormat(storageTo, fileDAO.findById(id));
        checkInputFileSize(storageTo, file);

        file.setStorage(storageTo);
        fileDAO.update(file);

    }

    private void checkInputFileFormat(Storage storage, File file) throws BadRequestException, SQLException{
        for(String format : storageDAO.findById(storage.getId()).getFormatsSupported())
            if(format.equals(file.getFormat()))
                return;
        throw new BadRequestException(getClass().getName()+"-checkInputFileFormat","file format is not accepted. storage id:"+storage.getId()+(file.getId()==0?"":" file id:"+file.getId()));
    }

    private void checkInputFileSize(Storage storage, File file) throws BadRequestException, SQLException{
        if(storageDAO.getUsedSpace(storage.getId())+file.getSize() > storageDAO.findById(storage.getId()).getStorageSize())
            throw new BadRequestException(getClass().getName()+"-checkInputFileSize","there is no enough free space in storage id:"+storage.getId()+(file.getId()==0?"":" file id:"+file.getId()));
    }
}
