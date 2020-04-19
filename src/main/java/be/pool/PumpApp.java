package be.pool;

import org.w3c.dom.ls.LSOutput;

import java.time.Duration;
import java.time.LocalTime;
import java.time.Period;
import java.util.Timer;
import java.util.TimerTask;

public class PumpApp {





    public static void main(String[] args) throws Exception {



        LocalTime start = LocalTime.of(15,59);
        LocalTime end = LocalTime.of(16,00);
       PumpTimer pumpTimer = new PumpTimer(start,end);
       
       
       pumpTimer.pumpOnTimer(3);










    }



}
