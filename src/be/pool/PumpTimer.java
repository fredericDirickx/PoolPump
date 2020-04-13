package be.pool;

import java.time.LocalTime;
import java.util.Scanner;

public class PumpTimer extends Thread{
    private LocalTime start;
    private LocalTime end;
    private  boolean isPumpOn;
    private  boolean isStopTimer = true;


    public PumpTimer(){

    }

    public PumpTimer(LocalTime start, LocalTime end){
        this.start = start;
        this.end = end;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public boolean isPumpOn() {
        return isPumpOn;
    }

    public void setPumpOn(boolean pumpOn) {
        isPumpOn = pumpOn;
    }

    public  boolean isIsStopTimer() {
        return isStopTimer;
    }

    public void setIsStopTimer(boolean isStopTimer) {
        this.isStopTimer = isStopTimer;
    }



    public void pumpOnTimer(){

        String pumpStatus = isPumpOn?"Pump is running":"Pump is off";
        System.out.println(pumpStatus);


        while (isStopTimer){

            if(LocalTime.now().toSecondOfDay() > start.toSecondOfDay() &&
                    LocalTime.now().toSecondOfDay() < end.toSecondOfDay()){
                if(!isPumpOn) {
                    System.out.println("Pump is running");
                    setPumpOn(true);
                }

            }else {

                if(isPumpOn) {
                    System.out.println("Pump is off");
                    setPumpOn(false);
                }

            }
        }

        System.out.println("Pump is stopped by User");

    }





    @Override
    public void run(){
        System.out.println("pump program running");
        pumpOnTimer();
    }
}
