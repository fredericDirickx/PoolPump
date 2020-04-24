package be.pool;

import be.pool.models.Planner;
import be.pool.models.Timer;
import org.hibernate.query.criteria.internal.predicate.ImplicitNumericExpressionTypeDeterminer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class PumpApp {





    public static void main(String[] args) throws Exception {

        LocalTime shortDuration = LocalTime.of(1,0);
        LocalTime longDuration = LocalTime.of(0,15);

        Planner planner = new Planner();

        LocalTime time = LocalTime.now();

        planner.addTimers(new Timer(LocalTime.of(00,00),longDuration));
        planner.addTimers(new Timer(LocalTime.of(9,00),shortDuration));
        planner.addTimers(new Timer(LocalTime.of(12,00),shortDuration));
        planner.addTimers(new Timer(LocalTime.of(18,00),shortDuration));

        planner.run();


    }



}
