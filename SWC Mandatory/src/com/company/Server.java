package com.company;

import java.net.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server extends Thread {

    //    ConnectionID
    private int uniqueConnectionID;
    //    port
    private int port;

    //    Client Listen
    private volatile List<ClientThread> clientList = null;

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
        System.out.println("$$\\   $$\\ $$$$$$$$\\  $$$$$$\\        $$$$$$$$\\  $$$$$$\\  $$$$$$$\\         $$$$$$\\  $$$$$$$$\\ $$$$$$$\\  $$\\    $$\\ $$$$$$$$\\ $$$$$$$\\  \n" +
                "$$ | $$  |$$  _____|$$  __$$\\       \\__$$  __|$$  __$$\\ $$  __$$\\       $$  __$$\\ $$  _____|$$  __$$\\ $$ |   $$ |$$  _____|$$  __$$\\ \n" +
                "$$ |$$  / $$ |      $$ /  $$ |         $$ |   $$ /  \\__|$$ |  $$ |      $$ /  \\__|$$ |      $$ |  $$ |$$ |   $$ |$$ |      $$ |  $$ |\n" +
                "$$$$$  /  $$$$$\\    $$$$$$$$ |         $$ |   $$ |      $$$$$$$  |      \\$$$$$$\\  $$$$$\\    $$$$$$$  |\\$$\\  $$  |$$$$$\\    $$$$$$$  |\n" +
                "$$  $$<   $$  __|   $$  __$$ |         $$ |   $$ |      $$  ____/        \\____$$\\ $$  __|   $$  __$$<  \\$$\\$$  / $$  __|   $$  __$$< \n" +
                "$$ |\\$$\\  $$ |      $$ |  $$ |         $$ |   $$ |  $$\\ $$ |            $$\\   $$ |$$ |      $$ |  $$ |  \\$$$  /  $$ |      $$ |  $$ |\n" +
                "$$ | \\$$\\ $$$$$$$$\\ $$ |  $$ |         $$ |   \\$$$$$$  |$$ |            \\$$$$$$  |$$$$$$$$\\ $$ |  $$ |   \\$  /   $$$$$$$$\\ $$ |  $$ |\n" +
                "\\__|  \\__|\\________|\\__|  \\__|         \\__|    \\______/ \\__|             \\______/ \\________|\\__|  \\__|    \\_/    \\________|\\__|  \\__|\n"
        );

        try {
            this.port = port;
            System.out.println("Starting Server Object on port: " + port);
            clientList = new ArrayList<>();
            server = new ServerSocket(port);
            start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void start() {

        boolean isrunning = true;

//    TODO: True Loop in the Task for accepting Clients
        while (isrunning) {

            System.out.println("Listening for a client");

            Socket socket = null;
            try {
                socket = server.accept();
                System.out.println("Client Accepted");
                ClientThread newClient = new ClientThread(socket, new Client(), Server.this);
                newClient.out.println("J_OK: Welcome to the server");

                String input = newClient.in.nextLine();

                newClient.
                        getClient().
                        setUsername(input);

                input = newClient.in.nextLine();

                newClient.
                        getClient().
                        setIpAdress(input);

                input = newClient.in.nextLine();

                newClient.
                        getClient().
                        setPort(Integer.parseInt(input));

                newClient.
                        getClient().
                        setDate(LocalDateTime.now());


                for (ClientThread ct1 : clientList) {

                    if (newClient.getClient().
                            getUsername().
                            equalsIgnoreCase
                                    (ct1.getClient()
                                            .getUsername())) {
                        newClient.getClient()
                                .setUsername
                                        (newClient.getClient().getUsername()
                                                + uniqueConnectionID);

                        newClient.out.print("Bad Username - Added " + uniqueConnectionID + " to name" + '\n');

                        uniqueConnectionID++;
                    }
                }

                sendGlobalSystemMsg(newClient.getClient(), "**** J_SYSTEM: " + newClient.getClient().getUsername() + " has joined the room");

                clientList.add(newClient);
                System.out.println("Client added to list");

//                TODO: Thread for a recieving a Clients Messages
                Thread t2 = new Thread(newClient);
                t2.start();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

//            TODO: Send Messages to All Users
    public void sendMsg(Client client, String msg) {
        for (ClientThread ct1 : getClientList()) {
            ct1.out.println(client.getUsername() + " : " + msg);
        }
    }

//            TODO: Who is the chat room?
    public void whoisIn(ClientThread clientThread) {
        clientThread.out.println("    **** J_SYSTEM: WHO IS IN ? ****");
        clientThread.out.println("_.~\"(_.~\"(_.~\"(_.~\"(_.~\"( _.~\"(_.~\"(_.~\"(_.~\"(_.~\"(" + "\n");
        for (ClientThread ct1 : getClientList()) {
            clientThread.out.println("      " + ct1.getClient().getUsername().toUpperCase());
        }
    }
//            TODO: Send a System message
    public void sendGlobalSystemMsg(Client client, String msg) {
        for (ClientThread ct1 : getClientList()) {
            ct1.out.println("**** J_SYSTEM: " + msg);
        }
    }


    public List<ClientThread> getClientList() {
        return clientList;
    }
}
