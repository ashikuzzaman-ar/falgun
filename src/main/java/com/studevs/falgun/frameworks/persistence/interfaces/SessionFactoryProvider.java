/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studevs.falgun.frameworks.persistence.interfaces;

import java.io.Serializable;
import org.hibernate.SessionFactory;

/**
 *
 * @author ashik
 */
public interface SessionFactoryProvider extends Serializable {

//    public abstract void setSessionFactory(SessionFactory sessionFactory);
    public abstract SessionFactory getSessionFactory();
}
