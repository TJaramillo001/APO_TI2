package model;

public class Team {
    private String teamName;
    private String country;
    private Player[] players;
    private String coachName;
    private int matchesPlayed;
    private int matchesWon;
    private int matchesLost;
    private int matchesDraw;
    private int goalsFor;
    private int goalsAgainst;
    private int yellowCards;
    private int redCards;
    /**
     * Description: Initial constructor class used to create and register teams
     * @param String teamName : Corresponds to the team name
     * @param String country : Corresponds to the country of origin of the team
     * @param String coachName : Corresponds to the name of the head coach of that team.
     */
    public Team(String teamName, String country, String coachName) {
        this.teamName = teamName;
        this.country = country;
        this.coachName = coachName;
    }
    /**
     * Description: Constructor class used to hold information regarding each team
     * @param String teamName : Corresponds to the team name
     * @param String country : Corresponds to the country of origin of the team
     * @param String coachName : Corresponds to the name of the head coach of that team.
     * @param int matchesPlayed : Corresponds to the amount of matches played
     * @param int matchesWon : Corresponds to the amount of matches won
     * @param int matchesLost : Corresponds to the amount of matches lost
     * @param int goalsFor : Corresponds to the total sum of goals this team has scored
     * @param int goalsAgainst : Corresponds to the total sum of goals other teams have scored against this team
     * @param int yellowCards : Corresponds to the amount of yellow cards the players in this team have received
     * @param int redCards : Corresponds to the amount of red cards the players in this team have received
     */
    public Team(String teamName, String country, String coachName, int matchesPlayed,int matchesDraw, int matchesWon, int matchesLost, int goalsFor, int goalsAgainst, int yellowCards, int redCards) {
        
        this.teamName = teamName;
        this.country = country;
        this.coachName = coachName;
        this.matchesPlayed = matchesPlayed;
        this.matchesWon = matchesWon;
        this.matchesDraw=matchesDraw;
        this.matchesLost = matchesLost;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.yellowCards = yellowCards;
        this.redCards = redCards;

        this.players = new Player[20];


    }

    //Usable methods
    /**
     * Description: This method will receive a Player datatype and associate it within the team in case that the team is not full yet
     * @param Player player : This corresponds to a player object that will be added to a specific team
     */
    public void addPlayer(Player player) {
        boolean added = false; // Flag to check if the player was added
        
        for (int i = 0; i < players.length; i++) {
            if (players[i] == null) {
                players[i] = player;
                System.out.println("Player successfully registered");
                added = true; // Set the flag to true
                break; // Exit the loop after adding the player
            }
        }
        // If the player was not added, it means the team is full
        if (!added) {
            System.out.println("Sorry. This team is already full");
        }
    }

    public void incrementGoalsFor(){
        this.goalsFor++;
    }
    public void incrementGoalsAgainst(){
        this.goalsAgainst++;
    }
    public void incrementMatchesPlayed(){
        this.matchesPlayed++;
    }
    public void incrementMatchesWon(){
        this.matchesWon++;
    }
    public void incrementMatchesLost(){
        this.matchesLost++;
    }
    public void incrementMatchesDraw(){
        this.matchesDraw++;
    }
    public void incrementYellowCards() {
        this.yellowCards++;
    }
    public void incrementRedCards() {
        this.redCards++;
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

    public String getPlayerNames() { //Returns a list of all players in this team
        String playerNames = "";
        
        for (Player player : players) {
            if (player != null) {
                playerNames += "- " + player.getName() + "\n";
            }
        }
        
        return !playerNames.isEmpty() ? playerNames : "No players registered";
    }
    public int getMatchesDraw(){
        return matchesDraw;
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