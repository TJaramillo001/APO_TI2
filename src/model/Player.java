package model;

public class Player {
    private String playerName;
    private int playerNum;
    private PlayerPosition playerPosition;
    private String country;
    private int goalsScored;
    private int assists;
    private int yellowCards;
    private int redCards;
    private int matchesPlayed;
    
    public Player(String playerName, int playerNum, PlayerPosition playerPosition, String country){
        this.playerName=playerName;
        this.playerNum=playerNum;
        this.playerPosition=playerPosition;
        this.country=country;
    }

    public Player(String playerName, int playerNum, PlayerPosition playerPosition, String country, int goalsScored, int assists, int yellowCards, int redCards, int matchesPlayed){
        this.playerName=playerName;
        this.playerNum=playerNum;
        this.playerPosition=playerPosition;
        this.country=country;
        this.goalsScored=goalsScored;
        this.assists=assists;
        this.yellowCards=yellowCards;
        this.redCards=redCards;
        this.matchesPlayed=matchesPlayed;
    }
    //Getters & Setters
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }
    
    public PlayerPosition getPlayerPosition(){
        return playerPosition;
    }

    public void setPlayerPosition(PlayerPosition playerPosition){
        this.playerPosition=playerPosition;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
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

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

}
