package model;

public class Controller {
    private int roundCount; 
    /*  This number is used to keep track of each stage of the code. 
        if == 0, then we are at the registration stage
        if == 1, registration state has finished, group stage has begun
        if == 2, group stage is over, semi finals begin
        if == 3, semifinals are over, finals begin
        if == 4, tournament has concluded, prizes are awarded.
     */
    private Team[] teams;
    private Player[] players;
    private Referee[] referees;
    private int teamCount;
    private int playerCount;
    private int refereeCount;
    public Controller(int roundCount, Team[] teams, Player[] players, Referee[] referees, int teamCount, int playerCount, int refereeCount) {
        this.roundCount=0;
        this.teams= new Team[8]; //Only 8 teams allowed to be divided in 2 groups of 4
        this.players = new Player[160]; //20 players per team: 20*8=160 total players.
        this.referees= new Referee[12]; // 12 referees allowed in tournament. 4 Central, 8 Assistants.
        this.teamCount = 0;
        this.playerCount = 0;
        this.refereeCount = 0;
    }
    public void registerPlayer(String playerName, int playerNum, String country, int goalsScored, int assists, int yellowCards, int redCards, int matchesPlayed){
        players[playerCount]= new Player(playerName, playerNum, country, goalsScored, assists, yellowCards, redCards, matchesPlayed);
        playerCount++;
    }
    public void registerTeam(String teamName, String country, Player[] players, String coachName, int matchesPlayed, int matchesWon, int matchesLost, int goalsFor, int goalsAgainst, int yellowCards, int redCards){
        teams[teamCount] = new Team(teamName, country, players, coachName, matchesPlayed, matchesWon, matchesLost, goalsFor, goalsAgainst, yellowCards, redCards)
        teamCount++;
    }
    public void registerReferee(String refName, String refID, RefereeType refType, int matchesOfficiated, int yellowsGiven, int redsGiven){
        referees[refereeCount] = new Referee(refName, refID, refType, matchesOfficiated, yellowsGiven, redsGiven);
        refereeCount++;
    }
    
}
/*
public void associateApartment(String buildingName, int number, TypeApartment type, double rentValue){
        Building building = findBuilding(buildingName);
        if(building != null){ //Checks if building exists
            building.addApartment(new Apartment(number, type, rentValue, true));
        } else {
            System.out.println("Building not found");
        }
    }
 * public void addApartment(Apartment apartment){
        for(int i=0;i<apartments.length;i++){
            if(apartments[i]==null){
                apartments[i]=apartment;
                apartmentCount++;
                System.out.println("Apartment successfully registered");
                break;
            }
        }
    }
 */