package be.pool;

import java.time.LocalTime;
import java.util.Scanner;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinDirection;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger;
import com.pi4j.io.gpio.trigger.GpioPulseStateTrigger;
import com.pi4j.io.gpio.trigger.GpioSetStateTrigger;
import com.pi4j.io.gpio.trigger.GpioSyncStateTrigger;
import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.event.PinEventType;
import java.util.Collection;
import java.time.LocalDateTime;


public class PumpTimer extends Thread{
    private LocalTime start;
    private LocalTime end;
    private  boolean isPumpOn;
    private  boolean isStopTimer = true;
    final GpioController gpio = GpioFactory.getInstance();
    GpioPinDigitalOutput pumpSwitch;
    
    


    public PumpTimer(){
        setPumpSwtich();
    }
    
   

    public PumpTimer(LocalTime start, LocalTime end){
        setPumpSwtich();
        this.start = start;
        this.end = end;
    }
    
    
    
    public void setPumpSwtich(){
        Collection<GpioPin> pinList = gpio.getProvisionedPins();
        
        if(  !pinList.isEmpty() ){
        for(GpioPin pin : pinList){
            
            System.out.println(pin.getName());
            
            gpio.unprovisionPin(pin);
        
        }
    }
        
       this.pumpSwitch = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29,   
                                                           "pumpSwitch",           // PIN FRIENDLY NAME (optional)
                                                           PinState.LOW);
                             pumpSwitch.low();
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
        if(pumpOn==true){
        this.pumpSwitch.high();
    }else{
        this.pumpSwitch.low();
    }
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
//             Thread.yield();
        }

        System.out.println("Pump is stopped by User");
        
        
        gpio.shutdown();
        
        gpio.unprovisionPin(pumpSwitch);

    }
    
    
      public void pumpOnTimer(int days){
          
          LocalDateTime startDay = LocalDateTime.now();
          LocalDateTime endDay = startDay.plusDays(days);

        String pumpStatus = isPumpOn?"Pump is running":"Pump is off";
        System.out.println(pumpStatus);


        while(LocalDateTime.now().compareTo(endDay)<0){

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

        System.out.println("Pump is stopped by after" + days + "days of running");
        
        
        gpio.shutdown();
        
        gpio.unprovisionPin(this.pumpSwitch);

    }





    @Override
    public void run(){
        System.out.println("pump program running");
        pumpOnTimer();
    }
}
