package trivia;

import java.util.LinkedList;
import java.util.List;

public class Category {
    private final String name;
    private final int[] slots;
    private final LinkedList<String> questions ;

    public Category(String name, int[] slots) {
        this.name = name;
        this.slots = slots;
        this.questions = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public int[] getSlots() {
        return slots;
    }

    public LinkedList<String> getQuestions() {
        return questions;
    }

    public boolean isCurrentCategory(int slot){
        for(int i : slots){
            if (i==slot-1) return true;
        }
        return false;
    }

    public void addQuestion(int questionIndex){
        this.questions.add(name + " Question "+questionIndex);
    }

    public void addQuestions(int nbQuestions){
        for (int i = 0; i < nbQuestions; i++) {
            addQuestion(i);
        }
    }



}
