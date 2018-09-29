package lesson4.homework4_1;

import lesson4.homework4_1.controller.Controller;
import lesson4.homework4_1.dao.FileDAO;
import lesson4.homework4_1.dao.StorageDAO;
import lesson4.homework4_1.model.File;
import lesson4.homework4_1.model.Storage;

import java.sql.SQLException;

public class Demo {
    public static void main(String[] args) {

        FileDAO fileDAO = new FileDAO();
        StorageDAO storageDAO = new StorageDAO();
        Controller controller = new Controller();

        Storage storage = null;
        File file;

        try {
            storage = storageDAO.findById(2);
        }catch (SQLException e){
            System.err.println(e);
        }
        file = new File();
            file.setId(100);
//            file.setName("txt_fil4");
//            file.setFormat("txt");
//            file.setSize(10);

        try {
            //controller.put(storage, file);
            //controller.delete(storage, file);
            //controller.transferFile(storageDAO.findById(2), storageDAO.findById(1), 100);
            controller.transferAll(storageDAO.findById(2), storageDAO.findById(1));

//            Service service = new Service();
//            service.validateFile(storageDAO.findById(1), null);

            //controller.put(storage, file);
            //controller.transferAll(storageDAO.findById(2), storageDAO.findById(1));
            //controller.transferFile(storageDAO.findById(1), storageDAO.findById(2), 31);


            //controller.delete(storageDAO.findById(2), fileDAO.findById(6));
        }catch (Exception e){
            System.err.println(e);
        }

    }
}