/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java7sample;

import java.io.Closeable;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sun.security.x509.X509CertImpl;

/**
 *
 * @author TanakaTa
 */
public class Java7Sample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MyException1, MyException2 {
//        Map<String, List<Date>> datesMap = new HashMap<>();
//
//        
//        System.out.println(datesMap);
//        
        try {
            throwException();
        } catch (MyException1 
                | MyException2 | Throwable ex) {
            
        }
//        
//        try {
//            throwException();
//        } catch (Exception ex) {
//            throw ex;
//        }
        try (MyResource rs = new MyResource();
                MyResource rs1 = new MyResource();) {
            //rs.hoge();
        } catch(IOException exception) {
           // exception.printStackTrace();
            for (Throwable throwable : exception.getSuppressed()) {
                throwable.printStackTrace();
            }
        }
    }
    
    public static class MyResource implements Closeable {

        @Override
        public void close() throws IOException {
            throw new IOException("Not supported yet.");
        }
        
        public void hoge() {
            throw new RuntimeException("#");
        }
    }
    
    private static void throwException() throws MyException1, MyException2 {
        throw new MyException1();
    }
    
    public static class MyException1 extends Exception {
        public String getString() {
            return "MyException1";
        }        
    }
    
    public static class MyException2 extends Exception {
        public String getString() {
            return "MyException2";
        }        
    }
    
public void operate(String status) {
    if (status.equals("stopped")) {
        //操作
    } else if (status.equals("stopping")) {
        //操作
    } else if (status.equals("pending")) {
        //操作
    } else if (status.equals("running")) {
        //操作
    } else {
        //操作
    }
}
    
public void operate2(String status) {
    switch (status) {
        case "stopped":
            //操作
        case "stopping":
            //操作
        case "pending":
            //操作
        case "running":
            //操作
        default:
            //操作
    }
}
    
    public enum StatusType {
        STOPPED("stopped"),
        STOPPING("stopping"),
        PENDING("pending"),
        RUNNING("running");
        
        private final String text;

        private StatusType(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }   
    }
    
    private Map<String, List<Date>> datesMap = new HashMap<>();
    
}
