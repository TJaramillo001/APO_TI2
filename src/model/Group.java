package model;

public class Group {
    private String groupName;
    private Team[] teams;
    private Match[] matches;
    private int teamCount;
    private int matchCount;
    public Group(String groupName) {
        this.groupName = groupName;
        this.teams=new Team[4];
        this.matches = new Match[6];
        this.teamCount=0;
        this.matchCount=0;
    }

    public void addTeam(Team team){
        if(teamCount<4){
            teams[teamCount]=team;
            teamCount++;
        } else {
            System.out.println("Group is already full");
        }
    }

    public void generateMatches() {
        matchCount=0;
        for (int i = 0; i < teams.length; i++) {
            for (int j = i + 1; j < teams.length; j++) {
                matches[matchCount] = new Match(teams[i], teams[j]);
                matchCount++;
            }
        }
    }
    
    public void displayGroup() {
        for (Team team : teams) {
            if (team != null) {
                System.out.println("- "+team.getTeamName());
            }
        }
    }

    public void showMatches(){
        for (int i=0; i<matchCount; i++){
            matches[i].displayMatch();
        }
    }

    public String getGroupName() {
        return groupName;
    }

    public Team[] getTeams() {
        return teams;
    }

    public Match[] getMatches() {
        return matches;
    }    
}
