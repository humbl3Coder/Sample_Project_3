package model;

public class Users {

    private int useID;
    private String useName;
    private String pass;

    public Users(int useID, String useName, String pass) {
        this.useID = useID;
        this.useName = useName;
        this.pass = pass;
    }

    public int getUseID() {
        return useID;
    }

    public String getUseName() {
        return useName;
    }

    public String getPass() {
        return pass;
    }

    @Override
    public String toString() {
        return (Integer.toString(useID));
    }
}
