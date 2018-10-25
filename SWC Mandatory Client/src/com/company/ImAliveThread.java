package com.company;

public class ImAliveThread extends Thread {

    Client client;

    public ImAliveThread(Client client) {
        this.client = client;
    }


    @Override
    public void run() {

        while (true) {
            try {
                sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!client.socket.isClosed()) {
                client.out.println("IMAV");
            } else {
                break;
            }
        }


    }
}
