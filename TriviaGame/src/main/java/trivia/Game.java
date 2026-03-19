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
      System.out.println("They are player number " + players.size());
      return true;
   }

   public int howManyPlayers() {
      return players.size();
   }

   public void roll(int roll) {
      System.out.println(players.get(currentPlayer) + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (inPenaltyBox[currentPlayer]) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;

            System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
            places[currentPlayer] = places[currentPlayer] + roll;
            if (places[currentPlayer] > 12) places[currentPlayer] = places[currentPlayer] - 12;

            System.out.println(players.get(currentPlayer)
                               + "'s new location is "
                               + places[currentPlayer]);
            System.out.println("The category is " + currentCategory().getName());
            askQuestion();
         } else {
            System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
         }

      } else {

         places[currentPlayer] = places[currentPlayer] + roll;
         if (places[currentPlayer] > 12) places[currentPlayer] = places[currentPlayer] - 12;

         System.out.println(players.get(currentPlayer)
                            + "'s new location is "
                            + places[currentPlayer]);
         System.out.println("The category is " + currentCategory().getName());
         askQuestion();
      }

   }

   private void askQuestion() {
      System.out.println(currentCategory().getQuestions().removeFirst());
   }


   private Category currentCategory() {
      for(Category cat : questionSet){
         if(cat.isCurrentCategory(places[currentPlayer])){
            return cat;
         }
      }
      return questionSet.get(0);
   }

   public boolean handleCorrectAnswer() {
      if (inPenaltyBox[currentPlayer]) {
         if (isGettingOutOfPenaltyBox) {
            System.out.println("Answer was correct!!!!");
            purses[currentPlayer]++;
            System.out.println(players.get(currentPlayer)
                               + " now has "
                               + purses[currentPlayer]
                               + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;

            return winner;
         } else {
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;
            return true;
         }


      } else {

         System.out.println("Answer was corrent!!!!");
         purses[currentPlayer]++;
         System.out.println(players.get(currentPlayer)
                            + " now has "
                            + purses[currentPlayer]
                            + " Gold Coins.");

         boolean winner = didPlayerWin();
         currentPlayer++;
         if (currentPlayer == players.size()) currentPlayer = 0;

         return winner;
      }
   }

   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
      inPenaltyBox[currentPlayer] = true;

      currentPlayer++;
      if (currentPlayer == players.size()) currentPlayer = 0;
      return true;
   }


   private boolean didPlayerWin() {
      return !(purses[currentPlayer] == 6);
   }
}
