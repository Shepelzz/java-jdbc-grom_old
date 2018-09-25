package lesson4.homework4_1;

import lesson4.homework4_1.dao.FileDAO;
import lesson4.homework4_1.model.File;
import lesson4.homework4_1.model.Storage;

public class Demo {
    public static void main(String[] args) {

        FileDAO fileDAO = new FileDAO();
        Storage storage = new Storage();
            storage.setId(1);
            storage.setFormatsSupported(new String[]{"txt"});
            storage.setStorageCountry("Ukraine");
            storage.setStorageSize(500);

        File file = new File();
            file.setId(10);
            file.setName("test_file1");
            file.setFormat("txt");
            file.setSize(130);
            file.setStorage(storage);

        try {
            //fileDAO.save(file);
            //System.out.println(fileDAO.findById(10).getName());
            //fileDAO.update(file);
            //fileDAO.delete(10);
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
        }

    }
}