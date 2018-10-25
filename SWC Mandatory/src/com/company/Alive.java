package com.company;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.List;

public class Alive extends Thread {

    ClientThread clientThread;
    boolean running = true;


    public Alive(ClientThread clientThread) {
        this.clientThread = clientThread;
    }

    @Override
    public void run() {

        do{
        try {
            sleep(3000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

            LocalDateTime now = LocalDateTime.now();
            LocalDateTime now2 = clientThread.getClient().getDate();
            LocalTime nowTime = now.toLocalTime();
            LocalTime nowTime2 = now2.toLocalTime();

            long seconds = ChronoUnit.SECONDS.between(nowTime,nowTime2);
            System.out.println(seconds);
            if(seconds<-61){
                clientThread.close(this.clientThread);
            }
        }

        while(!clientThread.socket.isClosed());



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



