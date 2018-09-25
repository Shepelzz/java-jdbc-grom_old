package lesson4.homework4_1.service;

import lesson4.homework4_1.dao.StorageDAO;
import lesson4.homework4_1.exception.BadRequestException;
import lesson4.homework4_1.model.Storage;

public class StorageService {
    private StorageDAO storageDAO = new StorageDAO();

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception{
//        storageTo.checkTransfer(storageFrom.getFiles());
//
//        for (File file : storageFrom.getFiles())
//            if (file != null) {
//                storageTo.addFile(file);
//                delete(storageFrom, file);
//            }

        if(storageDAO.getUsedSpace(storageFrom.getId())+storageDAO.getUsedSpace(storageTo.getId()) > storageTo.getStorageSize())
            throw new BadRequestException(getClass().getName()+"-put", "there is no free space. storage id:"+storageTo.getId());

        storageDAO.transferAll(storageFrom, storageTo);

    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception{
//        storageTo.checkPutFile(storageFrom.getFileById(id));
//
//        storageTo.addFile(storageFrom.getFileById(id));
//        storageFrom.deleteFile(storageFrom.getFileById(id));
        //TODO
    }
}
