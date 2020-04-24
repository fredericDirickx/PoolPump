package be.pool.models;

import java.time.*;
import java.time.temporal.Temporal;


public class Timer {

    private LocalTime startTime;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private LocalTime duration;


    public Timer(LocalTime start, LocalTime duration) {
        this.startTime = start;
        this.startDateTime = LocalDateTime
                .of(LocalDate.now(),startTime);
        this.duration = duration;
        this.endDateTime = startDateTime
                .plusHours(duration.getHour())
                .plusMinutes(duration.getMinute());
    }

    public Timer(int startHour, int startMinute, LocalTime duration) {
        this.startTime = LocalTime.of(startHour,startMinute);
        this.startDateTime = LocalDateTime
                .of(LocalDate.now(),startTime);
        this.duration = duration;
        this.endDateTime = startDateTime
                .plusHours(duration.getHour())
                .plusMinutes(duration.getMinute());
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
        this.startDateTime = LocalDateTime
                .of(LocalDate.now(),startTime);
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
        this.startTime = LocalTime.of(startDateTime.getHour(),startDateTime.getMinute());
        this.endDateTime = startDateTime
                .plusHours(duration.getHour())
                .plusMinutes(duration.getMinute());
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }



    public LocalTime getDuration() {
        return duration;
    }

    public Duration getDurationAsDuration(){
        Duration d =
                Duration.ofMinutes(duration.getMinute() + duration.getHour()*60);
        return d;
    }


    public static long durationInMilliSeconds(LocalDateTime start, LocalDateTime end){
        return Duration.between(start,end).toMillis();
    }



    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public boolean pumpOnTimer() {
        int diffNowStart = LocalDateTime.now().compareTo(startDateTime);
        int diffNowEnd =  LocalDateTime.now().compareTo(endDateTime);
        if(diffNowStart > 0  &&  diffNowEnd < 0 ){
            return true;
        }
        return false;
    }







}
