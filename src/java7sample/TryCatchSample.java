/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java7sample;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author TanakaTa
 */
public class TryCatchSample {

public void dumpSysdate() {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        conn = getConnection();
        String sql = "SELECT SYSDATE FROM DUAL";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
        rs.close();
        ps.close();
        conn.close();
    } catch (SQLException e) {
        if (rs != null) { try { rs.close(); } catch (SQLException e1) {} }
        if (ps != null) { try { ps.close(); } catch (SQLException e1) {} }
        if (conn != null) { try { conn.close(); } catch (SQLException e1) {} }
    }
}

    public void dumpSysdate2() {
        try (Connection conn = getConnection();
             conn.setReadOnly(true);
             String sql = "SELECT SYSDATE FROM DUAL";
             PreparedStatement ps
                     = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            //try節内と、Close時の両方の例外をキャッチする
        }
    }

    public static Connection getConnection() {
        return null;
    }

    public static class FirstException extends Exception {

    }

    public static class SecondException extends Exception {

    }

    public void rethrowException(String exceptionName) 
            throws FirstException, SecondException {
        try {
            if (exceptionName.equals("First")) {
                throw new FirstException();
            } else {
                throw new SecondException();
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
