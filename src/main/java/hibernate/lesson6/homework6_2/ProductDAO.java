package hibernate.lesson6.homework6_2;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductDAO {
    private SessionFactory sessionFactory;

    private static final String FIND_BY_ID_SQL = "SELECT * FROM PRODUCT WHERE ID = :productId";
    private static final String FIND_BY_NAME_SQL = "SELECT * FROM PRODUCT WHERE NAME = :productName";
    private static final String FIND_BY_CONTAINED_NAME_SQL = "SELECT * FROM PRODUCT WHERE NAME LIKE :productName";
    private static final String FIND_BY_PRICE_SQL = "SELECT * FROM PRODUCT WHERE PRICE BETWEEN :priceFrom AND :priceTo";
    private static final String FIND_BY_NAME_ASC_SQL = "SELECT * FROM PRODUCT WHERE NAME LIKE :productName ORDER BY NAME";
    private static final String FIND_BY_NAME_DESC_SQL = "SELECT * FROM PRODUCT WHERE NAME LIKE :productName ORDER BY NAME DESC";
    private static final String FIND_BY_PRICE_DESC_SQL = "SELECT * FROM PRODUCT WHERE PRICE BETWEEN :priceFrom AND :priceTo ORDER BY PRICE DESC";

    public Product findById(long id){
        try (Session session = createSessionFactory().openSession()) {

            return (Product) session.createSQLQuery(FIND_BY_ID_SQL)
                    .setParameter("productId", id)
                    .addEntity(Product.class).getSingleResult();

        } catch (HibernateException e) {
            System.err.println("findById failed");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Product> findByName(String name){
        try (Session session = createSessionFactory().openSession()) {

            return (List<Product>) session.createSQLQuery(FIND_BY_NAME_SQL)
                    .setParameter("productName", name)
                    .addEntity(Product.class).list();

        } catch (HibernateException e) {
            System.err.println("findByName failed");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Product> findByContainedName(String name){
        try (Session session = createSessionFactory().openSession()) {

            return (List<Product>) session.createSQLQuery(FIND_BY_CONTAINED_NAME_SQL)
                    .setParameter("productName", "%"+name+"%")
                    .addEntity(Product.class).list();

        } catch (HibernateException e) {
            System.err.println("findByName failed");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Product> findByPrice(int price, int delta){
        try (Session session = createSessionFactory().openSession()) {

            return (List<Product>) session.createSQLQuery(FIND_BY_PRICE_SQL)
                    .setParameter("priceFrom", price-delta)
                    .setParameter("priceTo", price+delta)
                    .addEntity(Product.class).list();

        } catch (HibernateException e) {
            System.err.println("findByName failed");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Product> findByNameSortedAsc(String name){
        try (Session session = createSessionFactory().openSession()) {

            return (List<Product>) session.createSQLQuery(FIND_BY_NAME_ASC_SQL)
                    .setParameter("productName", "%"+name+"%")
                    .addEntity(Product.class).list();

        } catch (HibernateException e) {
            System.err.println("findByName failed");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Product> findByNameSortedDesc(String name){
        try (Session session = createSessionFactory().openSession()) {

            return (List<Product>) session.createSQLQuery(FIND_BY_NAME_DESC_SQL)
                    .setParameter("productName", "%"+name+"%")
                    .addEntity(Product.class).list();

        } catch (HibernateException e) {
            System.err.println("findByName failed");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Product> findByPriceSortedDesc(int price, int delta){
        try (Session session = createSessionFactory().openSession()) {

            return (List<Product>) session.createSQLQuery(FIND_BY_PRICE_DESC_SQL)
                    .setParameter("priceFrom", price-delta)
                    .setParameter("priceTo", price+delta)
                    .addEntity(Product.class).list();

        } catch (HibernateException e) {
            System.err.println("findByName failed");
            System.err.println(e.getMessage());
        }
        return null;
    }

    private SessionFactory createSessionFactory(){
        if(sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}
