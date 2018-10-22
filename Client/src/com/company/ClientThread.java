package com.company;

import java.io.IOException;

public class ClientThread extends Thread {

    Client client;

    public ClientThread(Client client) {
        this.client = client;
    }


    //    Listen
    @Override
    public void run() {
        while (true) {
            if (client.in.hasNextLine()) {
                String response = client.in.nextLine();
                System.out.println(response);
            } else {
                System.out.println("**** J_SYSTEM:  " + "Closed connection." + '\n');
                System.out.println("**** J_CLIENT: Shutting Down Client: " + client.getUsername() + '\n' + "**** J_CLIENT: Press Enter to restart.");
                break;
            }

        }
    }
}
