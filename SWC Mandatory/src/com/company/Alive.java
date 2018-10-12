package com.company;

import java.io.IOException;

public class Alive extends Thread {

    ClientThread clientThread;
    boolean running = true;


    public Alive(ClientThread clientThread) {
        this.clientThread = clientThread;
    }

    @Override
    public void run() {

        System.out.println(clientThread.getClient().getUsername());
        System.out.println("Checking heartbeat");
                if(clientThread.amAlive){
                    System.out.println("Thread is alive");
                }
                else{
                    clientThread.close(clientThread);
                }


        }


//
//        while (running) {
//            try {
//                if (clientThread.socket.getInputStream().read() == -1) {
//                    System.out.println("Not alive");
//                    try {
//                        sleep(60000);
//                    } catch (InterruptedException e) {
//                        System.out.println("Exception " + e.getMessage() + " is caught. ");
//                    }
//                } else {
//                    System.out.println("Thread is Alive");
//                    sleep(60000);
//                }
//            } catch (IOException e) {
//                System.out.println("User: " + clientThread.getClient().getUsername() + " is not alive");
//
//                clientThread.close(clientThread);
//                break;
//
//            } catch (InterruptedException e) {
//                break;
//            }
//        }


    }



