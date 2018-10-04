package jdbc.lesson4.homework4_1;

import jdbc.lesson4.homework4_1.controller.Controller;
import jdbc.lesson4.homework4_1.dao.FileDAO;
import jdbc.lesson4.homework4_1.dao.StorageDAO;
import jdbc.lesson4.homework4_1.model.File;
import jdbc.lesson4.homework4_1.model.Storage;

public class Demo {
    public static void main(String[] args) throws Exception{
        Controller controller = new Controller();
        FileDAO fileDAO = new FileDAO();
        StorageDAO storageDAO = new StorageDAO();

        Storage storage = storageDAO.findById(2);
        File file = new File();
            file.setSize(20);
            file.setFormat("txt");
            file.setName("testF2");

        //controller.put(storage, file);
//        controller.delete(storage, fileDAO.findById(65));
//        controller.delete(storage, fileDAO.findById(66));

        //controller.transferFile(storageDAO.findById(1), storageDAO.findById(2), 151);

        controller.transferAll(storageDAO.findById(2), storageDAO.findById(1));
    }
}