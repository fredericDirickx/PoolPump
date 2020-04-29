package be.pool.models;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class Timer {

    private LocalTime startTime;
    private LocalTime duration;
    private LocalDateTime startDateTime;
    private long repeatDays;
    private LocalDateTime endDateTime;


    public Timer(LocalTime start, LocalTime duration, long repeatDays) {
        this.startTime = start;
        this.startDateTime = LocalDateTime
                .of(LocalDate.now(), startTime);
        this.duration = duration;
        this.repeatDays = repeatDays;
        setEndDateTime();
    }

    public Timer(int startHour, int startMinute, LocalTime duration, long repeatDays) {
        this.startTime = LocalTime.of(startHour, startMinute);
        this.startDateTime = LocalDateTime
                .of(LocalDate.now(), startTime);
        this.duration = duration;
        this.repeatDays = repeatDays;
        setEndDateTime();
    }

    private void setEndDateTime(){
        endDateTime = startDateTime
                .plusDays(repeatDays)
                .plusMinutes(duration.getMinute())
                .plusHours(duration.getHour());
    }


    public LocalDateTime getEndDateTime(){
        return this.endDateTime;
    }



    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
        this.startDateTime = LocalDateTime
                .of(LocalDate.now(), startTime);
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public long getRepeatDays() {
        return repeatDays;
    }

    public void setRepeatDays(long repeatDays) {
        this.repeatDays = repeatDays;
    }

    public Duration getDurationAsDuration() {
        Duration d =
                Duration.ofMinutes(duration.getMinute() + duration.getHour() * 60);
        return d;
    }

    public static long durationInMilliSeconds(LocalDateTime start, LocalDateTime end) {
        Long milliSeconds = Duration.between(start, end).toMillis();
        milliSeconds = milliSeconds < 0 ? 0 : milliSeconds;
        return milliSeconds;
    }

    public boolean isTimerOn() {

        int dayOfYear = LocalDateTime.now().getDayOfYear();
        LocalDateTime start = startDateTime.withDayOfYear(dayOfYear);
        LocalDateTime end = start
                .plusHours(duration.getHour())
                .plusMinutes(duration.getMinute());

        int diffNowStart = LocalDateTime.now().compareTo(start);
        int diffNowEnd = LocalDateTime.now().compareTo(end);

        return diffNowStart > 0 && diffNowEnd < 0;
    }


}
