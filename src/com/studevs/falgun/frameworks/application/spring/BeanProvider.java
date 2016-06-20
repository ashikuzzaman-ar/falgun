package com.studevs.falgun.frameworks.application.spring;

import java.io.Serializable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanProvider implements Serializable {

    private ApplicationContext applicationContext = null;

    public BeanProvider() {

        this.applicationContext = new ClassPathXmlApplicationContext("beans.xml");
    }

    public BeanProvider(String configurationXMLFileName) {

        this.applicationContext = new ClassPathXmlApplicationContext(configurationXMLFileName);
    }

    public Object getBean(String beanName) {

        try {

            return this.applicationContext.getBean(beanName);
        } catch (Exception e) {

            return null;
        }
    }

    public Object getBean(String beanName, String configurationXMLFileName) {

        try {

            this.applicationContext = new ClassPathXmlApplicationContext(configurationXMLFileName);
            return this.applicationContext.getBean(beanName);
        } catch (Exception e) {

            return null;
        }
    }
}
