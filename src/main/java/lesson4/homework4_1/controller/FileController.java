package lesson4.homework4_1.controller;

import lesson4.homework4_1.model.File;
import lesson4.homework4_1.model.Storage;
import lesson4.homework4_1.service.FileService;

public class FileController {
    FileService fileService = new FileService();

    public File put(Storage storage, File file) throws Exception{
        return fileService.put(storage, file);
    }

    public void delete(File file) throws Exception{
        fileService.delete(file);
    }
}
