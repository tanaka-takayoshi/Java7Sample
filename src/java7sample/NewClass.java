/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java7sample;

/**
 *
 * @author TanakaTa
 */
public class NewClass {
    public static void main(String[] args) {
        // TODO code application logic here
        MyClass<Integer> myObject = new MyClass<>("");
    }
    
    public static class MyClass<T> {
        <T> MyClass(T t){
            
        }
    }
}
