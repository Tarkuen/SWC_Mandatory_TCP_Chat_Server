package com.company;

public class Alive extends Thread{

    ClientThread clientThread;
    boolean running= true;


    public Alive(ClientThread clientThread) {
        this.clientThread = clientThread;
    }

    @Override
    public void run() {

        while(running){


        System.out.println(clientThread.getClient().isAmAlive());
        if(clientThread.getClient().isAmAlive()){
            try {
                System.out.println("Thread is alive");
                sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Thread is dead");
            clientThread.close(clientThread);
        }

        }


    }


}
