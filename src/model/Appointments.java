package model;

import java.time.LocalDateTime;

public class Appointments {

    private int appID;
    private String title;
    private String desc;
    private String loc;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int patID;
    private int useID;
    private int phyID;
    private String phyName;

    public Appointments(int appID, String title, String desc, String loc, String type, LocalDateTime start, LocalDateTime end, int patID, int useID, int phyID, String phyName) {
        this.appID = appID;
        this.title = title;
        this.desc = desc;
        this.loc = loc;
        this.type = type;
        this.start = start;
        this.end = end;
        this.patID = patID;
        this.useID = useID;
        this.phyID = phyID;
        this.phyName = phyName;
    }

    public int getAppID() {
        return appID;
    }

    public void setAppID(int appID) {
        this.appID = appID;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public int getPatID() {
        return patID;
    }

    public void setPatID(int patID) {
        this.patID = patID;
    }

    public int getUseID() {
        return useID;
    }

    public void setUseID(int useID) {
        this.useID = useID;
    }

    public int getPhyID() {
        return phyID;
    }

    public void setPhyID(int phyID) {
        this.phyID = phyID;
    }

    public String getPhyName() { return phyName; }

    public void setPhyName(String phyName) { this.phyName = phyName; }
}
