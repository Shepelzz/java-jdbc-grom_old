package hibernate.lesson6.homework6_1;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAO {
    private SessionFactory sessionFactory;

    private static final String FIND_BY_ID_HQL = "FROM Product WHERE id = : productId";
    private static final String FIND_BY_NAME_HQL = "FROM Product WHERE name = : productName";
    private static final String FIND_BY_CONTAINED_NAME_HQL = "FROM Product WHERE name LIKE CONCAT('%',: productName, '%')";
    private static final String FIND_BY_PRICE_HQL = "FROM Product WHERE price BETWEEN : priceFrom AND : priceTo";
    private static final String FIND_BY_NAME_ASC_HQL = "FROM Product WHERE name LIKE CONCAT('%',: productName, '%') ORDER BY name";
    private static final String FIND_BY_NAME_DESC_HQL = "FROM Product WHERE name LIKE CONCAT('%',: productName, '%') ORDER BY name DESC";
    private static final String FIND_BY_PRICE_DESC_HQL = "FROM Product WHERE price BETWEEN : priceFrom AND : priceTo ORDER BY price DESC";

    public Product findById(long id){
        try (Session session = createSessionFactory().openSession()) {

            Query<Product> query = session.createQuery(FIND_BY_ID_HQL);
            query.setParameter("productId", id);
            return query.getSingleResult();

        } catch (HibernateException e) {
            System.err.println("findById failed");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Product> findByName(String name){
        try (Session session = createSessionFactory().openSession()) {

            Query<Product> query = session.createQuery(FIND_BY_NAME_HQL);
            query.setParameter("productName", name);
            return query.list();

        } catch (HibernateException e) {
            System.err.println("findByName failed");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Product> findByContainedName(String name){
        try (Session session = createSessionFactory().openSession()) {

            Query<Product> query = session.createQuery(FIND_BY_CONTAINED_NAME_HQL);
            query.setParameter("productName", name);
            return query.list();

        } catch (HibernateException e) {
            System.err.println("findByName failed");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Product> findByPrice(int price, int delta){
        try (Session session = createSessionFactory().openSession()) {

            Query<Product> query = session.createQuery(FIND_BY_PRICE_HQL);
            query.setParameter("priceFrom", price-delta);
            query.setParameter("priceTo", price+delta);
            return query.list();

        } catch (HibernateException e) {
            System.err.println("findByName failed");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Product> findByNameSortedAsc(String name){
        try (Session session = createSessionFactory().openSession()) {

            Query<Product> query = session.createQuery(FIND_BY_NAME_ASC_HQL);
            query.setParameter("productName", name);
            return query.list();

        } catch (HibernateException e) {
            System.err.println("findByName failed");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Product> findByNameSortedDesc(String name){
        try (Session session = createSessionFactory().openSession()) {

            Query<Product> query = session.createQuery(FIND_BY_NAME_DESC_HQL);
            query.setParameter("productName", name);
            return query.list();

        } catch (HibernateException e) {
            System.err.println("findByName failed");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Product> findByPriceSortedDesc(int price, int delta){
        try (Session session = createSessionFactory().openSession()) {

            Query<Product> query = session.createQuery(FIND_BY_PRICE_DESC_HQL);
            query.setParameter("priceFrom", price-delta);
            query.setParameter("priceTo", price+delta);
            return query.list();

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
