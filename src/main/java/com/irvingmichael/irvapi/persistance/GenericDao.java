package com.irvingmichael.irvapi.persistance;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic Dao for creating, reading, updating and deleting objects
 *
 * @author Aaron Anderson
 */
public class GenericDao<T> {

    private Class<T> type;
    private final Logger log = Logger.getLogger(this.getClass());

    public GenericDao() {}

    /**
     * Generic Dao constructor
     * Example: AbstractDao dao = new AbstractDao(Users.class);
     * @param type Provide the class of object you would like to us the dao with
     */
    public GenericDao(Class<T> type) { this.type = type; }

    /**
     * Create a database entry for a object
     * @param object Object to create database entry from
     * @return int Id of object created
     */
    public int create(T object) {

        Transaction tx = null;
        Integer id = -1;
        Session session = getSession();

        try {
            tx = session.beginTransaction();
            id = (Integer) session.save(object);
            tx.commit();
            log.debug("Created " + object.getClass().getName() + " with id " +
                    "of: " + id);
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            log.error(e);
        } finally {
            session.close();
        }

        return id;
    }

    /**
     * Return array list of all objects built from the database entries
     * @return Array list of the specified type of objects
     */
    public List<T> getAll() {
        return (ArrayList<T>)getSession().createCriteria(type).list();
    }

    /**
     * Returns an object of the specified type built from the database entry with the supplied id.
     * @param id Id of database entry to return
     * @return Object of specified type with the retrieved information
     */
    public T getById(int id) {
        return (T) getSession().get(type, id);
    }

    /**
     * Update the database with information from supplied object
     * @param object Object to update the database with
     */
    public void update(T object) {

        Session session = getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(object);
            tx.commit();
            log.debug("Updated " + object.getClass().getName() + ": " + object);
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            log.error(e);
        } finally {
            session.close();
        }
    }

    /**
     * Delete the database entry for the supplied object
     * @param object Object to delete the database entry for
     */
    public void delete(T object) {
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(object);
            tx.commit();
            log.debug("Deleted " + object.getClass().getName() + ": " + object);
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            log.error(e);
        } finally {
            session.close();
        }
    }

    /**
     * Gets a seesion from the session factory
     * @return New session
     */
    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }
}
