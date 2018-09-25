package lesson4.homework4_1.controller;

import lesson4.homework4_1.model.Storage;
import lesson4.homework4_1.service.StorageService;

public class StorageController {
    private StorageService service = new StorageService();

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception{
        service.transferAll(storageFrom, storageTo);
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception{
        service.transferFile(storageFrom, storageTo, id);
    }
}