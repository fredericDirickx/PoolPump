package be.pool;

import be.pool.models.Planner;
import be.pool.models.Timer;
import org.hibernate.query.criteria.internal.predicate.ImplicitNumericExpressionTypeDeterminer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class PumpApp {





    public static void main(String[] args) throws Exception {

        LocalTime shortDuration = LocalTime.of(0,1);
        LocalTime longDuration = LocalTime.of(0,2);

        Planner planner = new Planner();

        LocalTime time = LocalTime.now();

        planner.addTimers(new Timer(time.plusMinutes(1),shortDuration));
        planner.addTimers(new Timer(time.plusMinutes(3),shortDuration));
        planner.addTimers(new Timer(time.plusMinutes(5),shortDuration));

        planner.run();


    }



}
