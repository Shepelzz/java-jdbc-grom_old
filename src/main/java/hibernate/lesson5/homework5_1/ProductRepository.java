package hibernate.lesson5.homework5_1;

import hibernate.lesson5.HibernateUtils;
import org.hibernate.Session;

public class ProductRepository {
    public void save(Product product){
        Session session = new HibernateUtils().createSessionFactory().openSession();
        try{
            session.getTransaction().begin();
            session.save(product);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            System.err.println(e.getMessage());
        }finally {
            session.close();
        }
    }

    public void update(Product product){
        Session session = new HibernateUtils().createSessionFactory().openSession();
        try{
            session.getTransaction().begin();
            session.update(product);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            System.err.println(e.getMessage());
        }finally {
            session.close();
        }
    }

    public void delete(long id){
        Session session = new HibernateUtils().createSessionFactory().openSession();
        try{
            session.getTransaction().begin();
            Product product = (Product)session.load(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            System.err.println(e.getMessage());
        }finally {
            session.close();
        }
    }
}
