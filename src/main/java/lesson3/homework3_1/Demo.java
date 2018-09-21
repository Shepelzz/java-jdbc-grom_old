package lesson3.homework3_1;

public class Demo {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findProductsByPrice(150, 50));
        System.out.println(solution.findProductsByName("est"));
        System.out.println(solution.findProductsWithEmptyDescription());
    }
}
