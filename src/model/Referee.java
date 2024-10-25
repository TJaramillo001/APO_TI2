package model;

public class Referee {
    private String refName;
    private String refID;
    private RefereeType refType;
    private int matchesOfficiated;
    private int yellowsGiven;
    private int redsGiven;

    public Referee(String refName, String refID, RefereeType refType, int matchesOfficiated, int yellowsGiven, int redsGiven) {
        this.refName = refName;
        this.refID = refID;
        this.refType = refType;
        this.matchesOfficiated = matchesOfficiated;
        this.yellowsGiven = yellowsGiven;
        this.redsGiven = redsGiven;
    }
    //Getters & Setters
    public String getRefName() {
        return refName;
    }

    public void setRefName(String refName) {
        this.refName = refName;
    }

    public String getRefID() {
        return refID;
    }

    public void setRefID(String refID) {
        this.refID = refID;
    }

    public RefereeType getRefType() {
        return refType;
    }

    public void setRefType(RefereeType refType) {
        this.refType = refType;
    }

    public int getMatchesOfficiated() {
        return matchesOfficiated;
    }

    public void setMatchesOfficiated(int matchesOfficiated) {
        this.matchesOfficiated = matchesOfficiated;
    }

    public int getYellowsGiven() {
        return yellowsGiven;
    }

    public void setYellowsGiven(int yellowsGiven) {
        this.yellowsGiven = yellowsGiven;
    }

    public int getRedsGiven() {
        return redsGiven;
    }

    public void setRedsGiven(int redsGiven) {
        this.redsGiven = redsGiven;
    }
    
    
}
