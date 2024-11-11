package model;
import java.util.ArrayList;

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

    public Match() {
        this.goalDetails = new ArrayList<>();
    }
    
    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.goalDetails = new ArrayList<>();
    }
    public Match(Team homeTeam, Team awayTeam, String matchDate) {
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
            this.matchDate = matchDate; // Date will be set separately
            this.goalDetails = new ArrayList<>();
    }
    public Match(Team homeTeam, Team awayTeam, String matchDate, Referee centralReferee) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchDate = matchDate; // Date will be set separately
        this.centralReferee=centralReferee;
        this.assistantReferees= new Referee[2];
        this.homeScore = 0;  
        this.awayScore = 0; //Scores are initially set as 0
        this.goalDetails = new ArrayList<GoalDetail>();
        
    }


    // Returns match details as a string
    public void displayMatch() {
        System.out.println(homeTeam.getTeamName() + " vs " + awayTeam.getTeamName() + " on " + matchDate);
    }

    public void printScore() {
        System.out.println("\nFinal Score: " + homeTeam.getTeamName() + " " + homeScore + " - " + awayScore + " " + awayTeam.getTeamName());
    }

    public void addGoalDetail(Player scorer, Player assister, int minute) {
        
        if (scorer == null) {
            System.out.println("Error: scorer is null");
            return;
        }
    
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
    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    // Editar
    
    
}
