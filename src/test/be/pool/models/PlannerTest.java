package be.pool.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PlannerTest {

    Planner planner = new Planner();
    LocalTime start = LocalTime.now();
    LocalTime duration = LocalTime.of(1,1);
    Timer timer = new Timer(start,duration);


    @Test
    void getTotalDuration() {

        Timer a = new Timer(start.minusMinutes(2),duration);
        Timer b = new Timer(start.plusMinutes(4),duration);
        Timer c = new Timer(start.plusMinutes(6),duration);
        Timer d = new Timer(start.plusMinutes(8),duration);
        planner.getTimerList().add(a);
        planner.getTimerList().add(b);
        planner.getTimerList().add(c);
        planner.getTimerList().add(d);

        Optional<Timer> optMin = planner.getTimerList().stream().min(Comparator.comparing(Timer::getStartDateTime));
        Optional<Timer> optMax = planner.getTimerList().stream().max(Comparator.comparing(Timer::getStartDateTime));

        Assertions.assertEquals(optMin.get(),a);
        Assertions.assertEquals(optMax.get(),d);


    }


    @Test
    void isMustRunTest(){
        LocalTime duration = LocalTime.of(0,1);
        Timer a = new Timer(start,duration);
        planner.addTimers(a);

        Assertions.assertTrue(planner.isTimerOn());

    }


    @Test
    void isNowBetweenDates(){
        planner.addTimers(timer);
        System.out.println(planner.getMinStartDate());
        System.out.println(planner.getMaxEndDate());
        System.out.println(planner.isMustRun());
    }

}