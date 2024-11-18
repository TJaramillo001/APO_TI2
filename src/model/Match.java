package model;
import java.util.ArrayList;
import java.util.List;

import model.Referee;

public class Match {
    private Team homeTeam;
    private Team awayTeam;
    private String matchDate;
    private Referee centralReferee;
    private Referee[] assistantReferees;
    private int homeScore;
    private int awayScore;
    private ArrayList<GoalDetail> goalDetails;
    private List<CardDetail> cardDetails;


    public Match() {
        this.goalDetails = new ArrayList<>();
        this.cardDetails = new ArrayList<>();
    }
    
    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.goalDetails = new ArrayList<>();
        this.cardDetails = new ArrayList<>();
    }
    public Match(Team homeTeam, Team awayTeam, String matchDate) {
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
            this.matchDate = matchDate; // Date will be set separately
            this.goalDetails = new ArrayList<>();
            this.cardDetails = new ArrayList<>();
    }
    public Match(Team homeTeam, Team awayTeam, String matchDate, Referee centralReferee) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchDate = matchDate; 
        this.centralReferee=centralReferee;
        this.assistantReferees= new Referee[2];
        this.homeScore = 0;  
        this.awayScore = 0; //Scores are initially set as 0
        this.goalDetails = new ArrayList<GoalDetail>();
        this.cardDetails = new ArrayList<>();
        
    }

    // Returns match details as a string
    public void displayMatch() {
        System.out.println(homeTeam.getTeamName() + " vs " + awayTeam.getTeamName() + " on " + matchDate);
    }

    public void printScore() {
        System.out.println("\nFinal Score: " + homeTeam.getTeamName() + " " + homeScore + " - " + awayScore + " " + awayTeam.getTeamName());
    }

    public void addGoalDetail(Player scorer, Player assister, int minute) {
        GoalDetail detail = new GoalDetail(scorer, assister, minute);
        goalDetails.add(detail);  
    }
    

    public void displayGoals() {
        if (goalDetails.isEmpty()) {
            System.out.println("No goals have been recorded for this match.");
        } else {
            for (GoalDetail detail : goalDetails) {
                System.out.println("Minute " + detail.getMinute() + ": " + detail.getScorer().getName() +
                        (detail.getAssister() != null ? " assisted by " + detail.getAssister().getName() : ""));
            }
        }
    }

    public void addCardDetail(Player person, CardType type, int minute){
        CardDetail detail = new CardDetail(person, type, minute);
        cardDetails.add(detail);
    }
    
    public void showCards(){
        if(cardDetails.isEmpty()){
            System.out.println("No cards have been recorded for this match");
        } else{
            for(CardDetail detail : cardDetails){
                System.out.println("Minute "+detail.getMinute() + ": "+detail.getPlayer().getName()+ 
                detail.getCardType());
            }
        }
    }
    

    // Getters & Setters
    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public String getDate() {
        return matchDate;
    }
    public Referee getCentralReferee(){
        return centralReferee;
    }
    public Referee getAss1Referee(){
        return assistantReferees[0];
    }
    public Referee getAss2Referee(){
        return assistantReferees[1];
    }

    public void setCentralReferee(Referee centralReferee) {
        this.centralReferee=centralReferee;
    }
    public void setAssistantReferees(Referee[] assistantReferees) {
        if (assistantReferees.length == 2) {
            this.assistantReferees = assistantReferees;
        } else {
            System.out.println("Error: Must provide exactly two assistant referees.");
        }
    }
    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    private boolean isScoreProcessed = false; // Flag

    public boolean isScoreProcessed() {
        return isScoreProcessed;
    }

    public void setScoreProcessed(boolean scoreProcessed) {
        isScoreProcessed = scoreProcessed;
    }
    
    
}
