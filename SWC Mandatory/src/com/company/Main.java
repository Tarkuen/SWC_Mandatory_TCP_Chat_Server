package com.company;

public class Main {

    public static void main(String[] args) {

    Server server = new Server(6000);
    Thread t1 = new Thread(server);
//    TODO: Starting Server Thread, for accepting Clients
    t1.start();



    }
}
