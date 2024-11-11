package model;

public class Player extends Person{
    
    private int playerNum;
    private PlayerPosition playerPosition;
    private int goalsScored;
    private int assists;
    private int yellowCards;
    private int redCards;
    private int matchesPlayed;
    /**
     * Description: This is the constructor class used for player registration.
     * @param String playerName : Corresponds to the player name
     * @param int playerNum : Corresponds to the player's jersey number
     * @param PlayerPosition playerPosition : Corresponds to the position in which this player plays (GOALKEEPER, DEFENDER, MIDFIELDER, or FORWARD)
     * @param String country : Corresponds to the country of origin of this player
     */
    public Player(String playerName, int playerNum, PlayerPosition playerPosition, String country){
        super(playerName, country);
        this.playerNum=playerNum;
        this.playerPosition=playerPosition;
    }
    /**
     * Description: This is the constructor class used to store all player related information
     * @param String playerName : Corresponds to the player name
     * @param int playerNum : Corresponds to the player's jersey number
     * @param PlayerPosition playerPosition : Corresponds to the position in which this player plays (GOALKEEPER, DEFENDER, MIDFIELDER, or FORWARD)
     * @param String country : Corresponds to the country of origin of this player
     * @param int goalsScored : Corresponds to the amount of goals scored by this player
     * @param int assists : Corresponds to the amount of assists given by this player
     * @param int yellowCards : Corresponds to the amount of yellow cards this player has received
     * @param int redCards : Corresponds to the amount of red Cards this player has received
     * @param int matchesPlayed : Corresponds to the amount of matches this player has played in.
     */
    public Player(String playerName, int playerNum, PlayerPosition playerPosition, String country, int goalsScored, int assists, int yellowCards, int redCards, int matchesPlayed){
        super(playerName, country);
        this.playerNum=playerNum;
        this.playerPosition=playerPosition;
        this.goalsScored=goalsScored;
        this.assists=assists;
        this.yellowCards=yellowCards;
        this.redCards=redCards;
        this.matchesPlayed=matchesPlayed;
    }
    //Getters & Setters
   
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
    
    public void incrementGoals() {
        this.goalsScored++;
    }
    
    public void incrementAssists() {
        this.assists++;
    }
    
    public void incrementMatchesPlayed() {
        this.matchesPlayed++;
    }
    
}
