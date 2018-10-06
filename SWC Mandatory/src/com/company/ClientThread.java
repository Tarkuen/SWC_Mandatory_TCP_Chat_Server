package com.company;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class ClientThread extends Thread {
    public Socket getSocket() {
        return socket;
    }

    private Client client;

    Socket socket;
    Scanner in;
    PrintWriter out;

    Server server;

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

        String received = "";
        while (!received.equalsIgnoreCase("QUIT")) {

            received = in.nextLine();
            server.sendMsg(received);
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void sendOneMsg(String msg){

        for (int i = server.getClientList().size() - 1; i >= 0; i--) {
            ClientThread t1 = server.getClientList().get(i);
            t1.out.println(msg);
        }


        //        TODO: Her printes den
        System.out.println(msg+" From Client THread");
    }



    public Client getClient() {
        return client;
    }


}
