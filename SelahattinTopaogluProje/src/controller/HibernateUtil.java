/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
 
 
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
 
/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author asimsinanyuksel
 */
public class HibernateUtil {
 
    private static final SessionFactory sessionFactory;
     
    static {
        try {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
       } catch (Throwable ex) {
           throw new ExceptionInInitializerError(ex);
        }
    }
     
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}