package trivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// REFACTOR ME
public class Game implements IGame {
   List<Player> playerz = new ArrayList<>();
   ArrayList players = new ArrayList<>();
   int[] places = new int[6];
   int[] purses = new int[6];
   boolean[] inPenaltyBox = new boolean[6];

   Category rockCategory = new Category("Rock", new int[]{3, 7, 11, 12});
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
      places[howManyPlayers()] = 1;
      purses[howManyPlayers()] = 0;
      inPenaltyBox[howManyPlayers()] = false;
      players.add(playerName);

      playerz.add(new Player(playerName, false, 0, 1));

      System.out.println(playerName + " was added");
      System.out.println("They are player number " + playerz.size());
      return true;
   }

   public int howManyPlayers() {
      return playerz.size();
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

            System.out.println(playerRoll.getName()
                               + "'s new location is "
                               + playerRoll.getPos());
            System.out.println("The category is " + currentCategory().getName());
            askQuestion();
         } else {
            System.out.println(playerRoll.getName() + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
         }

      } else {

         playerRoll.move(roll);

         System.out.println(playerRoll.getName()
                            + "'s new location is "
                            + playerRoll.getPos());
         System.out.println("The category is " + currentCategory().getName());
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

      if (getCurrentPlayer().inJail) {
         if (isGettingOutOfPenaltyBox) {
            System.out.println("Answer was correct!!!!");
            getCurrentPlayer().setPurse(getCurrentPlayer().getPurse()+1);
            System.out.println(getCurrentPlayer().getName()
                               + " now has "
                               + getCurrentPlayer().getPurse()
                               + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == playerz.size()) currentPlayer = 0;

            return winner;
         } else {
            currentPlayer++;
            if (currentPlayer == playerz.size()) currentPlayer = 0;
            return true;
         }


      } else {

         System.out.println("Answer was correct!!!!");
         getCurrentPlayer().setPurse(getCurrentPlayer().getPurse()+1);
         System.out.println(players.get(currentPlayer)
                            + " now has "
                            + getCurrentPlayer().getPurse()
                            + " Gold Coins.");

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
      if (currentPlayer == playerz.size()) currentPlayer = 0;
      return true;
   }

   private Player getCurrentPlayer() {
      return playerz.get(currentPlayer);
   }

   private boolean didPlayerWin() {
      return !(getCurrentPlayer().getPurse() == 6);
   }
}
