package be.pool.models;
import com.pi4j.io.gpio.*;
import java.util.Collection;


public class PumpController implements Controller {

    private final GpioController gPio = GpioFactory.getInstance();
    private boolean isStatusSwitch;
    private GpioPinDigitalOutput pumpSwitch;


    public PumpController() {
        setUpGioPinForSwitch();
    }

    public void setUpGioPinForSwitch() {
        Collection<GpioPin> pinList = gPio.getProvisionedPins();

        if (!pinList.isEmpty()) {
            for (GpioPin pin : pinList) {

                gPio.unprovisionPin(pin);

            }
        }

        this.pumpSwitch = gPio.provisionDigitalOutputPin(RaspiPin.GPIO_29,
                "pumpSwitch",
                PinState.LOW);
        pumpSwitch.low();
    }


    @Override
    public boolean getStatusSwitch() {
        return isStatusSwitch;
    }

    public void setStatusSwitch(boolean statusSwitch) {
        isStatusSwitch = statusSwitch;
    }

    @Override
    public void setSwitch(boolean isPumpOn) {
        if (isPumpOn) {
            this.pumpSwitch.high();

        } else {
            this.pumpSwitch.low();
        }

        this.isStatusSwitch = isPumpOn;
    }





    @Override
    public void close() {
        gPio.shutdown();
    }


    @Override
    public String toString() {
        return "PumpController{" +
                "isStatusSwitch=" + isStatusSwitch +
                '}';
    }
}