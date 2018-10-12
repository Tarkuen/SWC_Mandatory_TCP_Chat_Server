package com.company;

import javafx.concurrent.Task;

import java.util.TimerTask;


public class AliveTask extends TimerTask {

    ClientThread ct1;
    Alive alive;

    public AliveTask(Alive alive) {
        this.alive=alive;
        this.ct1 = alive.clientThread;
    }

    @Override
    public void run() {
        alive.run();
    }
}
