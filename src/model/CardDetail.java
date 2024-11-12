package model;

public class CardDetail {
    private Player player;
    private CardType cardType;
    private int minute;

    public CardDetail(Player player, CardType cardType, int minute) {
        this.player = player;
        this.cardType = cardType;
        this.minute = minute;
    }

    public Player getPlayer() {
        return player;
    }

    public String getCardType() {
        return cardType.toString();
    }

    public int getMinute() {
        return minute;
    }
}
