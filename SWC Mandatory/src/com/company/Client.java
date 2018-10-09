package com.company;

public class Client {

    private String username;
    private String ipAdress;
    private int port;
    private boolean amAlive;

    public Client() {
    }

    public String getUsername() {
        return username;
    }

    public Client(String username, String ipAdress, int port) {
        this.username = username;
        this.ipAdress = ipAdress;
        this.port = port;
        this.amAlive=true;

    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isAmAlive() {
        return amAlive;
    }

    public void setAmAlive(boolean amAlive) {
        this.amAlive = amAlive;
    }
}
