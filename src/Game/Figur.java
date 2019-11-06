package Game;

class Figur {
    private int Feld = 0;
    private int ID = 0;
    private boolean round = false;

    Figur(int aktuellesFeld, int ID) {
        this.Feld = aktuellesFeld;
        this.ID = ID;
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
        //Jules Lösung
        return round;
    }

    void setRound(boolean p_round) {
        this.round = p_round;
    }
}