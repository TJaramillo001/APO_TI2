package model;

public class Referee extends Person{

    private String refID;
    private RefereeType refType;
    private int matchesOfficiated;
    private int yellowsGiven;
    private int redsGiven;
    /**
     * Description: Initial constructor class used for referee registration
     * @param String refName : Corresponds to the referee name
     * @param String refID : Corresponds to the unique referee ID
     * @param RefereeType refType : CENTRAL or ASSISTANT
     * @param String country : Corresponds to the country of origin of the referee
     */
    public Referee(String refName, String refID, RefereeType refType, String country) {
        super(refName, country);
        this.refID = refID;
        this.refType = refType;
    }
    /**
     * Description: Constructor class used for referee registration which includes all data
     * @param String refName : Corresponds to the referee name
     * @param String refID : Corresponds to the unique referee ID
     * @param RefereeType refType : CENTRAL or ASSISTANT
     * @param String country : Corresponds to the country of origin of the referee
     * @param int matchesOfficiated : Corresponds to the amount of matches this referee has officiated
     * @param int yellowsGiven : Corresponds to the amount of yellow cards this referee has given.
     * @param int redsGiven : Corresponds to the amount of red cards this referee has given.
     */
    public Referee(String refName, String refID, RefereeType refType, String country, int matchesOfficiated, int yellowsGiven, int redsGiven) {
        super(refName, country);
        this.refID = refID;
        this.refType = refType;
        this.matchesOfficiated = matchesOfficiated;
        this.yellowsGiven = yellowsGiven;
        this.redsGiven = redsGiven;
    }
    //Getters & Setters

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

    public void incrementMatchesOfficiated() {
        this.matchesOfficiated++;
    }

    public int getYellowsGiven() {
        return yellowsGiven;
    }

    public int getRedsGiven() {
        return redsGiven;
    }

    public void incrementYellowCards(){
        this.yellowsGiven++;
    }

    public void incrementRedCards(){
        this.redsGiven++;
    }

    

}
