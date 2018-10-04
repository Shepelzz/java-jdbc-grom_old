package hibernate.lesson5.homework5_1;

public class Demo {
    public static void main(String[] args) {

        ProductRepository productRepository = new ProductRepository();
        Product product = new Product();
            product.setId(101);
            product.setName("dog");
            product.setDescription("doberman1");
            product.setPrice(1000);

//        productRepository.save(product);
//        System.out.println("save done");

//        productRepository.update(product);
//        System.out.println("update done");

//        productRepository.delete(101);
//        System.out.println("delete done");
    }
}
