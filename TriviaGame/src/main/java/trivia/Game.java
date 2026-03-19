package trivia;

import trivia.log.GameLogger;
import trivia.log.SoutGameLogger;

import java.util.ArrayList;
import java.util.List;

// REFACTOR ME
public class Game implements IGame {

   public final static int BASE_MONEY_ON_START = 0;
   public final static int REQUIRED_MONEY = 6;
   public final static int BASE_SLOT_ON_START = 1;
   public final static boolean START_IN_JAIL = false;
   public final static int QUESTIONS_PER_CATEGORY = 50;

   GameLogger logger = new SoutGameLogger();
   Board board = new Board();

   List<Player> players = new ArrayList<>();

   Category rockCategory = new Category("Rock");
   Category popCategory = new Category("Pop");
   Category scienceCategory = new Category("Science");
   Category sportsCategory = new Category("Sports");

   int currentPlayer = 0;
   boolean isGettingOutOfPenaltyBox;

   public Game() {
      board.addCategory(rockCategory);
      board.addCategory(popCategory);
      board.addCategory(scienceCategory);
      board.addCategory(sportsCategory);

      board.setSlotToCategory(0, popCategory);
      board.setSlotToCategory(1, scienceCategory);
      board.setSlotToCategory(2, sportsCategory);
      board.setSlotToCategory(3, rockCategory);
      board.setSlotToCategory(4, popCategory);
      board.setSlotToCategory(5, scienceCategory);
      board.setSlotToCategory(6, sportsCategory);
      board.setSlotToCategory(7, rockCategory);
      board.setSlotToCategory(8, popCategory);
      board.setSlotToCategory(9, scienceCategory);
      board.setSlotToCategory(10, sportsCategory);
      board.setSlotToCategory(11, rockCategory);

      for (Category cat : board.getCategories()) {
         cat.addQuestions(QUESTIONS_PER_CATEGORY);
      }
   }

   public boolean isPlayable() {
      return (howManyPlayers() >= 2);
   }

   public boolean add(String playerName) {

      Player newPlayer = new Player(playerName, START_IN_JAIL, BASE_MONEY_ON_START, BASE_SLOT_ON_START);

      players.add(newPlayer);

      logger.logPlayerAdded(newPlayer, players.size());
      return true;
   }

   public int howManyPlayers() {
      return players.size();
   }

   public void roll(int roll) {
      logger.logRoll(getCurrentPlayer(), roll);

      if (getCurrentPlayer().isInJail()) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;
            getCurrentPlayer().setInJail(false);
            logger.logPlayerGettingOuttaJail(getCurrentPlayer());
            getCurrentPlayer().move(roll);

            logger.logPlayerPosition(getCurrentPlayer());
            logger.logCategory(currentCategory());
            askQuestion();
         } else {
            logger.logPlayerNotGettingOuttaJail(getCurrentPlayer());
            isGettingOutOfPenaltyBox = false;
         }

      } else {

         getCurrentPlayer().move(roll);

         logger.logPlayerPosition(getCurrentPlayer());
         logger.logCategory(currentCategory());
         askQuestion();
      }

   }

   private void askQuestion() {
      logger.logQuestion(currentCategory().getQuestions().removeFirst());
   }


   private Category currentCategory() {
      return board.getCurrentCategory(getCurrentPlayer().getPos());
   }

   public boolean handleCorrectAnswer() {

      if(getCurrentPlayer().isInJail() && !isGettingOutOfPenaltyBox) {
         nextPlayer();
         return true;
      } else {
         logger.logCorrectAnswer();
         getCurrentPlayer().addCoinToPurse(1);
         logger.logPlayerMoney(getCurrentPlayer());

         boolean winner = didPlayerWin();
         nextPlayer();
         return winner;
      }
   }

   public boolean wrongAnswer() {
      logger.logIncorrectAnswer();
      logger.logPlayerSendtInJail(getCurrentPlayer());
      getCurrentPlayer().setInJail(true);

      nextPlayer();
      return true;
   }

   private Player getCurrentPlayer() {
      return players.get(currentPlayer);
   }

   private boolean didPlayerWin() {
      return !(getCurrentPlayer().getPurse() == REQUIRED_MONEY);
   }

   private void nextPlayer() {
      currentPlayer++;
      if (currentPlayer == players.size()) currentPlayer = 0;
   }
}
