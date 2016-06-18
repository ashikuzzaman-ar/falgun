/**
 * This class is created to make all hibernate staff easy for developers.
 * Basic target is to create a class for CRUD operation in hibernate.
 * It's very painful to create a session from session factory and transaction object and
 * managing exceptions and rollback. This class will manage all this staffs.
 *
 */
package com.studevs.falgun.frameworks.persistence;

import com.studevs.falgun.frameworks.persistence.interfaces.SessionFactoryProvider;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author ashik
 */
public class Hibernate implements Serializable {

    /**
     * sessionFactory is a object of SessionFactory class to provide session
     * object. session is a object of Session class. This object get a local
     * session from sessionFactory class and open a new session for a single
     * operation. transaction is a object of Transaction class and this object
     * maintain transactions.
     */
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
    private Query query;

    /**
     * Public constructor with no value.
     */
    public Hibernate() {

        this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    }

    /**
     * Public constructor with session factory.
     *
     * @param sessionFactoryProvider
     */
    public Hibernate(SessionFactoryProvider sessionFactoryProvider) {

        this.sessionFactory = sessionFactoryProvider.getSessionFactory();
    }

    /**
     * getter method for sessionFactory
     *
     * @return
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Setter method for sessionFactory
     *
     * @param sessionFactory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public long deleteObjects(String HibernateQueryLanguage, Map<String, Object> namedParameterMap) {

        return this.updateObjectsFromHQL(HibernateQueryLanguage, namedParameterMap);
    }

    private long deleteObjectArray(Object[] deleteObjects) {

        this.session = this.sessionFactory.openSession();
        this.transaction = null;

        long rowAffected = -1;

        try {

            if (deleteObjects != null && this.sessionFactory != null) {

                this.transaction = this.session.beginTransaction();

                for (Object object : deleteObjects) {

                    this.session.delete(object);
                }

                this.transaction.commit();

                rowAffected = deleteObjects.length;
            }
        } catch (Exception e) {

            if (this.transaction != null) {

                this.transaction.rollback();
            }

            throw new ExceptionInInitializerError(e);
        } finally {

            this.session.close();
        }

        return rowAffected;
    }

    public long deleteObjects(Collection<Object> deleteObjects) {

        return this.deleteObjectArray(deleteObjects.toArray());
    }

    public long deleteObjects(Object... deleteObjects) {

        return this.deleteObjectArray(deleteObjects);
    }

    private long updateObjectsArray(Object[] updatedObjects) {

        this.session = this.sessionFactory.openSession();
        this.transaction = null;

        long rowAffected = -1;

        try {

            if (updatedObjects != null && this.sessionFactory != null) {

                this.transaction = this.session.beginTransaction();

                for (Object object : updatedObjects) {

                    this.session.update(object);
                }

                this.transaction.commit();

                rowAffected = updatedObjects.length;
            }
        } catch (Exception e) {

            if (this.transaction != null) {

                this.transaction.rollback();
            }

            throw new ExceptionInInitializerError(e);
        } finally {

            this.session.close();
        }

        return rowAffected;
    }

    public long updateObjects(Collection<Object> updatedObjects) {

        return this.updateObjectsArray(updatedObjects.toArray());
    }

    public long updateObjects(Object... updatedObjects) {

        return this.updateObjectsArray(updatedObjects);
    }

    private long updateObjectsFromHQL(String HibernateQueryLanguage, Map<String, Object> namedParameterMap) {

        try {

            if (HibernateQueryLanguage.trim().equals("") || namedParameterMap == null || this.sessionFactory == null) {

                return -1;
            } else {

                this.session = this.sessionFactory.openSession();
                this.transaction = null;
                this.query = null;

                long rowAffected;
                Set<String> namedParameterMapKeys = namedParameterMap.keySet();

                try {

                    this.transaction = this.session.beginTransaction();

                    this.query = this.session.createQuery(HibernateQueryLanguage);

                    namedParameterMapKeys.stream().forEach((key) -> {

                        this.query.setParameter(key, namedParameterMap.get(key));
                    });

                    rowAffected = (long) this.query.executeUpdate();

                    this.transaction.commit();
                } catch (Exception e) {

                    if (this.transaction != null) {

                        this.transaction.rollback();
                    }

                    rowAffected = -1;

                    throw new ExceptionInInitializerError(e);
                } finally {

                    this.session.close();
                }

                return rowAffected;
            }
        } catch (HibernateException e) {

            throw new ExceptionInInitializerError(e);
        }
    }

    public long updateObjects(String HibernateQueryLanguage, Map<String, Object> namedParameterMap) {

        return this.updateObjectsFromHQL(HibernateQueryLanguage, namedParameterMap);
    }

    public List<Object> getObjects(String HibernateQueryLanguage, Map<String, Object> namedParameterMap,
            Integer startPosition, Integer maxResult) {

        try {

            if (HibernateQueryLanguage.trim().equals("") || this.sessionFactory == null) {

                return null;
            } else {

                this.session = this.sessionFactory.openSession();
                this.transaction = null;
                this.query = null;

                List<Object> objects;

                try {

                    this.transaction = this.session.beginTransaction();

                    this.query = this.session.createQuery(HibernateQueryLanguage);

                    if (namedParameterMap != null) {

                        Set<String> namedParameterMapKeys = namedParameterMap.keySet();

                        namedParameterMapKeys.stream().forEach((key) -> {

                            this.query.setParameter(key, namedParameterMap.get(key));
                        });
                    }

                    if (startPosition != null) {

                        this.query.setFirstResult(startPosition);
                    }

                    if (maxResult != null) {

                        this.query.setMaxResults(maxResult);
                    }

                    objects = this.query.list();

                    this.transaction.commit();
                } catch (Exception e) {

                    if (this.transaction != null) {

                        this.transaction.rollback();
                    }

                    objects = null;

                    throw new ExceptionInInitializerError(e);
                } finally {

                    this.session.close();
                }

                return objects;
            }
        } catch (HibernateException e) {

            throw new ExceptionInInitializerError(e);
        }
    }

    public List<Object> getObjects(String HibernateQueryLanguage, Integer startPosition, Integer maxResult) {

        return this.getObjects(HibernateQueryLanguage, null, startPosition, maxResult);
    }

    public List<Object> getObjects(String HibernateQueryLanguage, Map<String, Object> namedParameterMap, Integer startPosition) {

        return this.getObjects(HibernateQueryLanguage, namedParameterMap, startPosition, null);
    }

    public List<Object> getObjects(String HibernateQueryLanguage, Map<String, Object> namedParameterMap) {

        return this.getObjects(HibernateQueryLanguage, namedParameterMap, null, null);
    }

    public List<Object> getObjects(String HibernateQueryLanguage) {

        return this.getObjects(HibernateQueryLanguage, null, null, null);
    }

    /**
     *
     * @param insertedObjects
     * @return Long[]
     *
     * This method is a private method, created to save object's array in
     * database. addObjectArray method takes an array of objects and save all
     * objects one by one and return an Array of Long class containing id of the
     * objects.
     */
    private Long[] addObjectArray(Object[] insertedObjects) {

        try {

            /**
             * If insertedObjects is null that means the array is empty or no
             * object is provided to save. In this situation nothing will happen
             * and null will be returned.
             *
             * If session factory is null then nothing can be done so that no
             * session will be created.
             */
            if (insertedObjects == null || this.sessionFactory == null) {

                return null;
            } else {

                /**
                 * session object is initialized with a new session created by
                 * session factory and a transaction object is initialized to
                 * null. A long type array named userID is created with the
                 * length of inserted object's array length. After that session
                 * object save each and every single object to database and
                 * store it's primary keys in userID array. If any exception
                 * happen then transaction object rollback all session. Finally
                 * session will be closed and userID array will be returned.
                 */
                this.session = this.sessionFactory.openSession();
                this.transaction = null;

                Long[] userID = new Long[insertedObjects.length];

                try {

                    this.transaction = this.session.beginTransaction();

                    for (int i = 0; i < insertedObjects.length; i++) {

                        userID[i] = (Long) this.session.save(insertedObjects[i]);
                    }

                    this.transaction.commit();
                } catch (Exception e) {

                    if (this.transaction != null) {

                        this.transaction.rollback();
                    }

                    throw new ExceptionInInitializerError(e);
                } finally {

                    this.session.close();
                }

                return userID;
            }
        } catch (HibernateException e) {

            System.err.println(e);
            return null;
        }
    }

    /**
     *
     * This public method will get an object of collection type [basically List
     * and Set] and store the objects by using addObjectArray method.
     *
     * @param insertedObjects
     * @return
     */
    public Long[] addObjectCollection(Collection<Object> insertedObjects) {

        return this.addObjectArray(insertedObjects.toArray());
    }

    /**
     *
     * This method will take variable length parameter of objects and store all
     * objects in database using hibernate.
     *
     * @param insertedObjects
     * @return
     */
    public Long[] addObjects(Object... insertedObjects) {

        return this.addObjectArray(insertedObjects);
    }

    public long addObjects(String HibernateQueryLanguage, Map<String, Object> namedParameterMap) {

        return this.updateObjectsFromHQL(HibernateQueryLanguage, namedParameterMap);
    }
}
