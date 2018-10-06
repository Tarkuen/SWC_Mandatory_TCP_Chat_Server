package com.company;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ClientThread extends Thread {


    private Client client;

    Socket socket;
    BufferedReader in;
    BufferedWriter out;

    Server server;

    public ClientThread() {
    }

    public ClientThread(Socket socket, Client client, Server server) {
        this.socket = socket;
        this.client=client;
        this.server = server;

        try {
           in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public synchronized void run() {

        while(true) {
            try {
                String msg = in.readLine();
                server.sendMsg(msg);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        }

    public synchronized void sendOneMsg(String msg){
        try {
            out.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        TODO: Her printes den
        System.out.println(msg+"From Client THread");
    }



    public Client getClient() {
        return client;
    }


}
