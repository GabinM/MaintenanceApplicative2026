package trivia;

public class SoutGameLogger implements GameLogger {
    @Override
    public void logPlayerPosition(Player p) {
        System.out.println(p.getName()
                + "'s new location is "
                + p.getPos());
    }

    @Override
    public void logPlayerMoney(Player p) {
        System.out.println(p.getName()
                + " now has "
                + p.getPurse()
                + " Gold Coins.");
    }

    @Override
    public void logCategory(Category cat) {
        System.out.println("The category is " + cat.getName());
    }

    @Override
    public void logPlayerAdded(Player p, int playerNumber) {
        System.out.println(p.getName() + " was added");
        System.out.println("They are player number " + playerNumber);
    }

    @Override
    public void logRoll(Player p, int roll) {
        System.out.println(p.getName() + " is the current player");
        System.out.println("They have rolled a " + roll);
    }

    @Override
    public void logPlayerGettingOuttaJail(Player p) {
        System.out.println(p.getName() + " is getting out of the penalty box");
    }

    @Override
    public void logPlayerNotGettingOuttaJail(Player p) {
        System.out.println(p.getName() + " is not getting out of the penalty box");
    }

    @Override
    public void logPlayerSendtInJail(Player p) {
        System.out.println(p.getName() + " was sent to the penalty box");
    }

    @Override
    public void logCorrectAnswer() {
        System.out.println("Answer was correct!!!!");
    }

    @Override
    public void logIncorrectAnswer() {
        System.out.println("Question was incorrectly answered");
    }

    @Override
    public void logQuestion(String question){
        System.out.println(question);
    }
}
