package com.company;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Server{

    //    ConnectionID
    private int uniqueConnectionID;
    //    port
    private int port;

    //    Client Listen
    private List<ClientThread> clientList = null;

    //    ServerSocket
    private ServerSocket server = null;

    //    boolean
    private boolean listen = true;

//      uniqueUserID
        private int userIDGen;




    public Server(int port) {
            this.port = port;
            System.out.println("Starting Server Object on port: "+port);
            clientList = new ArrayList<>();

    }


    public void start(){
        try {

            server = new ServerSocket(port);

            while(listen){
                System.out.println("Listening for a client");
                Socket socket = server.accept();

                System.out.println("Client Accepted");

                ClientThread newClient = new ClientThread(socket, new Client(), Server.this);

//                TODO: Check Username

                clientList.add(newClient);
                System.out.println("Client added to list");

//                starting Client Thread
             newClient.start();

            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Client on Server Socket, on port: "+port);
        }
    }


    public synchronized void sendMsg(String msg){

        for (int i =clientList.size(); --i>=0; ) {

            ClientThread tempClient = clientList.get(i);
            System.out.println(msg+"From SendMSG in SERVER");
            tempClient.sendOneMsg(msg);

            if(tempClient.socket.isConnected()){

            }

            else{
                System.out.println("Client Socket is close ??: "+tempClient);
                clientList.remove(tempClient);
            }


        }



    }




}
