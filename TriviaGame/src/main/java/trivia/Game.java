package trivia;

import java.util.ArrayList;
import java.util.List;

// REFACTOR ME
public class Game implements IGame {
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

      players.add(new Player(playerName, false, 0, 1));

      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
      return true;
   }

   public int howManyPlayers() {
      return players.size();
   }

   public void roll(int roll) {
      Player playerRoll = getCurrentPlayer();
      System.out.println(playerRoll.getName() + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (playerRoll.isInJail()) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;

            System.out.println(playerRoll.getName() + " is getting out of the penalty box");
            playerRoll.move(roll);

            System.out.println(getUpdateOnPosition());
            System.out.println(getUpdateOnCategory());
            askQuestion();
         } else {
            System.out.println(playerRoll.getName() + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
         }

      } else {

         playerRoll.move(roll);

         System.out.println(getUpdateOnPosition());
         System.out.println(getUpdateOnCategory());
         askQuestion();
      }

   }

   private void askQuestion() {
      System.out.println(currentCategory().getQuestions().removeFirst());
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
         System.out.println("Answer was correct!!!!");
         getCurrentPlayer().setPurse(getCurrentPlayer().getPurse()+1);
         System.out.println(getUpdateOnMoney());

         boolean winner = didPlayerWin();
         currentPlayer++;
         if (currentPlayer == players.size()) currentPlayer = 0;

         return winner;
      }
   }

   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(getCurrentPlayer().getName() + " was sent to the penalty box");
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

   private String getUpdateOnMoney(){
      return getCurrentPlayer().getName()
              + " now has "
              + getCurrentPlayer().getPurse()
              + " Gold Coins.";
   }

   private String getUpdateOnPosition(){
      return getCurrentPlayer().getName()
              + "'s new location is "
              + getCurrentPlayer().getPos();
   }

   private String getUpdateOnCategory(){
      return "The category is " + currentCategory().getName();
   }
}
