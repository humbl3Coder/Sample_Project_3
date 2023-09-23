package model;

public abstract class Patients {

    private int patID;
    private String patName;
    private String patAdd;
    private String patPos;
    private int sID;
    private String stateName;
    private String patPhone;
    private int patCovID;
    private String patCov;

    public Patients(int patID, String patName, String patAdd, String patPos, int sID, String stateName, String patPhone, int patCovID, String patCov) {
        this.patID = patID;
        this.patName = patName;
        this.patAdd = patAdd;
        this.patPos = patPos;
        this.sID = sID;
        this.stateName = stateName;
        this.patPhone = patPhone;
        this.patCovID = patCovID;
        this.patCov = patCov;
    }

    public int getPatID() {
        return patID;
    }

    public void setPatID(int patID) {
        this.patID = patID;
    }

    public String getPatName() {
        return patName;
    }

    public void setPatName(String patName) {
        this.patName = patName;
    }

    public String getPatAdd() {
        return patAdd;
    }

    public void setPatAdd(String patAdd) {
        this.patAdd = patAdd;
    }

    public String getPatPos() {
        return patPos;
    }

    public void setPatPos(String patPos) {
        this.patPos = patPos;
    }

    public int getsID() {
        return sID;
    }

    public void setsID(int sID) {
        this.sID = sID;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getPatPhone() {
        return patPhone;
    }

    public void setPatPhone(String patPhone) {
        this.patPhone = patPhone;
    }

    public int getPatCovID() { return patCovID; }

    public void setPatCovID(int patCovID) { this.patCovID = patCovID; }

    public String getPatCov() { return patCov; }

    public void setPatCov(String patCov) { this.patCov = patCov; }

    @Override
    public String toString() {
        return patName;
    }
}





