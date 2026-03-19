package trivia;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public final static int BOARD_SIZE = 12;

    List<Category> categories;
    Category[] board;

    public Board() {
        categories = new ArrayList<Category>();
        board = new Category[BOARD_SIZE];
    }

    public Category getCurrentCategory(int pos) {
        return board[pos-1];
    }

    public Category[] getBoard() {
        return board;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setSlotToCategory(int pos, Category category) {
        board[pos] = category;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }


}
