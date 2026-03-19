package trivia;

import java.util.LinkedList;
import java.util.List;

public class Category {
    private final String name;
    private final LinkedList<String> questions ;

    public Category(String name) {
        this.name = name;
        this.questions = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public LinkedList<String> getQuestions() {
        return questions;
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
