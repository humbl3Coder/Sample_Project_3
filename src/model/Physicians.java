package model;

public class Physicians {

    private int physID;
    private String pName;
    private String pEmail;

    public Physicians(int physID, String pName, String pEmail) {
        this.physID = physID;
        this.pName = pName;
        this.pEmail = pEmail;
    }

    public int getPhysID() {
        return physID;
    }

    public String getpName() {
        return pName;
    }

    public String getpEmail() {
        return pEmail;
    }

    @Override
    public String toString() {
        return pName;
    }
}
