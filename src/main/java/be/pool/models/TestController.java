package be.pool.models;

public class TestController implements Controller {

    boolean isStatusSwitch;

    @Override
    public void setSwitch(boolean isOn) {
        isStatusSwitch = isOn;
    }

    @Override
    public boolean getStatusSwitch() {
        return isStatusSwitch;
    }

    @Override
    public void setStatusSwitch(boolean statusSwitch) {
        this.isStatusSwitch = statusSwitch;
    }

    @Override
    public void close() {

    }

    @Override
    public String toString() {
        return "TestController{" +
                "isStatusSwitch=" + isStatusSwitch +
                '}';
    }
}
