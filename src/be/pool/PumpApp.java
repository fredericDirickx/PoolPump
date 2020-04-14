package be.pool;

import org.w3c.dom.ls.LSOutput;

import java.time.Duration;
import java.time.LocalTime;
import java.time.Period;
import java.util.Timer;
import java.util.TimerTask;

public class PumpApp {





    public static void main(String[] args) {



        LocalTime start = LocalTime.of(16,51);
        LocalTime end = LocalTime.of(16,53);
        Thread pumpTimer = new PumpTimer(start,end);
        Thread pumpStop = new PumpController();

        pumpTimer.start();
        pumpStop.start();









    }



}
