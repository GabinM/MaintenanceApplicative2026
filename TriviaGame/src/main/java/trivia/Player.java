package trivia;

public class Player {

    String name;
    boolean inJail;
    int purse;
    int pos;

    public Player(String name, boolean inJail, int purse, int pos) {
        this.name = name;
        this.inJail = inJail;
        this.purse = purse;
        this.pos = pos;
    }

    public int getPurse() {
        return purse;
    }

    public void setPurse(int purse) {
        this.purse = purse;
    }

    public String getName() {
        return name;
    }

    public boolean isInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

}
