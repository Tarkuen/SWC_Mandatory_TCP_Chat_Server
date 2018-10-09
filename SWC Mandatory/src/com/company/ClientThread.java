package com.company;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

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
        String received=" ";
        System.out.println("Starting heartbeat thread");
        Alive alive = new Alive(this);
        alive.start();
        while(amAlive){
            received = in.nextLine();
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Alive not true");
                amAlive=false;
            }
            server.sendMsg(client, received);
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


    public void close(ClientThread client){
        try {
            client.out.println("J_System: Closing Connection");
            client.socket.close();
            amAlive=false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Client getClient() {
        return client;
    }


}
