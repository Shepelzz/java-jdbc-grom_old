package hibernate.lesson6;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductDAO {

    private SessionFactory sessionFactory;

    public Product save(Product product){
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();

            session.save(product);

            session.getTransaction().commit();
            return product;
        } catch (HibernateException e) {
            System.err.println("Save failed");
            System.err.println(e.getMessage());
            if (transaction != null)
                transaction.rollback();
        }
        return null;
    }

    public Product update(Product product){
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();

            session.update(product);

            session.getTransaction().commit();
            return product;
        } catch (HibernateException e) {
            System.err.println("Update failed");
            System.err.println(e.getMessage());
            if (transaction != null)
                transaction.rollback();
        }
        return null;
    }

    public Product delete(Product product){
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();

            session.delete(product);

            session.getTransaction().commit();
            return product;
        } catch (HibernateException e) {
            System.err.println("Delete failed");
            System.err.println(e.getMessage());
            if (transaction != null)
                transaction.rollback();
        }
        return null;
    }

    public void saveAll(List<Product> products){
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();

            for (Product product : products)
                session.save(product);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println("Save failed");
            System.err.println(e.getMessage());
            if (transaction != null)
                transaction.rollback();
        }
        System.out.println("Save All - Done.");
    }

    public void updateAll(List<Product> products){
        Transaction transaction = null;

        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();

            for (Product product : products)
                session.update(product);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println("Update failed");
            System.err.println(e.getMessage());
            if (transaction != null)
                transaction.rollback();
        }
        System.out.println("Update All - Done.");
    }

    public void deleteAll(List<Product> products){
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();

            for (Product product : products)
                session.delete(product);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println("Delete failed");
            System.err.println(e.getMessage());
            if (transaction != null)
                transaction.rollback();
        }
        System.out.println("Delete All - Done.");
    }

    private SessionFactory createSessionFactory(){
        //Singleton pattern
        if(sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }

}
