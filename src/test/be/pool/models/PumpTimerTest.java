package be.pool.models;

import be.pool.models.Timer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

class PumpTimerTest {

    @Test
    void pumpOnTimer() {

        LocalTime start = LocalTime.now().minusMinutes(10);
        LocalTime duration = LocalTime.of(0,30);
        Timer timer = new Timer(start,duration);

        Assertions.assertTrue(timer.pumpOnTimer());


        start = LocalTime.now().plusHours(2);
        timer.setStartTime(start);

        Assertions.assertFalse(timer.pumpOnTimer());

    }
}