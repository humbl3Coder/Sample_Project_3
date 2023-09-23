package model;

import java.time.LocalTime;

public class EndHours {

    private LocalTime endTime;

    public EndHours(java.time.LocalTime localTime) {
        this.endTime = localTime;
    }

    public LocalTime getEndLT() {
        return endTime;
    }

    public void setEndLT(LocalTime localTime) {
        this.endTime = localTime;
    }

    @Override
    public String toString() { return (endTime.toString()); }
}
