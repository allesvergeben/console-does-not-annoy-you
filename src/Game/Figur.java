package Game;

class Figur {
    private int Feld;
    private int ID;
    private boolean round = false;

    Figur(int p_home, int p_id) {
        this.Feld = p_home;
        this.ID = p_id;
    }

    int getFeld() {
        return Feld;
    }

    void setFeld(int p_Feld) {
        this.Feld = p_Feld;
    }

    int getID() {
        return ID;
    }

    boolean getRound() {
        return round;
    }

    void setRound(boolean p_round) {
        this.round = p_round;
    }
}