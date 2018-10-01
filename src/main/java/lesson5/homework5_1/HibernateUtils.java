package lesson5.homework5_1;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private SessionFactory sessionFactory;

    public SessionFactory createSessionFactory(){
        return new Configuration().configure().buildSessionFactory();
    }
}
