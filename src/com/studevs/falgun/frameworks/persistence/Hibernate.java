/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studevs.falgun.frameworks.persistence;

import java.util.Collection;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ashik
 */
public class Hibernate {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public Hibernate() {

        this.sessionFactory = null;
        this.session = null;
        this.transaction = null;
    }

    public Hibernate(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
        this.session = null;
        this.transaction = null;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Long[] addObjectArray(Object[] insertedObjects) {

        try {

            if (insertedObjects == null || this.sessionFactory == null) {

                return null;
            } else {

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

    public Long[] addObjectCollection(Collection<Object> insertedObjects) {

        try {

            return this.addObjectArray(insertedObjects.toArray());
        } catch (HibernateException e) {

            System.err.println(e);
            return null;
        }
    }

    public Long[] addObjects(Object... insertedObjects) {

        try {

            return this.addObjectArray(insertedObjects);
        } catch (HibernateException e) {

            System.err.println(e);
            return null;
        }
    }
}
