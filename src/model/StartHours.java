package model;

import java.time.LocalTime;

public class StartHours {

    private LocalTime startTime;

    public StartHours(LocalTime localTime) {
        this.startTime = localTime;
    }

    public LocalTime getStartLT() {
        return startTime;
    }

    public void setStartLT(LocalTime localTime) {
        this.startTime = localTime;
    }

    @Override
    public String toString() {
        return (startTime.toString());
    }

}
