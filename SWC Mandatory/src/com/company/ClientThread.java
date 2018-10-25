package com.company;

import javafx.concurrent.Task;
import sun.net.www.http.KeepAliveCache;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ClientThread extends Thread {
    public Socket getSocket() {
        return socket;
    }

    private Client client;

    Socket socket;
    Scanner in;
    PrintWriter out;
    int name=0;
    Server server;
    boolean amAlive = true;
    boolean doRun = true;

    public ClientThread() {
    }

    public ClientThread(Socket socket, Client client, Server server) {

        this.client = client;
        this.server = server;

        this.socket = socket;
        try {
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(
                    socket.getOutputStream(),true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        out.println("Type messages now, send with ENTER");
        String received = "";
        System.out.println("Starting heartbeat thread");

        Alive alive = new Alive(ClientThread.this);
        alive.start();

        while(this.doRun){

           try{
               received = in.nextLine();
           }catch (NoSuchElementException e){
               System.out.println("Client "+client.getUsername().toUpperCase()+" shut down unexpected at "+LocalTime.now().toString());
               doRun=false;
           }

            if(received.length()>250){
                out.println("**** J_ERR: Message above 250 characters.");
                break;
            }

            else if(received!=null || (received.length() > 0)){
                String[] temparray = received.split(" ");
                switch (temparray[0]){

                    case "IMAV":
//                        client.setDate(LocalDateTime.now());
                        break;

                    case "QUIT":
                        close(this);
                        this.doRun=false;
                        break;

                    case "WHOSIN":
                        server.whoisIn(this);
                        break;

                    case "DATA":
                        String received1;
                        temparray = received.split(":");
                        received1 = temparray[1];
                        server.sendMsg(client, received1);
                        break;

                    default:  out.println("**** J_ERR: UNKOWN COMMAND");

                }
            }

            else{
                out.println("**** J_ERR: EMPTY COMMAND NOT ACCEPTED");
            }





//            if(received.equalsIgnoreCase("IMAV")){
//                client.setDate(LocalDateTime.now());
//            }
//
//        if(received.equalsIgnoreCase("QUIT")){
//            close(this);
//            this.doRun=false;
//        }
//        if(received.equalsIgnoreCase("WHOSIN")){
//          server.whoisIn(this);
//        }
//
//        else{
//            server.sendMsg(client,received);
//        }
//

        }
    }




//    public void sendOneMsg(String msg){
//
//        for (int i = server.getClientList().size() - 1; i >= 0; i--) {
//            ClientThread t1 = server.getClientList().get(i);
//            t1.out.println(msg);
//        }
//
//
//        //        TODO: Her printes den
//        System.out.println(msg+" From Client THread");
//    }


    public void close(ClientThread targetThread){
        try {
            System.out.println("**** J_SYSTEM:  Closing Connection");
            String username = targetThread.getClient().getUsername();
            for (int i = server.getClientList().size() - 1; i >= 0; i--) {
                if(server.getClientList().get(i).getClient().getUsername().equalsIgnoreCase(username)){
                    System.out.println("Removing Client from Array"+'\n'+'\n');

                    server.sendGlobalSystemMsg(targetThread.getClient(),
                            "**** J_SYSTEM: "+
                                    server.getClientList()
                                            .get(i)
                                            .getClient()
                                            .getUsername()+
                                            " has left the room"+'\n');

                    server.getClientList().remove(server.getClientList().get(i));
                    break;
                }
            }
            targetThread.socket.close();
            System.out.println("Connection closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Client getClient() {
        return client;
    }


}
