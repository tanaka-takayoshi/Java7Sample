/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java7sample;

/**
 *
 * @author TanakaTa
 */
public class StringSwitchSample {
    
    public void operate(String status) {
        switch (status) {
            case "stopped":
                
            case "stopping":
                
            case "running":
                
            case "terminated":
                
            default:
                    
        }
    }
    
    public void operate2(String status) {
        if (status.equals("stopped")) {
            
        } else if (status.equals("stopping")) {
            
        } else if (status.equals("running")) {
            
        } else if (status.equals("terminated")) {
            
        } else {
            
        }
    }
    
    public static void main(String[] args) {
        new StringSwitchSample().operate(null);
    }
}
