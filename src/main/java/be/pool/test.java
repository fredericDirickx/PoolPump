package be.pool;
import java.util.concurrent.TimeUnit;
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
    import java.util.*;



public class test
{
    public static void main(String [] args) throws Exception{
        
        
        
    // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();
        
        Collection<GpioPin> pinList = gpio.getProvisionedPins();
        
        
        for(GpioPin pin : pinList){
            
            System.out.println(pin.getName());
            
            gpio.unprovisionPin(pin);
        
        }
        // lookup the pin by address
       GpioPinDigitalOutput pumpSwitch = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29,   // PIN NUMBER
                                                           "pumpSwitch",           // PIN FRIENDLY NAME (optional)
                                                           PinState.LOW);
              
       
// switch ON
Scanner input = new Scanner(System.in);

String answer = "";

while(!answer.equals("q")){
System.out.println("For making pump run push y else n");
System.out.println("or q if you want to quite the programe");
   answer = input.next();

if(answer.equals("y")){
    pumpSwitch.high();
} else{
        pumpSwitch.low();
  }    
        
        Thread.sleep(1000);  
        
    }
    gpio.shutdown();
        
gpio.unprovisionPin(pumpSwitch);
        
      // switch OFF

   

    
    }
}
