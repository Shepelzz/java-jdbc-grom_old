package lesson4.homework4_1.service;

import lesson4.homework4_1.dao.FileDAO;
import lesson4.homework4_1.dao.StorageDAO;
import lesson4.homework4_1.exception.BadRequestException;
import lesson4.homework4_1.model.File;
import lesson4.homework4_1.model.Storage;

public class Service {
    FileDAO fileDAO = new FileDAO();
    StorageDAO storageDAO = new StorageDAO();

    public File put(Storage storage, File file) throws Exception{
        if(file.getName().equals("") || file.getFormat().equals(""))
            throw new BadRequestException(getClass().getName()+"-put", "Values can not be empty");

        if(storageDAO.getUsedSpace(storage.getId())+file.getSize() > storage.getStorageSize())
            throw new BadRequestException(getClass().getName()+"-put", "there is no free space. storage id:"+storage.getId());

        checkFileFormat(storage, file);

        file.setStorage(storage);
        return fileDAO.save(file);
    }

    public void delete(File file) throws Exception{
        fileDAO.delete(file.getId());
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception{
        if(storageDAO.getUsedSpace(storageFrom.getId())+storageDAO.getUsedSpace(storageTo.getId()) > storageTo.getStorageSize())
            throw new BadRequestException(getClass().getName()+"-put", "there is no free space. storage id:"+storageTo.getId());


        for(File file : fileDAO.getFilesByStorageId(storageFrom.getId())){
            checkFileFormat(storageTo, file);

            file.setStorage(storageTo);
            fileDAO.update(file);
        }
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception{

    }

    private void checkFileFormat(Storage storage, File file) throws Exception{
        for(String format : storage.getFormatsSupported())
            if(format.equals(file.getFormat()))
                return;
        throw new Exception("file format is not accepted. storage id:"+storage.getId()+(file.getId()==0?"":" file id:"+file.getId()));
    }
}
