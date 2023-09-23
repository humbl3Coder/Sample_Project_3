package model;

public class Insured extends Patients{

    private String carrier;

    public Insured(int patID, String patName, String patAdd, String patPos, int sID, String stateName, String patPhone, int covID, String cov, String carrier) {
        super(patID, patName, patAdd, patPos, sID, stateName, patPhone, covID, cov);
        this.carrier = carrier;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

}
