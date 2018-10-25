package com.company;

import java.time.LocalDateTime;
import java.util.Date;

public class Client {

    private String username;
    private String ipAdress;
    private int port;
    private LocalDateTime date;



    public Client() {
    }

    public String getUsername() {
        return username;
    }

    public Client(String username, String ipAdress, int port) {
        this.username = username;
        this.ipAdress = ipAdress;
        this.port = port;
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


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
