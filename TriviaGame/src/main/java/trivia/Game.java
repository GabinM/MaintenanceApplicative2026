package trivia;

import java.util.ArrayList;
import java.util.List;

// REFACTOR ME
public class Game implements IGame {

   GameLogger logger = new SoutGameLogger();

   List<Player> players = new ArrayList<>();

   Category rockCategory = new Category("Rock", new int[]{3, 7, 11});
   Category popCategory = new Category("Pop", new int[]{0, 4, 8});
   Category scienceCategory = new Category("Science", new int[]{1, 5, 9});
   Category sportsCategory = new Category("Sports", new int[]{2, 6, 10});

   List<Category> questionSet = new ArrayList<>();

   int currentPlayer = 0;
   boolean isGettingOutOfPenaltyBox;

   public Game() {
      questionSet.add(rockCategory);
      questionSet.add(scienceCategory);
      questionSet.add(sportsCategory);
      questionSet.add(popCategory);
      for (Category cat : questionSet) {
         cat.addQuestions(50);
      }
   }

   public boolean isPlayable() {
      return (howManyPlayers() >= 2);
   }

   public boolean add(String playerName) {

      Player newPlayer = new Player(playerName, false, 0, 1);

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
         currentPlayer++;
         if (currentPlayer == players.size()) currentPlayer = 0;
         return true;
      } else {
         logger.logCorrectAnswer();
         getCurrentPlayer().setPurse(getCurrentPlayer().getPurse()+1);
         logger.logPlayerMoney(getCurrentPlayer());

         boolean winner = didPlayerWin();
         currentPlayer++;
         if (currentPlayer == players.size()) currentPlayer = 0;

         return winner;
      }
   }

   public boolean wrongAnswer() {
      logger.logIncorrectAnswer();
      logger.logPlayerSendtInJail(getCurrentPlayer());
      getCurrentPlayer().inJail = true;

      currentPlayer++;
      if (currentPlayer == players.size()) currentPlayer = 0;
      return true;
   }

   private Player getCurrentPlayer() {
      return players.get(currentPlayer);
   }

   private boolean didPlayerWin() {
      return !(getCurrentPlayer().getPurse() == 6);
   }
}
