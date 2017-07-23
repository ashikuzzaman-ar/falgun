/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studevs.falgun.util;

import com.studevs.falgun.io.ObjectSerialization;

/**
 *
 * @author ashik
 */
public class ObjectCopy {

    public static Object getCopyOf(Object object) {

        try {

            ObjectSerialization objectSerialization = new ObjectSerialization(object, (object.getClass().getName() + ".ser"));
            objectSerialization.serialize();

            object = objectSerialization.getSerializedObject(object.getClass().getName() + ".ser");

            objectSerialization.removeSerializationFile();

            return object;
        } catch (Exception e) {

            System.err.println(e.toString());
            return null;
        }
    }
}
