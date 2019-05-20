package com.hyq.zookeeper.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Objects;

/**
 * @author dibulidohu
 * @classname IPUtil
 * @date 2019/5/2016:05
 * @description
 */
public class IPUtil {

    public static String LOCAL_IP_ADDRESS = "127.0.0.1";
    public static byte[] LOCAL_IP_BYTE_ADDRESS = null;

    static {
        try {
            Enumeration<NetworkInterface> interfaceList = NetworkInterface.getNetworkInterfaces();
            if (Objects.nonNull(interfaceList)) {
                String ipAddr;
                while (interfaceList.hasMoreElements()) {
                    NetworkInterface iface = interfaceList.nextElement();
                    Enumeration<InetAddress> addrList = iface.getInetAddresses();
                    while (addrList.hasMoreElements()) {
                        InetAddress address = addrList.nextElement();
                        if (Objects.nonNull(address) && address instanceof Inet4Address && !"127.0.0.1".equals(address.getHostAddress())) {
                            LOCAL_IP_ADDRESS = address.getHostAddress();
                            LOCAL_IP_BYTE_ADDRESS = address.getAddress();
                        }
                    }
                }
            }
        } catch (Exception t) {

        }
    }
}
