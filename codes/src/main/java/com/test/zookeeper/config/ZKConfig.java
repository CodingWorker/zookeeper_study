package com.test.zookeeper.config;


/**
 * Created by daiya on 2017/9/7.
 */
public class ZKConfig {
    private String ipAddress;
    private int port;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
