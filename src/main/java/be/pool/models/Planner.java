package be.pool.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Planner {

    private List<Timer> timerList = new ArrayList<>();
    private Controller controller = new TestController();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("Y-MM-dd hh:mm");

    public static boolean isNowBetweenDates(LocalDateTime earliest, LocalDateTime latest) {
        int diffNowStart = LocalDateTime.now().compareTo(earliest);
        int diffNowEnd = LocalDateTime.now().compareTo(latest);
        if (diffNowStart > 0 && diffNowEnd < 0) {
            return true;
        }
        return false;

    }

    public List<Timer> getTimerList() {
        return timerList;
    }

    public void setTimerList(List<Timer> timerList) {
        this.timerList = timerList;
    }

    public void addTimers(Timer timer) {
        timerList.add(timer);
    }

    public void run() throws InterruptedException {

        System.out.println(dateNowFormatted()+ ": run started");

        LocalDateTime start = LocalDateTime.now();

        Thread.sleep(Timer.durationInMilliSeconds(LocalDateTime.now(),getMinStartDate()));



            while (isMustRun()) {
                if (isTimerOn() && !controller.getStatusSwitch()) {
                    controller.setSwitch(true);
                    System.out.println(dateNowFormatted() + ": " + controller.toString());
                } else if (!isTimerOn() && controller.getStatusSwitch()) {
                    controller.setSwitch(false);
                    System.out.println(dateNowFormatted() + ": " + controller.toString());
                }
                Thread.sleep(5000);
            }


        controller.close();

        System.out.println(dateNowFormatted()+ ": run if finished");
    }

    public String dateNowFormatted(){
        return LocalDateTime.now().format(formatter);
    }

    public boolean isTimerOn() {

        for (Timer timer : timerList) {
            if (timer.isTimerOn()) {
                return true;
            }
        }

        return false;
    }

    public boolean isMustRun() {
        return isNowBetweenDates(getMinStartDate(), getMaxEndDate().plusMinutes(2));
    }

    public LocalDateTime getMinStartDate() {
        Optional<Timer> optMin = timerList
                .stream()
                .min(Comparator.comparing(Timer::getStartDateTime));
        return optMin.get().getStartDateTime();
    }

    public LocalDateTime getMaxEndDate() {
        Optional<Timer> optMax = timerList
                .stream()
                .max(Comparator.comparing(Timer::getEndDateTime));
        return optMax.get().getEndDateTime();
    }


}
