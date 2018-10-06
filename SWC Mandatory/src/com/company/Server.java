package com.company;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread{

    //    ConnectionID
    private int uniqueConnectionID;
    //    port
    private int port;

    //    Client Listen
    private List<ClientThread> clientList = null;

    //    ServerSocket
    private ServerSocket server;

    //    boolean
    private boolean listen = true;

//      uniqueUserID
        private int userIDGen;

//        Thread
//
//    private Thread thread;



    public Server(int port) {
        try {
            this.port = port;
            System.out.println("Starting Server Object on port: " + port);
            clientList = new ArrayList<>();
            server = new ServerSocket(port);
            start();

        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void start(){

        boolean isrunning = true;

            while (isrunning) {
                System.out.println("Listening for a client");
                Socket socket = null;
                try {
                    socket = server.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Client Accepted");

                ClientThread newClient = new ClientThread(socket, new Client(), Server.this);
                System.out.println(newClient);

                clientList.add(newClient);
                System.out.println("Client added to list");

                Thread t2 = new Thread(newClient);
                t2.start();



            }

            }




    public void sendMsg(String msg) {

        for (int i = getClientList().size() - 1; i >= 0; i--) {
            ClientThread t1 = getClientList().get(i);
            t1.out.println(msg);
        }
    }


    public List<ClientThread> getClientList() {
        return clientList;
    }
}
