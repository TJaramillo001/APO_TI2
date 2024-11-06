package model;
import model.Referee;

public class Match {
    private Team homeTeam;
    private Team awayTeam;
    private String matchDate;
    private Referee centralReferee;
    private Referee[] assistantReferees;

    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
}
    public Match(Team homeTeam, Team awayTeam, String matchDate) {
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
            this.matchDate = matchDate; // Date will be set separately
    }
    public Match(Team homeTeam, Team awayTeam, String matchDate, Referee centralReferee) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchDate = matchDate; // Date will be set separately
        this.centralReferee=centralReferee;
        this.assistantReferees= new Referee[2];
}

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    // Returns match details as a string
    public void displayMatch() {
        System.out.println(homeTeam.getTeamName() + " vs " + awayTeam.getTeamName() + " on " + matchDate);
    }



    // Getters for the teams and matchDate
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
}
