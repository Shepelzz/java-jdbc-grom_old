package lesson4.homework4_1.service;

import lesson4.homework4_1.dao.FileDAO;
import lesson4.homework4_1.dao.StorageDAO;
import lesson4.homework4_1.exception.BadRequestException;
import lesson4.homework4_1.model.File;
import lesson4.homework4_1.model.Storage;

public class FileService {
    private FileDAO fileDAO = new FileDAO();

    public File put(Storage storage, File file) throws Exception{
        StorageDAO storageDAO = new StorageDAO();

        if(storageDAO.getUsedSpace(storage.getId())+file.getSize() > storage.getStorageSize())
            throw new BadRequestException(getClass().getName()+"-put", "there is no free space. storage id:"+storage.getId());

        //TODO format check
        file.setStorage(storage);
        return fileDAO.save(file);
    }

    public void delete(File file) throws Exception{
        fileDAO.delete(file.getId());
    }
}
