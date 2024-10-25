package model;

public class Team {
    private String teamName;
    private String country;
    private Player[] players;
    private String coachName;
    private int matchesPlayed;
    private int matchesWon;
    private int matchesLost;
    private int goalsFor;
    private int goalsAgainst;
    private int yellowCards;
    private int redCards;

    public Team(String teamName, String country, String coachName) {
    
        this.teamName = teamName;
        this.country = country;
        this.coachName = coachName;
    
    }

    public Team(String teamName, String country, String coachName, int matchesPlayed, int matchesWon, int matchesLost, int goalsFor, int goalsAgainst, int yellowCards, int redCards) {
        
        this.teamName = teamName;
        this.country = country;
        this.coachName = coachName;
        this.matchesPlayed = matchesPlayed;
        this.matchesWon = matchesWon;
        this.matchesLost = matchesLost;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.yellowCards = yellowCards;
        this.redCards = redCards;

        this.players = new Player[20];


    }

    //Usable methods
    public void addPlayer(Player player){
        for(int i=0; i<players.length; i++){
            if(players[i]==null){
                players[i]=player;
                System.out.println("Player successfully registered");
                break;
            } else {
                System.out.println("Sorry. This team is already full");
            }
        }
    }
    

    //Getters & Setters
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getMatchesWon() {
        return matchesWon;
    }

    public void setMatchesWon(int matchesWon) {
        this.matchesWon = matchesWon;
    }

    public int getMatchesLost() {
        return matchesLost;
    }

    public void setMatchesLost(int matchesLost) {
        this.matchesLost = matchesLost;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public void setRedCards(int redCards) {
        this.redCards = redCards;
    }
    

}
