package src.be.pool;

import javax.crypto.spec.PSource;
import java.time.*;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class PumpController extends PumpTimer {


    public void stopByUser(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter 0 to stop");
        int answer = input.nextInt();
        if(answer == 0){
            setIsStopTimer(false);
        }
    }

    @Override
    public void run(){
        stopByUser();
    }





}
