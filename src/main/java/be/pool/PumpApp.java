package be.pool;

import be.pool.models.Planner;
import be.pool.models.Timer;
import org.hibernate.query.criteria.internal.predicate.ImplicitNumericExpressionTypeDeterminer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PumpApp {





    public static void main(String[] args) throws Exception {

        LocalTime shortDuration = LocalTime.of(0,1);
        LocalTime longDuration = LocalTime.of(0,0);

        Planner planner = new Planner();

        LocalTime time = LocalTime.now();

        planner.addTimers(new Timer(LocalTime.of(0,0),longDuration,3));
        planner.addTimers(new Timer(LocalTime.of(9,0),shortDuration,3));
        planner.addTimers(new Timer(LocalTime.of(12,0),shortDuration,3));
        planner.addTimers(new Timer(LocalTime.of(18,0),shortDuration,3));

    }



}
