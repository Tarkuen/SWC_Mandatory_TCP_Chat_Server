package com.company;

import java.net.*;
import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {

    Socket socket;
    Scanner in;
    PrintWriter out;
    boolean amAlive = true;

    Scanner userEntry;
    String ip, username;
    int port;


    public Client(String username, String ip, int port) {
        this.username = username;
        this.ip = ip;
        this.port = port;
        try {
            socket = new Socket(ip, port);
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
            userEntry = new Scanner(System.in);
            amAlive = true;
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    Write
    public void run() {


        ClientThread ct1 = new ClientThread(this);
        ct1.start();


        out.println(username);
        out.println(ip);
        out.println(port);
//        out.println("IMAV");

        while (true) {
            if (ct1.isAlive()) {

                String msg;
                msg = userEntry.nextLine();
                out.println(msg);
            } else {

                break;
            }


        }

    }


    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Scanner getIn() {
        return in;
    }

    public void setIn(Scanner in) {
        this.in = in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public boolean isAmAlive() {
        return amAlive;
    }

    public void setAmAlive(boolean amAlive) {
        this.amAlive = amAlive;
    }

    public Scanner getUserEntry() {
        return userEntry;
    }

    public void setUserEntry(Scanner userEntry) {
        this.userEntry = userEntry;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void sendMessage(String msg) {
        out.write(msg);
    }

}
