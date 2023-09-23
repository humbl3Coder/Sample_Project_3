package model;

public class States {

    private int sID;
    private String stateName;
    private String sAbbr;

    public States(int sID, String stateName, String sAbbr) {
        this.sID = sID;
        this.stateName = stateName;
        this.sAbbr = sAbbr;
    }

    public int getsID() {
        return sID;
    }

    public String getStateName() { return stateName; }

    public String getsAbbr() { return sAbbr; }

    @Override
    public String toString() { return (sAbbr); }
}
