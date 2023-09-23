package model;

public class Coverage {

    public static final int DEFAULT_COVERAGE_ID = 1;

    private int covID;
    private String cov;

    public Coverage(int covID, String cov) {
        this.covID = covID;
        this.cov = cov;
    }

    public int getCovID() { return covID; }

    public String getCov() { return cov; }

    @Override
    public String toString() { return (cov); }
}
