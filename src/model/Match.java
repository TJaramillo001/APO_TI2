package model;
import model.Referee;

public class Match {
    private Team homeTeam;
    private Team awayTeam;
    private String matchDate;
    
    public Match(Team homeTeam, Team awayTeam, String matchDate) {
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
            this.matchDate = matchDate; // Date will be set separately
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
}
