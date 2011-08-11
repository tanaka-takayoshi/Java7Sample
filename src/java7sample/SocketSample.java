/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java7sample;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.net.UnknownHostException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;

/**
 *
 * @author TanakaTa
 */
public class SocketSample {
    
    public void hoge() throws SocketException, UnknownHostException, IOException {
        NetworkInterface interf = NetworkInterface.getByName("eth0");
        InetAddress group = InetAddress.getByName("225.0.0.100");

        DatagramChannel dc = DatagramChannel.open(StandardProtocolFamily.INET)
            .setOption(StandardSocketOptions.SO_REUSEADDR, Boolean.TRUE)
            .bind(new InetSocketAddress(5000))
            .setOption(StandardSocketOptions.IP_MULTICAST_IF, interf);

        MembershipKey key = dc.join(group, interf); 
    }
}
