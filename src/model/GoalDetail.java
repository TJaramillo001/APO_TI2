package model;

public class GoalDetail {
    private Player scorer;
    private Player assister;
    private int minute;

    public GoalDetail(Player scorer, Player assister, int minute) {
        this.scorer = scorer;
        this.assister = assister;
        this.minute = minute;
    }

    public Player getScorer() {
        return scorer;
    }

    public Player getAssister() {
        return assister;
    }

    public int getMinute() {
        return minute;
    }
}
