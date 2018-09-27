package lesson4.homework4_1;

import lesson4.homework4_1.controller.Controller;
import lesson4.homework4_1.dao.FileDAO;
import lesson4.homework4_1.dao.StorageDAO;
import lesson4.homework4_1.model.File;
import lesson4.homework4_1.model.Storage;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {

        FileDAO fileDAO = new FileDAO();
        Storage storage = new Storage();
            storage.setId(1);

        File file = new File();
            file.setName("file");
            file.setFormat("xls");
            file.setSize(10);
            file.setStorage(storage);

        try {

            StorageDAO storageDAO = new StorageDAO();
            Controller controller = new Controller();

            controller.put(storage, file);
            //controller.transferAll(storageDAO.findById(2), storageDAO.findById(1));
            //controller.transferFile(storageDAO.findById(1), storageDAO.findById(2), 31);


            //controller.delete(storageDAO.findById(2), fileDAO.findById(6));
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
        }

    }
}