package lesson4.homework4_1.service;

import lesson4.homework4_1.dao.FileDAO;
import lesson4.homework4_1.dao.StorageDAO;
import lesson4.homework4_1.exception.BadRequestException;
import lesson4.homework4_1.exception.InternalServerError;
import lesson4.homework4_1.model.File;
import lesson4.homework4_1.model.Storage;

import java.sql.SQLException;
import java.util.List;

public class Service {
    private FileDAO fileDAO = new FileDAO();
    private StorageDAO storageDAO = new StorageDAO();

    public File put(Storage storage, File file) throws InternalServerError{
        file.setStorage(storage);
        validateFile(storage, file);

        try {
            return fileDAO.putFileIntoStorage(storage, file);
        }catch (SQLException e){
            throw new InternalServerError(getClass().getName() + "-put. " +e.getMessage());
        }
    }

    public void delete(Storage storage, File file) throws InternalServerError {
        try {
            fileDAO.deleteFileFromStorage(storage, file);
        }catch (SQLException e){
            throw new InternalServerError(getClass().getName() + "-delete. " +e.getMessage());
        }
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws InternalServerError{
        try {
            List<File> filesInStorage = fileDAO.getFilesByStorageId(storageFrom.getId());

            long filesSize = 0;
            long storageSize = storageTo.getStorageSize();
            for (File file : filesInStorage) {
                filesSize += file.getSize();
                storageSize -= file.getSize();

                checkFileFormat(storageTo, file);
                if (storageSize < 0)
                    throw new BadRequestException(getClass().getName() + "-transferAll. Storage is full. storage id:" + storageTo.getId() + (file.getId() == 0 ? "" : " file id:" + file.getId()));
            }

            fileDAO.transferFiles(storageFrom, storageTo, filesSize);
        }catch (SQLException e){
            throw new InternalServerError(getClass().getName() + "-transfer all. " +e.getMessage());
        }
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws InternalServerError{

        try{
            File file = fileDAO.findById(id);
            file.setStorage(storageTo);

            validateFile(storageTo, file);

            fileDAO.transferFile(storageFrom, storageTo, file);
        }catch (SQLException e){
            throw new InternalServerError(getClass().getName() + "-transfer file. " +e.getMessage());
        }
    }

    private void validateFile(Storage storage, File file) throws InternalServerError{
        List<File> filesInStorage;
        try{
            filesInStorage = fileDAO.getFilesByStorageId(storage.getId());
        }catch (SQLException | ClassCastException e){
            throw new InternalServerError(getClass().getName()+"-validateFile"+e.getMessage());
        }

        //check if exists
        for (File f : filesInStorage) {
            if (f.equals(file))
                throw new BadRequestException(getClass().getName() + "-validateFile. File id:" + file.getId() + " already exists in storage id:" + storage.getId());
        }
        //check size
        if(storage.getStorageSize() < file.getSize())
            throw new BadRequestException(getClass().getName()+"-checkInputFileSize. There is no enough free space in storage id:"+storage.getId()+" file id:"+file.getId());
        //check format
        checkFileFormat(storage, file);
    }

    private void checkFileFormat(Storage storage, File file){
        for(String format : storage.getFormatsSupported())
            if(format.equals(file.getFormat()))
                return;
        throw new BadRequestException(getClass().getName()+"-checkInputFileFormat. File format is not accepted. storage id:"+storage.getId()+" file id:"+file.getId());
    }
}
