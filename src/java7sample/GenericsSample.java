/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java7sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author TanakaTa
 */
public class GenericsSample {
    
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        Map<String, String> map = new HashMap<String, String>();
        Map<String, List<String>> listMap = new HashMap<String, List<String>>();
    }
    
    public static void main2(String[] args) {
        List<String> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        Map<String, List<String>> listMap = new HashMap<>();
    }
}
