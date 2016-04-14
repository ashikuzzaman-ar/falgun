/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studevs.io;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author ashik
 */
public class ReadTextFile {
    
    private Scanner scanner;
    
    public ReadTextFile(String filePath){
        
        try {
            
            this.scanner = new Scanner(new File(filePath));
        } catch (Exception e) {
            
            System.err.println(e.toString());
        }
    }

    public void setFilePath(String filePath) {
        
        try {
            
            this.scanner = new Scanner(new File(filePath));
        } catch (Exception e) {
            
            System.err.println(e.toString());
        }
    }
    
    
    
    public boolean hasNext(){
        
        try {
            
            return this.scanner.hasNext();
        } catch (Exception e) {
            
            System.err.println(e.toString());
            return false;
        }
    }
    
    public String readNextWord(){
        
        try {
            
            return this.scanner.next();
        } catch (Exception e) {
            
            System.err.println(e.toString());
            return null;
        }
    }
    
    public String readNextLine(){
        
        try {
            
            return this.scanner.nextLine();
        } catch (Exception e) {
            
            System.err.println(e.toString());
            return null;
        }
    }
    
    public void close(){
        
        try {
            
            this.scanner.close();
        } catch (Exception e) {
            
            System.err.println(e.toString());
        }
    }
}
