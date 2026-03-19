package trivia.log;

import trivia.Category;
import trivia.Player;

public interface GameLogger {

    void logPlayerPosition(Player p);
    void logPlayerMoney(Player p);
    void logCategory(Category cat);
    void logPlayerAdded(Player p, int playerNumber);
    void logRoll(Player p, int roll);
    void logPlayerGettingOuttaJail(Player p);
    void logPlayerNotGettingOuttaJail(Player p);
    void logPlayerSendtInJail(Player p);
    void logCorrectAnswer();
    void logIncorrectAnswer();
    void logQuestion(String question);

}
