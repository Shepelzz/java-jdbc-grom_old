package lesson4.homework4_1.controller;

import lesson4.homework4_1.exception.InternalServerError;
import lesson4.homework4_1.model.File;
import lesson4.homework4_1.model.Storage;
import lesson4.homework4_1.service.Service;

import java.sql.SQLException;

public class Controller {
    private Service service = new Service();

    public File put(Storage storage, File file) throws InternalServerError, SQLException {
        return service.put(storage, file);
    }

    public void delete(Storage storage, File file) throws InternalServerError, SQLException{
        service.delete(storage, file);
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws InternalServerError, SQLException{
        service.transferAll(storageFrom, storageTo);
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws InternalServerError, SQLException{
        service.transferFile(storageFrom, storageTo, id);
    }
}
