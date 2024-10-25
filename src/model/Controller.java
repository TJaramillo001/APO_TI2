package model;
import model.*;

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

    public Controller() {
        this.roundCount=0;
        this.teams= new Team[8]; //Only 8 teams allowed to be divided in 2 groups of 4
        this.players = new Player[160]; //20 players per team: 20*8=160 total players.
        this.referees= new Referee[12]; // 12 referees allowed in tournament. 4 Central, 8 Assistants.
        this.teamCount = 0;
        this.playerCount = 0;
        this.refereeCount = 0;
    }
    public void registerPlayer(String playerName, int playerNum, PlayerPosition playerPosition, String country, String teamName){
        Team teamToAdd = findTeam(teamName);
        if (teamToAdd!=null){
        
            players[playerCount]= new Player(playerName, playerNum, playerPosition, country, 0, 0, 0, 0, 0); 
            playerCount++;
            // 0's correspond to goals scored, assists, yellows, reds and matches played. 
            teamToAdd.addPlayer(new Player(playerName, playerNum, playerPosition, country,0,0,0,0,0));
            System.out.println("Player successfully added to "+teamToAdd);
        
        } else{
            System.out.println("Sorry, that team was not found.");
        }
    }

    public void registerTeam(String teamName, String country, String coachName){
        teams[teamCount] = new Team(teamName, country, coachName, 0, 0, 0, 0, 0, 0, 0);
        teamCount++;
    }
    public Team findTeam(String name){
        for (int i=0; i<teamCount; i++){
            if(teams[i].getTeamName().equalsIgnoreCase(name)){
                return teams[i];
            }
        }
        return null;
    }

    public void registerReferee(String refName, String refID, RefereeType refType){
        referees[refereeCount] = new Referee(refName, refID, refType, 0, 0, 0);
        refereeCount++;
    }
    
}
