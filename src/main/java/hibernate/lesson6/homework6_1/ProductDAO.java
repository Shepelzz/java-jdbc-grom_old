package hibernate.lesson6.homework6_1;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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

    public Product findById(long id) throws Exception{
        try (Session session = createSessionFactory().openSession()) {

            return (Product) session.createQuery(FIND_BY_ID_HQL)
                .setParameter("productId", id).getSingleResult();

        } catch (HibernateException e) {
            throw new Exception("findById failed"+e.getMessage());
        }
    }

    public List<Product> findByName(String name) throws Exception{
        try (Session session = createSessionFactory().openSession()) {

            return (List<Product>) session.createQuery(FIND_BY_NAME_HQL)
                    .setParameter("productName", name).list();

        } catch (HibernateException e) {
            throw new Exception("findByName failed"+e.getMessage());
        }
    }

    public List<Product> findByContainedName(String name) throws Exception{
        try (Session session = createSessionFactory().openSession()) {

            return (List<Product>) session.createQuery(FIND_BY_CONTAINED_NAME_HQL)
                    .setParameter("productName", name).list();

        } catch (HibernateException e) {
            throw new Exception("findByContainedName failed"+e.getMessage());
        }
    }

    public List<Product> findByPrice(int price, int delta) throws Exception{
        try (Session session = createSessionFactory().openSession()) {

            return (List<Product>) session.createQuery(FIND_BY_PRICE_HQL)
                    .setParameter("priceFrom", price-delta)
                    .setParameter("priceTo", price+delta).list();

        } catch (HibernateException e) {
            throw new Exception("findByPrice failed"+e.getMessage());
        }
    }

    public List<Product> findByNameSortedAsc(String name) throws Exception{
        try (Session session = createSessionFactory().openSession()) {

            return (List<Product>) session.createQuery(FIND_BY_NAME_ASC_HQL)
                    .setParameter("productName", name).list();

        } catch (HibernateException e) {
            throw new Exception("findByNameSortedAsc failed"+e.getMessage());
        }
    }

    public List<Product> findByNameSortedDesc(String name) throws Exception{
        try (Session session = createSessionFactory().openSession()) {

            return (List<Product>) session.createQuery(FIND_BY_NAME_DESC_HQL)
                    .setParameter("productName", name).list();

        } catch (HibernateException e) {
            throw new Exception("findByNameSortedDesc failed"+e.getMessage());
        }
    }

    public List<Product> findByPriceSortedDesc(int price, int delta) throws Exception{
        try (Session session = createSessionFactory().openSession()) {

            return (List<Product>) session.createQuery(FIND_BY_PRICE_DESC_HQL)
                    .setParameter("priceFrom", price-delta)
                    .setParameter("priceTo", price+delta).list();

        } catch (HibernateException e) {
            throw new Exception("findByPriceSortedDesc failed"+e.getMessage());
        }
    }

    private SessionFactory createSessionFactory(){
        if(sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}
