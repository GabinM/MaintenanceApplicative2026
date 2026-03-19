package trivia;

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

   List<Player> players = new ArrayList<>();

   List<Category> questionSet = new ArrayList<>();

   Category rockCategory = new Category("Rock", new int[]{3, 7, 11});
   Category popCategory = new Category("Pop", new int[]{0, 4, 8});
   Category scienceCategory = new Category("Science", new int[]{1, 5, 9});
   Category sportsCategory = new Category("Sports", new int[]{2, 6, 10});

   int currentPlayer = 0;
   boolean isGettingOutOfPenaltyBox;

   public Game() {
      questionSet.add(rockCategory);
      questionSet.add(scienceCategory);
      questionSet.add(sportsCategory);
      questionSet.add(popCategory);
      for (Category cat : questionSet) {
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
      for(Category cat : questionSet){
         if(cat.isCurrentCategory(getCurrentPlayer().getPos())){
            return cat;
         }
      }
      return questionSet.get(0);
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
