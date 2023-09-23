package model;

public class Uninsured extends Patients{

    private String discRate;

    public Uninsured(int patID, String patName, String patAdd, String patPos, int sID, String stateName, String patPhone, int covID, String cov, String discRate) {
        super(patID, patName, patAdd, patPos, sID, stateName, patPhone, covID, cov);
        this.discRate = discRate;
    }

    public String getDiscRate() { return discRate; }

    public void setDiscRate(String discRate) { this.discRate = discRate; }
}
