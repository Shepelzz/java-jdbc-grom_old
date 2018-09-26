package lesson4.homework4_1.controller;

import lesson4.homework4_1.model.File;
import lesson4.homework4_1.model.Storage;
import lesson4.homework4_1.service.Service;

public class Controller {
    Service service = new Service();

    public File put(Storage storage, File file) throws Exception{
        return service.put(storage, file);
    }

    public void delete(File file) throws Exception{
        service.delete(file);
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception{
        service.transferAll(storageFrom, storageTo);
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception{
        service.transferFile(storageFrom, storageTo, id);
    }
}
