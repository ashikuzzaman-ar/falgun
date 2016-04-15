/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studevs.falgun.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author ashik
 */
public class ObjectSerialization {

    private Object serializedObject;
    private FileOutputStream fileOutputStream;
    private ObjectOutputStream objectOutputStream;
    private FileInputStream fileInputStream;
    private ObjectInputStream objectInputStream;
    private String objectFileName ;

    public ObjectSerialization(Object object) {

        try {

            this.serializedObject = object;
            this.objectFileName = "object.ser";
        } catch (Exception e) {

            System.err.println(e.toString());
        }
    }
    
    
    public ObjectSerialization(Object object, String objectFileName) {

        this(object);
        try {

            this.objectFileName = objectFileName;
        } catch (Exception e) {

            System.err.println(e.toString());
        }
    }

    public void setObjectToSerialize(Object object) {

        try {

            this.serializedObject = object;
        } catch (Exception e) {

            System.err.println(e.toString());
        }
    }
    
    
    public void setObjectToSerialize(Object object, String objectFileName) {

        try {

            this.setObjectToSerialize(object);
            this.objectFileName = objectFileName;
        } catch (Exception e) {

            System.err.println(e.toString());
        }
    }

    public void serialize() {

        try {

            this.fileOutputStream = new FileOutputStream(this.objectFileName);
            this.objectOutputStream = new ObjectOutputStream(this.fileOutputStream);

            this.objectOutputStream.writeObject(this.serializedObject);

            this.objectOutputStream.close();
            this.fileOutputStream.close();

        } catch (Exception e) {

            System.err.println(e.toString());
        }
    }

    public Object getSerializedObject(String objectFileName) {

        try {

            this.fileInputStream = new FileInputStream(objectFileName);
            this.objectInputStream = new ObjectInputStream(fileInputStream);
            this.serializedObject = this.objectInputStream.readObject();

            this.objectInputStream.close();
            this.fileInputStream.close();
            
            return this.serializedObject;
        } catch (IOException | ClassNotFoundException e) {

            System.err.println(e.toString());
            return null;
        }
    }
    
    public void removeSerializationFile(){
        
        try {
            
            new File("object.ser").delete();
        } catch (Exception e) {
            
            System.err.println(e.toString());
        }
    }
}
