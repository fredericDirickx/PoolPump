package be.pool.models;

public interface Controller {

    void setSwitch(boolean isOn);

    void close();

    boolean getStatusSwitch();

    public void setStatusSwitch(boolean statusSwitch);

}
